package com.mwzhang.bgp.core.tabledump;


import com.mwzhang.bgp.core.BgpAttributes;
import com.mwzhang.bgp.core.IpPrefix;

import java.net.InetAddress;

/**
 * Created by Mingwei Zhang on 8/12/15.
 * <p>
 * RIB entry class for parsing and storage.
 */
public class TableDumpV1Entry extends TableDumpEntry {

    private int viewNumber;
    private int seqNumber;
    private IpPrefix prefix;
    private int prefixLength;
    private int status;
    private int originatedTime;
    private InetAddress peerIpAddress;
    private int peerAs;
    private int attributeLength;
    private BgpAttributes bgpAttributes = new BgpAttributes();

    public TableDumpV1Entry(int type, int subtime, int timestamp) {
        super(type, subtime, timestamp);
    }


    public IpPrefix getPrefix() {
        return prefix;
    }

    public void setPrefix(IpPrefix prefix) {
        this.prefix = prefix;
    }

    public int getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(int prefixLength) {
        this.prefixLength = prefixLength;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOriginatedTime() {
        return originatedTime;
    }

    public void setOriginatedTime(int originatedTime) {
        this.originatedTime = originatedTime;
    }

    public InetAddress getPeerIpAddress() {
        return peerIpAddress;
    }

    public void setPeerIpAddress(InetAddress peerIpAddress) {
        this.peerIpAddress = peerIpAddress;
    }

    public int getPeerAs() {
        return peerAs;
    }

    public void setPeerAs(int peerAs) {
        this.peerAs = peerAs;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }

    public BgpAttributes getBgpAttributes() {
        return bgpAttributes;
    }

    public void setBgpAttributes(BgpAttributes bgpAttributes) {
        this.bgpAttributes = bgpAttributes;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }


}
