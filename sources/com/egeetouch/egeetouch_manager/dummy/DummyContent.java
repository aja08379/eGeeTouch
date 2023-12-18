package com.egeetouch.egeetouch_manager.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class DummyContent {
    private static final int COUNT = 25;
    public static final List<DummyItem> ITEMS = new ArrayList();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap();

    static {
        for (int i = 1; i <= 25; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem dummyItem) {
        ITEMS.add(dummyItem);
        ITEM_MAP.put(dummyItem.id, dummyItem);
    }

    private static DummyItem createDummyItem(int i) {
        return new DummyItem(String.valueOf(i), "Item " + i, makeDetails(i));
    }

    private static String makeDetails(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Details about Item: ").append(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("\nMore details information here.");
        }
        return sb.toString();
    }

    /* loaded from: classes.dex */
    public static class DummyItem {
        public final String content;
        public final String details;
        public final String id;

        public DummyItem(String str, String str2, String str3) {
            this.id = str;
            this.content = str2;
            this.details = str3;
        }

        public String toString() {
            return this.content;
        }
    }
}
