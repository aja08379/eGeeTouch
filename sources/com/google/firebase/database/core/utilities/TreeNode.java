package com.google.firebase.database.core.utilities;

import com.google.firebase.database.snapshot.ChildKey;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class TreeNode<T> {
    public Map<ChildKey, TreeNode<T>> children = new HashMap();
    public T value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toString(String str) {
        String str2 = str + "<value>: " + this.value + "\n";
        if (this.children.isEmpty()) {
            return str2 + str + "<empty>";
        }
        for (Map.Entry<ChildKey, TreeNode<T>> entry : this.children.entrySet()) {
            str2 = str2 + str + entry.getKey() + ":\n" + entry.getValue().toString(str + "\t") + "\n";
        }
        return str2;
    }
}
