package com.mwzhang.bgp.core.tabledump.v2utils;

import java.net.InetAddress;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public class PeerEntry {
    public int peerType;
    public int peerBgpId;
    public int peerAs;
    public InetAddress peerIpAddress;
    public int index;

    public PeerEntry(int peerType, int peerBgpId, int peerAs, InetAddress peerIpAddress, int index) {
        this.peerType = peerType;
        this.peerBgpId = peerBgpId;
        this.peerAs = peerAs;
        this.peerIpAddress = peerIpAddress;
        this.index = index;
    }

}
