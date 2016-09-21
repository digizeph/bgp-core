package com.mwzhang.bgp.core.tabledump.v2utils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public class PeerIndexTable {
    int collectorBgpId;
    String viewName;
    int peerCount;
    List<PeerEntry> peerEntries;

    public PeerIndexTable(int collectorBgpId, String viewName, int peerCount) {
        this.collectorBgpId = collectorBgpId;
        this.viewName = viewName;
        this.peerCount = peerCount;
        peerEntries = new ArrayList<>();
    }

    public void addEntry(int type, int bgpId, int as, InetAddress address, int index) {
        peerEntries.add(new PeerEntry(type, bgpId, as, address, index));
    }

    public List<PeerEntry> getEntries() {
        return peerEntries;
    }
}
