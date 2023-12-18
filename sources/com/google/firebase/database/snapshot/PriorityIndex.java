package com.google.firebase.database.snapshot;
/* loaded from: classes2.dex */
public class PriorityIndex extends Index {
    private static final PriorityIndex INSTANCE = new PriorityIndex();

    public int hashCode() {
        return 3155577;
    }

    public String toString() {
        return "PriorityIndex";
    }

    public static PriorityIndex getInstance() {
        return INSTANCE;
    }

    private PriorityIndex() {
    }

    @Override // java.util.Comparator
    public int compare(NamedNode namedNode, NamedNode namedNode2) {
        return NodeUtilities.nameAndPriorityCompare(namedNode.getName(), namedNode.getNode().getPriority(), namedNode2.getName(), namedNode2.getNode().getPriority());
    }

    @Override // com.google.firebase.database.snapshot.Index
    public boolean isDefinedOn(Node node) {
        return !node.getPriority().isEmpty();
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode makePost(ChildKey childKey, Node node) {
        return new NamedNode(childKey, new StringNode("[PRIORITY-POST]", node));
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode maxPost() {
        return makePost(ChildKey.getMaxName(), Node.MAX_NODE);
    }

    @Override // com.google.firebase.database.snapshot.Index
    public String getQueryDefinition() {
        throw new IllegalArgumentException("Can't get query definition on priority index!");
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return obj instanceof PriorityIndex;
    }
}
