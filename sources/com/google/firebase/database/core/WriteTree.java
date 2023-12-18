package com.google.firebase.database.core;

import com.google.firebase.database.core.utilities.Predicate;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class WriteTree {
    private static final Predicate<UserWriteRecord> DEFAULT_FILTER = new Predicate<UserWriteRecord>() { // from class: com.google.firebase.database.core.WriteTree.2
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(UserWriteRecord userWriteRecord) {
            return userWriteRecord.isVisible();
        }
    };
    private CompoundWrite visibleWrites = CompoundWrite.emptyWrite();
    private List<UserWriteRecord> allWrites = new ArrayList();
    private Long lastWriteId = -1L;

    public WriteTreeRef childWrites(Path path) {
        return new WriteTreeRef(path, this);
    }

    public void addOverwrite(Path path, Node node, Long l, boolean z) {
        Utilities.hardAssert(l.longValue() > this.lastWriteId.longValue());
        this.allWrites.add(new UserWriteRecord(l.longValue(), path, node, z));
        if (z) {
            this.visibleWrites = this.visibleWrites.addWrite(path, node);
        }
        this.lastWriteId = l;
    }

    public void addMerge(Path path, CompoundWrite compoundWrite, Long l) {
        Utilities.hardAssert(l.longValue() > this.lastWriteId.longValue());
        this.allWrites.add(new UserWriteRecord(l.longValue(), path, compoundWrite));
        this.visibleWrites = this.visibleWrites.addWrites(path, compoundWrite);
        this.lastWriteId = l;
    }

    public UserWriteRecord getWrite(long j) {
        for (UserWriteRecord userWriteRecord : this.allWrites) {
            if (userWriteRecord.getWriteId() == j) {
                return userWriteRecord;
            }
        }
        return null;
    }

    public List<UserWriteRecord> purgeAllWrites() {
        ArrayList arrayList = new ArrayList(this.allWrites);
        this.visibleWrites = CompoundWrite.emptyWrite();
        this.allWrites = new ArrayList();
        return arrayList;
    }

    public boolean removeWrite(long j) {
        UserWriteRecord userWriteRecord;
        Iterator<UserWriteRecord> it = this.allWrites.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                userWriteRecord = null;
                break;
            }
            userWriteRecord = it.next();
            if (userWriteRecord.getWriteId() == j) {
                break;
            }
            i++;
        }
        Utilities.hardAssert(userWriteRecord != null, "removeWrite called with nonexistent writeId");
        this.allWrites.remove(userWriteRecord);
        boolean isVisible = userWriteRecord.isVisible();
        boolean z = false;
        for (int size = this.allWrites.size() - 1; isVisible && size >= 0; size--) {
            UserWriteRecord userWriteRecord2 = this.allWrites.get(size);
            if (userWriteRecord2.isVisible()) {
                if (size >= i && recordContainsPath(userWriteRecord2, userWriteRecord.getPath())) {
                    isVisible = false;
                } else if (userWriteRecord.getPath().contains(userWriteRecord2.getPath())) {
                    z = true;
                }
            }
        }
        if (isVisible) {
            if (z) {
                resetTree();
                return true;
            }
            if (userWriteRecord.isOverwrite()) {
                this.visibleWrites = this.visibleWrites.removeWrite(userWriteRecord.getPath());
            } else {
                Iterator<Map.Entry<Path, Node>> it2 = userWriteRecord.getMerge().iterator();
                while (it2.hasNext()) {
                    this.visibleWrites = this.visibleWrites.removeWrite(userWriteRecord.getPath().child(it2.next().getKey()));
                }
            }
            return true;
        }
        return false;
    }

    public Node getCompleteWriteData(Path path) {
        return this.visibleWrites.getCompleteNode(path);
    }

    public Node calcCompleteEventCache(Path path, Node node) {
        return calcCompleteEventCache(path, node, new ArrayList());
    }

    public Node calcCompleteEventCache(Path path, Node node, List<Long> list) {
        return calcCompleteEventCache(path, node, list, false);
    }

    public Node calcCompleteEventCache(final Path path, Node node, final List<Long> list, final boolean z) {
        if (list.isEmpty() && !z) {
            Node completeNode = this.visibleWrites.getCompleteNode(path);
            if (completeNode != null) {
                return completeNode;
            }
            CompoundWrite childCompoundWrite = this.visibleWrites.childCompoundWrite(path);
            if (childCompoundWrite.isEmpty()) {
                return node;
            }
            if (node != null || childCompoundWrite.hasCompleteWrite(Path.getEmptyPath())) {
                if (node == null) {
                    node = EmptyNode.Empty();
                }
                return childCompoundWrite.apply(node);
            }
            return null;
        }
        CompoundWrite childCompoundWrite2 = this.visibleWrites.childCompoundWrite(path);
        if (z || !childCompoundWrite2.isEmpty()) {
            if (z || node != null || childCompoundWrite2.hasCompleteWrite(Path.getEmptyPath())) {
                CompoundWrite layerTree = layerTree(this.allWrites, new Predicate<UserWriteRecord>() { // from class: com.google.firebase.database.core.WriteTree.1
                    @Override // com.google.firebase.database.core.utilities.Predicate
                    public boolean evaluate(UserWriteRecord userWriteRecord) {
                        return (userWriteRecord.isVisible() || z) && !list.contains(Long.valueOf(userWriteRecord.getWriteId())) && (userWriteRecord.getPath().contains(path) || path.contains(userWriteRecord.getPath()));
                    }
                }, path);
                if (node == null) {
                    node = EmptyNode.Empty();
                }
                return layerTree.apply(node);
            }
            return null;
        }
        return node;
    }

    public Node calcCompleteEventChildren(Path path, Node node) {
        Node Empty = EmptyNode.Empty();
        Node completeNode = this.visibleWrites.getCompleteNode(path);
        if (completeNode != null) {
            if (!completeNode.isLeafNode()) {
                for (NamedNode namedNode : completeNode) {
                    Empty = Empty.updateImmediateChild(namedNode.getName(), namedNode.getNode());
                }
            }
            return Empty;
        }
        CompoundWrite childCompoundWrite = this.visibleWrites.childCompoundWrite(path);
        for (NamedNode namedNode2 : node) {
            Empty = Empty.updateImmediateChild(namedNode2.getName(), childCompoundWrite.childCompoundWrite(new Path(namedNode2.getName())).apply(namedNode2.getNode()));
        }
        for (NamedNode namedNode3 : childCompoundWrite.getCompleteChildren()) {
            Empty = Empty.updateImmediateChild(namedNode3.getName(), namedNode3.getNode());
        }
        return Empty;
    }

    public Node calcEventCacheAfterServerOverwrite(Path path, Path path2, Node node, Node node2) {
        Utilities.hardAssert((node == null && node2 == null) ? false : true, "Either existingEventSnap or existingServerSnap must exist");
        Path child = path.child(path2);
        if (this.visibleWrites.hasCompleteWrite(child)) {
            return null;
        }
        CompoundWrite childCompoundWrite = this.visibleWrites.childCompoundWrite(child);
        if (childCompoundWrite.isEmpty()) {
            return node2.getChild(path2);
        }
        return childCompoundWrite.apply(node2.getChild(path2));
    }

    public Node calcCompleteChild(Path path, ChildKey childKey, CacheNode cacheNode) {
        Path child = path.child(childKey);
        Node completeNode = this.visibleWrites.getCompleteNode(child);
        if (completeNode != null) {
            return completeNode;
        }
        if (cacheNode.isCompleteForChild(childKey)) {
            return this.visibleWrites.childCompoundWrite(child).apply(cacheNode.getNode().getImmediateChild(childKey));
        }
        return null;
    }

    public Node shadowingWrite(Path path) {
        return this.visibleWrites.getCompleteNode(path);
    }

    public NamedNode calcNextNodeAfterPost(Path path, Node node, NamedNode namedNode, boolean z, Index index) {
        CompoundWrite childCompoundWrite = this.visibleWrites.childCompoundWrite(path);
        Node completeNode = childCompoundWrite.getCompleteNode(Path.getEmptyPath());
        NamedNode namedNode2 = null;
        if (completeNode == null) {
            if (node != null) {
                completeNode = childCompoundWrite.apply(node);
            }
            return namedNode2;
        }
        for (NamedNode namedNode3 : completeNode) {
            if (index.compare(namedNode3, namedNode, z) > 0 && (namedNode2 == null || index.compare(namedNode3, namedNode2, z) < 0)) {
                namedNode2 = namedNode3;
            }
        }
        return namedNode2;
    }

    private boolean recordContainsPath(UserWriteRecord userWriteRecord, Path path) {
        if (userWriteRecord.isOverwrite()) {
            return userWriteRecord.getPath().contains(path);
        }
        Iterator<Map.Entry<Path, Node>> it = userWriteRecord.getMerge().iterator();
        while (it.hasNext()) {
            if (userWriteRecord.getPath().child(it.next().getKey()).contains(path)) {
                return true;
            }
        }
        return false;
    }

    private void resetTree() {
        this.visibleWrites = layerTree(this.allWrites, DEFAULT_FILTER, Path.getEmptyPath());
        if (this.allWrites.size() > 0) {
            List<UserWriteRecord> list = this.allWrites;
            this.lastWriteId = Long.valueOf(list.get(list.size() - 1).getWriteId());
            return;
        }
        this.lastWriteId = -1L;
    }

    private static CompoundWrite layerTree(List<UserWriteRecord> list, Predicate<UserWriteRecord> predicate, Path path) {
        CompoundWrite emptyWrite = CompoundWrite.emptyWrite();
        for (UserWriteRecord userWriteRecord : list) {
            if (predicate.evaluate(userWriteRecord)) {
                Path path2 = userWriteRecord.getPath();
                if (userWriteRecord.isOverwrite()) {
                    if (path.contains(path2)) {
                        emptyWrite = emptyWrite.addWrite(Path.getRelative(path, path2), userWriteRecord.getOverwrite());
                    } else if (path2.contains(path)) {
                        emptyWrite = emptyWrite.addWrite(Path.getEmptyPath(), userWriteRecord.getOverwrite().getChild(Path.getRelative(path2, path)));
                    }
                } else if (path.contains(path2)) {
                    emptyWrite = emptyWrite.addWrites(Path.getRelative(path, path2), userWriteRecord.getMerge());
                } else if (path2.contains(path)) {
                    Path relative = Path.getRelative(path2, path);
                    if (relative.isEmpty()) {
                        emptyWrite = emptyWrite.addWrites(Path.getEmptyPath(), userWriteRecord.getMerge());
                    } else {
                        Node completeNode = userWriteRecord.getMerge().getCompleteNode(relative);
                        if (completeNode != null) {
                            emptyWrite = emptyWrite.addWrite(Path.getEmptyPath(), completeNode);
                        }
                    }
                }
            }
        }
        return emptyWrite;
    }
}
