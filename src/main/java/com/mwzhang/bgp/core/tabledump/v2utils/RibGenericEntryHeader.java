package com.mwzhang.bgp.core.tabledump.v2utils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public class RibGenericEntryHeader {
    int sequnceNumber;
    int afi;    // address family identifier;
    int subsequentAfi;
    int entryCount;
    // TODO: NLRI
    List<RibEntry> ribEntries = new ArrayList<>();

    // TODO: to be finished.

    public RibGenericEntryHeader(int sequnceNumber, int afi, int subsequentAfi, int nextHopAddrLen, InetAddress nextHopAddress, int entryCount) {
        this.sequnceNumber = sequnceNumber;
        this.afi = afi;
        this.subsequentAfi = subsequentAfi;
        this.entryCount = entryCount;
        // TODO: add NLRI
    }

    public void addRibEntry(RibEntry entry) {
        ribEntries.add(entry);
    }
}
