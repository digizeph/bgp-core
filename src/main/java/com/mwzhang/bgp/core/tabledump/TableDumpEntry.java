package com.mwzhang.bgp.core.tabledump;


import com.mwzhang.bgp.core.IpPrefix;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public abstract class TableDumpEntry {
    int type;
    int subtype;
    int timestamp;

    public TableDumpEntry(int type, int subtype, int timestamp) {
        this.type = type;
        this.subtype = subtype;
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public int getSubtype() {
        return subtype;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public abstract IpPrefix getPrefix();
}
