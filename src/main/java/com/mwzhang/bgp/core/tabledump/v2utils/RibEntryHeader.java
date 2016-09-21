package com.mwzhang.bgp.core.tabledump.v2utils;


import com.mwzhang.bgp.core.IpPrefix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public class RibEntryHeader {
    boolean isIpv4;
    boolean isUnicast;
    int sequenceNumber;
    int prefixLength;
    IpPrefix prefix;
    int entryCount;
    List<RibEntry> ribEntries = new ArrayList<>();

    /**
     * Constructor for a RibEntryHeader
     *
     * @param isIpv4         whether it is a IPv4 address
     * @param isUnicast      whether it is a unicast or a multicast
     * @param sequenceNumber sequence number
     * @param prefixLength   prefix length in bits
     * @param prefix         the IP prefix of the RIB entry
     * @param entryCount     number of the detailed sub-entries in this RIB entry.
     */
    public RibEntryHeader(boolean isIpv4, boolean isUnicast, int sequenceNumber, int prefixLength, IpPrefix prefix, int entryCount) {
        this.isIpv4 = isIpv4;
        this.isUnicast = isUnicast;
        this.sequenceNumber = sequenceNumber;
        this.prefixLength = prefixLength;
        this.prefix = prefix;
        this.entryCount = entryCount;
    }

    public IpPrefix getPrefix() {
        return this.prefix;
    }

    public void addRibEntry(RibEntry entry) {
        ribEntries.add(entry);
    }

    public List<RibEntry> getRibEntries() {
        return ribEntries;
    }
}
