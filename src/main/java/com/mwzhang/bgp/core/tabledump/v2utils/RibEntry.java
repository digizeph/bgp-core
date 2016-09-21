package com.mwzhang.bgp.core.tabledump.v2utils;


import com.mwzhang.bgp.core.BgpAttributes;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public class RibEntry {
    int peerIndex;
    int originatedTime;
    int attributeLength;
    BgpAttributes bgpAttributes;

    public RibEntry(int peerIndex, int originatedTime, int attributeLength, BgpAttributes bgpAttributes) {
        this.peerIndex = peerIndex;
        this.originatedTime = originatedTime;
        this.attributeLength = attributeLength;
        this.bgpAttributes = bgpAttributes;
    }

    public int getPeerIndex() {
        return peerIndex;
    }

    public int getOriginatedTime() {
        return originatedTime;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public BgpAttributes getBgpAttributes() {
        return bgpAttributes;
    }
}

