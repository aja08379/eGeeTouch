package com.google.firebase.database.core.operation;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;
/* loaded from: classes2.dex */
public abstract class Operation {
    protected final Path path;
    protected final OperationSource source;
    protected final OperationType type;

    /* loaded from: classes2.dex */
    public enum OperationType {
        Overwrite,
        Merge,
        AckUserWrite,
        ListenComplete
    }

    public abstract Operation operationForChild(ChildKey childKey);

    /* JADX INFO: Access modifiers changed from: protected */
    public Operation(OperationType operationType, OperationSource operationSource, Path path) {
        this.type = operationType;
        this.source = operationSource;
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }

    public OperationSource getSource() {
        return this.source;
    }

    public OperationType getType() {
        return this.type;
    }
}
