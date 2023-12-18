package com.google.firebase.database.core.view;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.core.EventRegistration;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.WriteTreeRef;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.ViewProcessor;
import com.google.firebase.database.core.view.filter.IndexedFilter;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class View {
    private final EventGenerator eventGenerator;
    private final List<EventRegistration> eventRegistrations;
    private final ViewProcessor processor;
    private final QuerySpec query;
    private ViewCache viewCache;

    public View(QuerySpec querySpec, ViewCache viewCache) {
        this.query = querySpec;
        IndexedFilter indexedFilter = new IndexedFilter(querySpec.getIndex());
        NodeFilter nodeFilter = querySpec.getParams().getNodeFilter();
        this.processor = new ViewProcessor(nodeFilter);
        CacheNode serverCache = viewCache.getServerCache();
        CacheNode eventCache = viewCache.getEventCache();
        IndexedNode from = IndexedNode.from(EmptyNode.Empty(), querySpec.getIndex());
        IndexedNode updateFullNode = indexedFilter.updateFullNode(from, serverCache.getIndexedNode(), null);
        IndexedNode updateFullNode2 = nodeFilter.updateFullNode(from, eventCache.getIndexedNode(), null);
        this.viewCache = new ViewCache(new CacheNode(updateFullNode2, eventCache.isFullyInitialized(), nodeFilter.filtersNodes()), new CacheNode(updateFullNode, serverCache.isFullyInitialized(), indexedFilter.filtersNodes()));
        this.eventRegistrations = new ArrayList();
        this.eventGenerator = new EventGenerator(querySpec);
    }

    /* loaded from: classes2.dex */
    public static class OperationResult {
        public final List<Change> changes;
        public final List<DataEvent> events;

        public OperationResult(List<DataEvent> list, List<Change> list2) {
            this.events = list;
            this.changes = list2;
        }
    }

    public QuerySpec getQuery() {
        return this.query;
    }

    public Node getCompleteNode() {
        return this.viewCache.getCompleteEventSnap();
    }

    public Node getServerCache() {
        return this.viewCache.getServerCache().getNode();
    }

    public Node getEventCache() {
        return this.viewCache.getEventCache().getNode();
    }

    public Node getCompleteServerCache(Path path) {
        Node completeServerSnap = this.viewCache.getCompleteServerSnap();
        if (completeServerSnap != null) {
            if (this.query.loadsAllData() || !(path.isEmpty() || completeServerSnap.getImmediateChild(path.getFront()).isEmpty())) {
                return completeServerSnap.getChild(path);
            }
            return null;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.eventRegistrations.isEmpty();
    }

    public void addEventRegistration(EventRegistration eventRegistration) {
        this.eventRegistrations.add(eventRegistration);
    }

    public List<Event> removeEventRegistration(EventRegistration eventRegistration, DatabaseError databaseError) {
        List<Event> emptyList;
        int i = 0;
        if (databaseError != null) {
            emptyList = new ArrayList<>();
            Utilities.hardAssert(eventRegistration == null, "A cancel should cancel all event registrations");
            Path path = this.query.getPath();
            for (EventRegistration eventRegistration2 : this.eventRegistrations) {
                emptyList.add(new CancelEvent(eventRegistration2, databaseError, path));
            }
        } else {
            emptyList = Collections.emptyList();
        }
        if (eventRegistration != null) {
            int i2 = -1;
            while (true) {
                if (i >= this.eventRegistrations.size()) {
                    i = i2;
                    break;
                }
                EventRegistration eventRegistration3 = this.eventRegistrations.get(i);
                if (eventRegistration3.isSameListener(eventRegistration)) {
                    if (eventRegistration3.isZombied()) {
                        break;
                    }
                    i2 = i;
                }
                i++;
            }
            if (i != -1) {
                this.eventRegistrations.remove(i);
                this.eventRegistrations.get(i).zombify();
            }
        } else {
            for (EventRegistration eventRegistration4 : this.eventRegistrations) {
                eventRegistration4.zombify();
            }
            this.eventRegistrations.clear();
        }
        return emptyList;
    }

    public OperationResult applyOperation(Operation operation, WriteTreeRef writeTreeRef, Node node) {
        boolean z = false;
        if (operation.getType() == Operation.OperationType.Merge && operation.getSource().getQueryParams() != null) {
            Utilities.hardAssert(this.viewCache.getCompleteServerSnap() != null, "We should always have a full cache before handling merges");
            Utilities.hardAssert(this.viewCache.getCompleteEventSnap() != null, "Missing event cache, even though we have a server cache");
        }
        ViewCache viewCache = this.viewCache;
        ViewProcessor.ProcessorResult applyOperation = this.processor.applyOperation(viewCache, operation, writeTreeRef, node);
        if (applyOperation.viewCache.getServerCache().isFullyInitialized() || !viewCache.getServerCache().isFullyInitialized()) {
            z = true;
        }
        Utilities.hardAssert(z, "Once a server snap is complete, it should never go back");
        this.viewCache = applyOperation.viewCache;
        return new OperationResult(generateEventsForChanges(applyOperation.changes, applyOperation.viewCache.getEventCache().getIndexedNode(), null), applyOperation.changes);
    }

    public List<DataEvent> getInitialEvents(EventRegistration eventRegistration) {
        CacheNode eventCache = this.viewCache.getEventCache();
        ArrayList arrayList = new ArrayList();
        for (NamedNode namedNode : eventCache.getNode()) {
            arrayList.add(Change.childAddedChange(namedNode.getName(), namedNode.getNode()));
        }
        if (eventCache.isFullyInitialized()) {
            arrayList.add(Change.valueChange(eventCache.getIndexedNode()));
        }
        return generateEventsForChanges(arrayList, eventCache.getIndexedNode(), eventRegistration);
    }

    private List<DataEvent> generateEventsForChanges(List<Change> list, IndexedNode indexedNode, EventRegistration eventRegistration) {
        List<EventRegistration> asList;
        if (eventRegistration == null) {
            asList = this.eventRegistrations;
        } else {
            asList = Arrays.asList(eventRegistration);
        }
        return this.eventGenerator.generateEventsForChanges(list, indexedNode, asList);
    }

    List<EventRegistration> getEventRegistrations() {
        return this.eventRegistrations;
    }
}
