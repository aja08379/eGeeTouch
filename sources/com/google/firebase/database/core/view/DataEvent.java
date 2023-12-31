package com.google.firebase.database.core.view;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.core.EventRegistration;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.view.Event;
/* loaded from: classes2.dex */
public class DataEvent implements Event {
    private final EventRegistration eventRegistration;
    private final Event.EventType eventType;
    private final String prevName;
    private final DataSnapshot snapshot;

    public DataEvent(Event.EventType eventType, EventRegistration eventRegistration, DataSnapshot dataSnapshot, String str) {
        this.eventType = eventType;
        this.eventRegistration = eventRegistration;
        this.snapshot = dataSnapshot;
        this.prevName = str;
    }

    @Override // com.google.firebase.database.core.view.Event
    public Path getPath() {
        Path path = this.snapshot.getRef().getPath();
        return this.eventType == Event.EventType.VALUE ? path : path.getParent();
    }

    public DataSnapshot getSnapshot() {
        return this.snapshot;
    }

    public String getPreviousName() {
        return this.prevName;
    }

    public Event.EventType getEventType() {
        return this.eventType;
    }

    @Override // com.google.firebase.database.core.view.Event
    public void fire() {
        this.eventRegistration.fireEvent(this);
    }

    @Override // com.google.firebase.database.core.view.Event
    public String toString() {
        if (this.eventType == Event.EventType.VALUE) {
            return getPath() + ": " + this.eventType + ": " + this.snapshot.getValue(true);
        }
        return getPath() + ": " + this.eventType + ": { " + this.snapshot.getKey() + ": " + this.snapshot.getValue(true) + " }";
    }
}
