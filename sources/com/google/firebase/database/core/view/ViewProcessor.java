package com.google.firebase.database.core.view;

import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.WriteTreeRef;
import com.google.firebase.database.core.operation.AckUserWrite;
import com.google.firebase.database.core.operation.Merge;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.core.operation.Overwrite;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.filter.ChildChangeAccumulator;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.ChildrenNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.KeyIndex;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class ViewProcessor {
    private static NodeFilter.CompleteChildSource NO_COMPLETE_SOURCE = new NodeFilter.CompleteChildSource() { // from class: com.google.firebase.database.core.view.ViewProcessor.1
        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public NamedNode getChildAfterChild(Index index, NamedNode namedNode, boolean z) {
            return null;
        }

        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public Node getCompleteChild(ChildKey childKey) {
            return null;
        }
    };
    private final NodeFilter filter;

    public ViewProcessor(NodeFilter nodeFilter) {
        this.filter = nodeFilter;
    }

    /* loaded from: classes2.dex */
    public static class ProcessorResult {
        public final List<Change> changes;
        public final ViewCache viewCache;

        public ProcessorResult(ViewCache viewCache, List<Change> list) {
            this.viewCache = viewCache;
            this.changes = list;
        }
    }

    public ProcessorResult applyOperation(ViewCache viewCache, Operation operation, WriteTreeRef writeTreeRef, Node node) {
        ViewCache applyServerOverwrite;
        ChildChangeAccumulator childChangeAccumulator = new ChildChangeAccumulator();
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[operation.getType().ordinal()];
        if (i == 1) {
            Overwrite overwrite = (Overwrite) operation;
            if (overwrite.getSource().isFromUser()) {
                applyServerOverwrite = applyUserOverwrite(viewCache, overwrite.getPath(), overwrite.getSnapshot(), writeTreeRef, node, childChangeAccumulator);
            } else {
                Utilities.hardAssert(overwrite.getSource().isFromServer());
                applyServerOverwrite = applyServerOverwrite(viewCache, overwrite.getPath(), overwrite.getSnapshot(), writeTreeRef, node, overwrite.getSource().isTagged() || (viewCache.getServerCache().isFiltered() && !overwrite.getPath().isEmpty()), childChangeAccumulator);
            }
        } else if (i == 2) {
            Merge merge = (Merge) operation;
            if (merge.getSource().isFromUser()) {
                applyServerOverwrite = applyUserMerge(viewCache, merge.getPath(), merge.getChildren(), writeTreeRef, node, childChangeAccumulator);
            } else {
                Utilities.hardAssert(merge.getSource().isFromServer());
                applyServerOverwrite = applyServerMerge(viewCache, merge.getPath(), merge.getChildren(), writeTreeRef, node, merge.getSource().isTagged() || viewCache.getServerCache().isFiltered(), childChangeAccumulator);
            }
        } else if (i == 3) {
            AckUserWrite ackUserWrite = (AckUserWrite) operation;
            if (!ackUserWrite.isRevert()) {
                applyServerOverwrite = ackUserWrite(viewCache, ackUserWrite.getPath(), ackUserWrite.getAffectedTree(), writeTreeRef, node, childChangeAccumulator);
            } else {
                applyServerOverwrite = revertUserWrite(viewCache, ackUserWrite.getPath(), writeTreeRef, node, childChangeAccumulator);
            }
        } else if (i == 4) {
            applyServerOverwrite = listenComplete(viewCache, operation.getPath(), writeTreeRef, node, childChangeAccumulator);
        } else {
            throw new AssertionError("Unknown operation: " + operation.getType());
        }
        ArrayList arrayList = new ArrayList(childChangeAccumulator.getChanges());
        maybeAddValueEvent(viewCache, applyServerOverwrite, arrayList);
        return new ProcessorResult(applyServerOverwrite, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.database.core.view.ViewProcessor$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType;

        static {
            int[] iArr = new int[Operation.OperationType.values().length];
            $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType = iArr;
            try {
                iArr[Operation.OperationType.Overwrite.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[Operation.OperationType.Merge.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[Operation.OperationType.AckUserWrite.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[Operation.OperationType.ListenComplete.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void maybeAddValueEvent(ViewCache viewCache, ViewCache viewCache2, List<Change> list) {
        CacheNode eventCache = viewCache2.getEventCache();
        if (eventCache.isFullyInitialized()) {
            boolean z = eventCache.getNode().isLeafNode() || eventCache.getNode().isEmpty();
            if (list.isEmpty() && viewCache.getEventCache().isFullyInitialized() && ((!z || eventCache.getNode().equals(viewCache.getCompleteEventSnap())) && eventCache.getNode().getPriority().equals(viewCache.getCompleteEventSnap().getPriority()))) {
                return;
            }
            list.add(Change.valueChange(eventCache.getIndexedNode()));
        }
    }

    private ViewCache generateEventCacheAfterServerEvent(ViewCache viewCache, Path path, WriteTreeRef writeTreeRef, NodeFilter.CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator) {
        Node calcCompleteChild;
        IndexedNode indexedNode;
        Node calcCompleteEventCache;
        CacheNode eventCache = viewCache.getEventCache();
        if (writeTreeRef.shadowingWrite(path) != null) {
            return viewCache;
        }
        boolean z = false;
        if (path.isEmpty()) {
            Utilities.hardAssert(viewCache.getServerCache().isFullyInitialized(), "If change path is empty, we must have complete server data");
            if (viewCache.getServerCache().isFiltered()) {
                Node completeServerSnap = viewCache.getCompleteServerSnap();
                if (!(completeServerSnap instanceof ChildrenNode)) {
                    completeServerSnap = EmptyNode.Empty();
                }
                calcCompleteEventCache = writeTreeRef.calcCompleteEventChildren(completeServerSnap);
            } else {
                calcCompleteEventCache = writeTreeRef.calcCompleteEventCache(viewCache.getCompleteServerSnap());
            }
            indexedNode = this.filter.updateFullNode(viewCache.getEventCache().getIndexedNode(), IndexedNode.from(calcCompleteEventCache, this.filter.getIndex()), childChangeAccumulator);
        } else {
            ChildKey front = path.getFront();
            if (front.isPriorityChildName()) {
                Utilities.hardAssert(path.size() == 1, "Can't have a priority with additional path components");
                Node calcEventCacheAfterServerOverwrite = writeTreeRef.calcEventCacheAfterServerOverwrite(path, eventCache.getNode(), viewCache.getServerCache().getNode());
                if (calcEventCacheAfterServerOverwrite != null) {
                    indexedNode = this.filter.updatePriority(eventCache.getIndexedNode(), calcEventCacheAfterServerOverwrite);
                } else {
                    indexedNode = eventCache.getIndexedNode();
                }
            } else {
                Path popFront = path.popFront();
                if (eventCache.isCompleteForChild(front)) {
                    Node calcEventCacheAfterServerOverwrite2 = writeTreeRef.calcEventCacheAfterServerOverwrite(path, eventCache.getNode(), viewCache.getServerCache().getNode());
                    if (calcEventCacheAfterServerOverwrite2 != null) {
                        calcCompleteChild = eventCache.getNode().getImmediateChild(front).updateChild(popFront, calcEventCacheAfterServerOverwrite2);
                    } else {
                        calcCompleteChild = eventCache.getNode().getImmediateChild(front);
                    }
                } else {
                    calcCompleteChild = writeTreeRef.calcCompleteChild(front, viewCache.getServerCache());
                }
                Node node = calcCompleteChild;
                if (node != null) {
                    indexedNode = this.filter.updateChild(eventCache.getIndexedNode(), front, node, popFront, completeChildSource, childChangeAccumulator);
                } else {
                    indexedNode = eventCache.getIndexedNode();
                }
            }
        }
        if (eventCache.isFullyInitialized() || path.isEmpty()) {
            z = true;
        }
        return viewCache.updateEventSnap(indexedNode, z, this.filter.filtersNodes());
    }

    private ViewCache applyServerOverwrite(ViewCache viewCache, Path path, Node node, WriteTreeRef writeTreeRef, Node node2, boolean z, ChildChangeAccumulator childChangeAccumulator) {
        IndexedNode updateChild;
        CacheNode serverCache = viewCache.getServerCache();
        NodeFilter nodeFilter = this.filter;
        if (!z) {
            nodeFilter = nodeFilter.getIndexedFilter();
        }
        boolean z2 = true;
        if (path.isEmpty()) {
            updateChild = nodeFilter.updateFullNode(serverCache.getIndexedNode(), IndexedNode.from(node, nodeFilter.getIndex()), null);
        } else if (nodeFilter.filtersNodes() && !serverCache.isFiltered()) {
            Utilities.hardAssert(!path.isEmpty(), "An empty path should have been caught in the other branch");
            ChildKey front = path.getFront();
            updateChild = nodeFilter.updateFullNode(serverCache.getIndexedNode(), serverCache.getIndexedNode().updateChild(front, serverCache.getNode().getImmediateChild(front).updateChild(path.popFront(), node)), null);
        } else {
            ChildKey front2 = path.getFront();
            if (serverCache.isCompleteForPath(path) || path.size() <= 1) {
                Path popFront = path.popFront();
                Node updateChild2 = serverCache.getNode().getImmediateChild(front2).updateChild(popFront, node);
                if (front2.isPriorityChildName()) {
                    updateChild = nodeFilter.updatePriority(serverCache.getIndexedNode(), updateChild2);
                } else {
                    updateChild = nodeFilter.updateChild(serverCache.getIndexedNode(), front2, updateChild2, popFront, NO_COMPLETE_SOURCE, null);
                }
                if (!serverCache.isFullyInitialized() && !path.isEmpty()) {
                    z2 = false;
                }
                ViewCache updateServerSnap = viewCache.updateServerSnap(updateChild, z2, nodeFilter.filtersNodes());
                return generateEventCacheAfterServerEvent(updateServerSnap, path, writeTreeRef, new WriteTreeCompleteChildSource(writeTreeRef, updateServerSnap, node2), childChangeAccumulator);
            }
            return viewCache;
        }
        if (!serverCache.isFullyInitialized()) {
            z2 = false;
        }
        ViewCache updateServerSnap2 = viewCache.updateServerSnap(updateChild, z2, nodeFilter.filtersNodes());
        return generateEventCacheAfterServerEvent(updateServerSnap2, path, writeTreeRef, new WriteTreeCompleteChildSource(writeTreeRef, updateServerSnap2, node2), childChangeAccumulator);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.google.firebase.database.core.view.ViewCache applyUserOverwrite(com.google.firebase.database.core.view.ViewCache r9, com.google.firebase.database.core.Path r10, com.google.firebase.database.snapshot.Node r11, com.google.firebase.database.core.WriteTreeRef r12, com.google.firebase.database.snapshot.Node r13, com.google.firebase.database.core.view.filter.ChildChangeAccumulator r14) {
        com.google.firebase.database.snapshot.Node r4;
        com.google.firebase.database.core.view.CacheNode r0 = r9.getEventCache();
        com.google.firebase.database.core.view.ViewProcessor.WriteTreeCompleteChildSource r6 = new com.google.firebase.database.core.view.ViewProcessor.WriteTreeCompleteChildSource(r12, r9, r13);
        if (r10.isEmpty()) {
            return r9.updateEventSnap(r8.filter.updateFullNode(r9.getEventCache().getIndexedNode(), com.google.firebase.database.snapshot.IndexedNode.from(r11, r8.filter.getIndex()), r14), true, r8.filter.filtersNodes());
        }
        com.google.firebase.database.snapshot.ChildKey r3 = r10.getFront();
        if (r3.isPriorityChildName()) {
            return r9.updateEventSnap(r8.filter.updatePriority(r9.getEventCache().getIndexedNode(), r11), r0.isFullyInitialized(), r0.isFiltered());
        }
        com.google.firebase.database.core.Path r5 = r10.popFront();
        com.google.firebase.database.snapshot.Node r10 = r0.getNode().getImmediateChild(r3);
        if (!r5.isEmpty()) {
            com.google.firebase.database.snapshot.Node r12 = r6.getCompleteChild(r3);
            if (r12 != null) {
                if (r5.getBack().isPriorityChildName() && r12.getChild(r5.getParent()).isEmpty()) {
                    r4 = r12;
                    return r10.equals(r4) ? r9.updateEventSnap(r8.filter.updateChild(r0.getIndexedNode(), r3, r4, r5, r6, r14), r0.isFullyInitialized(), r8.filter.filtersNodes()) : r9;
                }
                r11 = r12.updateChild(r5, r11);
            } else {
                r11 = com.google.firebase.database.snapshot.EmptyNode.Empty();
            }
        }
        r4 = r11;
        if (r10.equals(r4)) {
        }
    }

    private static boolean cacheHasChild(ViewCache viewCache, ChildKey childKey) {
        return viewCache.getEventCache().isCompleteForChild(childKey);
    }

    private ViewCache applyUserMerge(ViewCache viewCache, Path path, CompoundWrite compoundWrite, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        Utilities.hardAssert(compoundWrite.rootWrite() == null, "Can't have a merge that is an overwrite");
        Iterator<Map.Entry<Path, Node>> it = compoundWrite.iterator();
        ViewCache viewCache2 = viewCache;
        while (it.hasNext()) {
            Map.Entry<Path, Node> next = it.next();
            Path child = path.child(next.getKey());
            if (cacheHasChild(viewCache, child.getFront())) {
                viewCache2 = applyUserOverwrite(viewCache2, child, next.getValue(), writeTreeRef, node, childChangeAccumulator);
            }
        }
        Iterator<Map.Entry<Path, Node>> it2 = compoundWrite.iterator();
        ViewCache viewCache3 = viewCache2;
        while (it2.hasNext()) {
            Map.Entry<Path, Node> next2 = it2.next();
            Path child2 = path.child(next2.getKey());
            if (!cacheHasChild(viewCache, child2.getFront())) {
                viewCache3 = applyUserOverwrite(viewCache3, child2, next2.getValue(), writeTreeRef, node, childChangeAccumulator);
            }
        }
        return viewCache3;
    }

    private ViewCache applyServerMerge(ViewCache viewCache, Path path, CompoundWrite compoundWrite, WriteTreeRef writeTreeRef, Node node, boolean z, ChildChangeAccumulator childChangeAccumulator) {
        if (!viewCache.getServerCache().getNode().isEmpty() || viewCache.getServerCache().isFullyInitialized()) {
            Utilities.hardAssert(compoundWrite.rootWrite() == null, "Can't have a merge that is an overwrite");
            CompoundWrite addWrites = path.isEmpty() ? compoundWrite : CompoundWrite.emptyWrite().addWrites(path, compoundWrite);
            Node node2 = viewCache.getServerCache().getNode();
            Map<ChildKey, CompoundWrite> childCompoundWrites = addWrites.childCompoundWrites();
            ViewCache viewCache2 = viewCache;
            for (Map.Entry<ChildKey, CompoundWrite> entry : childCompoundWrites.entrySet()) {
                ChildKey key = entry.getKey();
                if (node2.hasChild(key)) {
                    viewCache2 = applyServerOverwrite(viewCache2, new Path(key), entry.getValue().apply(node2.getImmediateChild(key)), writeTreeRef, node, z, childChangeAccumulator);
                }
            }
            ViewCache viewCache3 = viewCache2;
            for (Map.Entry<ChildKey, CompoundWrite> entry2 : childCompoundWrites.entrySet()) {
                ChildKey key2 = entry2.getKey();
                boolean z2 = !viewCache.getServerCache().isCompleteForChild(key2) && entry2.getValue().rootWrite() == null;
                if (!node2.hasChild(key2) && !z2) {
                    viewCache3 = applyServerOverwrite(viewCache3, new Path(key2), entry2.getValue().apply(node2.getImmediateChild(key2)), writeTreeRef, node, z, childChangeAccumulator);
                }
            }
            return viewCache3;
        }
        return viewCache;
    }

    private ViewCache ackUserWrite(ViewCache viewCache, Path path, ImmutableTree<Boolean> immutableTree, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        if (writeTreeRef.shadowingWrite(path) != null) {
            return viewCache;
        }
        boolean isFiltered = viewCache.getServerCache().isFiltered();
        CacheNode serverCache = viewCache.getServerCache();
        if (immutableTree.getValue() != null) {
            if ((path.isEmpty() && serverCache.isFullyInitialized()) || serverCache.isCompleteForPath(path)) {
                return applyServerOverwrite(viewCache, path, serverCache.getNode().getChild(path), writeTreeRef, node, isFiltered, childChangeAccumulator);
            }
            if (path.isEmpty()) {
                CompoundWrite emptyWrite = CompoundWrite.emptyWrite();
                CompoundWrite compoundWrite = emptyWrite;
                for (NamedNode namedNode : serverCache.getNode()) {
                    compoundWrite = compoundWrite.addWrite(namedNode.getName(), namedNode.getNode());
                }
                return applyServerMerge(viewCache, path, compoundWrite, writeTreeRef, node, isFiltered, childChangeAccumulator);
            }
            return viewCache;
        }
        CompoundWrite emptyWrite2 = CompoundWrite.emptyWrite();
        Iterator<Map.Entry<Path, Boolean>> it = immutableTree.iterator();
        CompoundWrite compoundWrite2 = emptyWrite2;
        while (it.hasNext()) {
            Path key = it.next().getKey();
            Path child = path.child(key);
            if (serverCache.isCompleteForPath(child)) {
                compoundWrite2 = compoundWrite2.addWrite(key, serverCache.getNode().getChild(child));
            }
        }
        return applyServerMerge(viewCache, path, compoundWrite2, writeTreeRef, node, isFiltered, childChangeAccumulator);
    }

    public ViewCache revertUserWrite(ViewCache viewCache, Path path, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        Node calcCompleteEventChildren;
        if (writeTreeRef.shadowingWrite(path) != null) {
            return viewCache;
        }
        WriteTreeCompleteChildSource writeTreeCompleteChildSource = new WriteTreeCompleteChildSource(writeTreeRef, viewCache, node);
        IndexedNode indexedNode = viewCache.getEventCache().getIndexedNode();
        if (path.isEmpty() || path.getFront().isPriorityChildName()) {
            if (viewCache.getServerCache().isFullyInitialized()) {
                calcCompleteEventChildren = writeTreeRef.calcCompleteEventCache(viewCache.getCompleteServerSnap());
            } else {
                calcCompleteEventChildren = writeTreeRef.calcCompleteEventChildren(viewCache.getServerCache().getNode());
            }
            indexedNode = this.filter.updateFullNode(indexedNode, IndexedNode.from(calcCompleteEventChildren, this.filter.getIndex()), childChangeAccumulator);
        } else {
            ChildKey front = path.getFront();
            Node calcCompleteChild = writeTreeRef.calcCompleteChild(front, viewCache.getServerCache());
            if (calcCompleteChild == null && viewCache.getServerCache().isCompleteForChild(front)) {
                calcCompleteChild = indexedNode.getNode().getImmediateChild(front);
            }
            Node node2 = calcCompleteChild;
            if (node2 != null) {
                indexedNode = this.filter.updateChild(indexedNode, front, node2, path.popFront(), writeTreeCompleteChildSource, childChangeAccumulator);
            } else if (node2 == null && viewCache.getEventCache().getNode().hasChild(front)) {
                indexedNode = this.filter.updateChild(indexedNode, front, EmptyNode.Empty(), path.popFront(), writeTreeCompleteChildSource, childChangeAccumulator);
            }
            if (indexedNode.getNode().isEmpty() && viewCache.getServerCache().isFullyInitialized()) {
                Node calcCompleteEventCache = writeTreeRef.calcCompleteEventCache(viewCache.getCompleteServerSnap());
                if (calcCompleteEventCache.isLeafNode()) {
                    indexedNode = this.filter.updateFullNode(indexedNode, IndexedNode.from(calcCompleteEventCache, this.filter.getIndex()), childChangeAccumulator);
                }
            }
        }
        return viewCache.updateEventSnap(indexedNode, viewCache.getServerCache().isFullyInitialized() || writeTreeRef.shadowingWrite(Path.getEmptyPath()) != null, this.filter.filtersNodes());
    }

    private ViewCache listenComplete(ViewCache viewCache, Path path, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        CacheNode serverCache = viewCache.getServerCache();
        return generateEventCacheAfterServerEvent(viewCache.updateServerSnap(serverCache.getIndexedNode(), serverCache.isFullyInitialized() || path.isEmpty(), serverCache.isFiltered()), path, writeTreeRef, NO_COMPLETE_SOURCE, childChangeAccumulator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriteTreeCompleteChildSource implements NodeFilter.CompleteChildSource {
        private final Node optCompleteServerCache;
        private final ViewCache viewCache;
        private final WriteTreeRef writes;

        public WriteTreeCompleteChildSource(WriteTreeRef writeTreeRef, ViewCache viewCache, Node node) {
            this.writes = writeTreeRef;
            this.viewCache = viewCache;
            this.optCompleteServerCache = node;
        }

        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public Node getCompleteChild(ChildKey childKey) {
            CacheNode serverCache;
            CacheNode eventCache = this.viewCache.getEventCache();
            if (eventCache.isCompleteForChild(childKey)) {
                return eventCache.getNode().getImmediateChild(childKey);
            }
            if (this.optCompleteServerCache != null) {
                serverCache = new CacheNode(IndexedNode.from(this.optCompleteServerCache, KeyIndex.getInstance()), true, false);
            } else {
                serverCache = this.viewCache.getServerCache();
            }
            return this.writes.calcCompleteChild(childKey, serverCache);
        }

        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public NamedNode getChildAfterChild(Index index, NamedNode namedNode, boolean z) {
            Node node = this.optCompleteServerCache;
            if (node == null) {
                node = this.viewCache.getCompleteServerSnap();
            }
            return this.writes.calcNextNodeAfterPost(node, namedNode, z, index);
        }
    }
}
