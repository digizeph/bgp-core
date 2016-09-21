package com.mwzhang.bgp.core.tabledump;


import com.mwzhang.bgp.core.IpPrefix;
import com.mwzhang.bgp.core.tabledump.v2utils.PeerIndexTable;
import com.mwzhang.bgp.core.tabledump.v2utils.RibEntryHeader;
import com.mwzhang.bgp.core.tabledump.v2utils.RibGenericEntryHeader;

import java.net.InetAddress;

/**
 * Created by Mingwei Zhang on 8/15/2015.
 * <p>
 * Table dump class.
 */
public class TableDumpV2Entry extends TableDumpEntry {

    /**
     * The type of the TableDumpV2Entry:
     * 1 PEER_INDEX_TABLE
     * 2 RIB_IPV4_UNICAST
     * 3 RIB_IPV4_MULTICAST
     * 4 RIB_IPV6_UNICAST
     * 5 RIB_IPV6_MULTICAST
     * 6 RIB_GENERIC
     */

    private PeerIndexTable peerIndexTable;
    private RibEntryHeader ribEntryHeader;
    private RibGenericEntryHeader ribGenericEntryHeader;

    public TableDumpV2Entry(int type, int subtype, int timestamp) {
        super(type, subtype, timestamp);
    }

    @Override
    public IpPrefix getPrefix() {
        if (subtype == 1)
            return null;
        else if (subtype == 6)
            return null;    // NOTE: ignored.
        else {
            if (ribEntryHeader == null)
                return null;
            return ribEntryHeader.getPrefix();
        }
    }

    public void initPeerIndexTable(int bgpId, String viewName, int peerCount) {
        peerIndexTable = new PeerIndexTable(bgpId, viewName, peerCount);
    }

    public void initRibEntryHeader(boolean isIpv4, boolean isUnicast, int sequenceNumber, int prefixLength, IpPrefix prefix, int entryCount) {
        ribEntryHeader = new RibEntryHeader(isIpv4, isUnicast, sequenceNumber, prefixLength, prefix, entryCount);
    }

    public void initRibGenericEntryHeader(int seqNum, int afi, int safi, int nextHopLen, InetAddress nextHopAddr, int entryCOunt) {
        ribGenericEntryHeader = new RibGenericEntryHeader(seqNum, afi, safi, nextHopLen, nextHopAddr, entryCOunt);
    }

    public PeerIndexTable getPeerIndexTable() {
        return peerIndexTable;
    }

    public RibEntryHeader getRibEntryHeader() {
        return ribEntryHeader;
    }

    public RibGenericEntryHeader getRibGenericEntryHeader() {
        return ribGenericEntryHeader;
    }


}
