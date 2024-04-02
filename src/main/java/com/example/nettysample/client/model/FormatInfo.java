package com.example.nettysample.client.model;

import lombok.Getter;

@Getter
public class FormatInfo {
    private final FormatInfo prev;
    private final int pos;
    private final String key;
    private final String description;
    private final int length;

    public FormatInfo(FormatInfo prev, String key, String description, int length) {
        this.prev = prev;
        this.pos = prev == null ? 0 : prev.getPos()+prev.getLength();
        this.key = key;
        this.description = description;
        this.length = length;
    }
}
