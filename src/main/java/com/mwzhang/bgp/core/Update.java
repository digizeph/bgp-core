package com.mwzhang.bgp.core;

import com.google.common.base.Joiner;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Update class stores each BGP update in an object.
 *
 * @author Mingwei Zhang
 * @
 */

public class Update implements Serializable {

    /**
     * MRT header information
     * [1]: https://tools.ietf.org/html/draft-ietf-grow-mrt-09#section-5.4
     */
    private InetAddress peerIp;
    private InetAddress localIp;
    private int peerASN;
    private int localASN;
    private int timestamp;
    private String collector;

    /**
     * BGP message type
     * 1 - OPEN
     * 2 - UPDATE
     * 3 - NOTIFICATION
     * 4 - KEEPALIVE
     */
    private int messageType;

    /**
     * TYPE 1 OPEN
     */
    private int VERSION;
    private int SENDER_AS;
    private int HOLD_TIME;
    private int IDENTIFIER;

    /**
     * TYPE 3 NOTIFICATION
     */
    // Error codes
    private int ERROR_CODE;
    private int SUB_ERROR_CODE;

    /**
     * TYPE 4 KEEPALIVE
     */
    // No other messages
    /**
     * Attributes
     * [2]: http://tools.ietf.org/html/rfc4271#section-4.3
     */
    private BgpAttributes BGP_ATTR = new BgpAttributes();
    /**
     * Network Layer Reachability Information (NLRI)
     */
    private Set<IpPrefix> NLRI = new HashSet<>();
    private Set<IpPrefix> WITHDRAWN = new HashSet<>();

    public Update() {
    }

    /**
     * TYPE 2 UPDATE
     */
    public BgpAttributes getBGP_ATTR() {
        return BGP_ATTR;
    }

    /* MRT HEADER */
    /* MRT HEADER */
    /* MRT HEADER */

    public InetAddress getPeerIp() {
        return peerIp;
    }

    public void setPeerIp(InetAddress peerIp) {
        this.peerIp = peerIp;
    }

    public InetAddress getLocalIp() {
        return localIp;
    }

    public void setLocalIp(InetAddress localIp) {
        this.localIp = localIp;
    }

    public int getPeerASN() {
        return peerASN;
    }

    public void setPeerASN(int peerASN) {
        this.peerASN = peerASN;
    }

    public int getLocalASN() {
        return localASN;
    }

    public void setLocalASN(int localASN) {
        this.localASN = localASN;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }


    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }


    /* BGP MESSAGE TYPE */
    /* BGP MESSAGE TYPE */
    /* BGP MESSAGE TYPE */

    /**
     * BGP message type
     * 1 - OPEN
     * 2 - UPDATE
     * 3 - NOTIFICATION
     * 4 - KEEPALIVE
     *
     * @return the type number.
     */
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    /* TYPE 1 OPEN */
    /* TYPE 1 OPEN */
    /* TYPE 1 OPEN */

    public int getVERSION() {
        return VERSION;
    }

    public void setVERSION(int VERSION) {
        this.VERSION = VERSION;
    }

    public int getSENDER_AS() {
        return SENDER_AS;
    }

    public void setSENDER_AS(int SENDER_AS) {
        this.SENDER_AS = SENDER_AS;
    }

    public int getHOLD_TIME() {
        return HOLD_TIME;
    }

    public void setHOLD_TIME(int HOLD_TIME) {
        this.HOLD_TIME = HOLD_TIME;
    }

    public int getIDENTIFIER() {
        return IDENTIFIER;
    }

    public void setIDENTIFIER(int IDENTIFIER) {
        this.IDENTIFIER = IDENTIFIER;
    }

    /* TYPE 2 UPDATE */
    /* TYPE 2 UPDATE */
    /* TYPE 2 UPDATE */

    public void addNLRI(IpPrefix net) {
        this.NLRI.add(net);
    }

    public void addNLRI(List<IpPrefix> prefixes) {
        this.NLRI.addAll(prefixes);
    }

    public Set<IpPrefix> getNLRISet() {
        return this.NLRI;
    }

    public void setNLRI(Set<IpPrefix> subnets) {
        this.NLRI = subnets;
    }

    public String getNLRIString() {
        Joiner joiner = Joiner.on(",");
        return joiner.join(this.NLRI);
    }

    public String getWITHDRAWNString() {
        Joiner joiner = Joiner.on(",");
        return joiner.join(this.WITHDRAWN);
    }

    public Set<IpPrefix> getWITHDRAWNSet() {
        return WITHDRAWN;
    }

    public void addWITHDRAWN(IpPrefix net) {
        this.WITHDRAWN.add(net);
    }

    public void addWITHDRAWN(List<IpPrefix> prefixes) {
        this.WITHDRAWN.addAll(prefixes);
    }

    /* TYPE 3 NOTIFICATION */
    /* TYPE 3 NOTIFICATION */
    /* TYPE 3 NOTIFICATION */

    public int getERROR_CODE() {
        return ERROR_CODE;
    }

    public void setERROR_CODE(int ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE;
    }

    public int getSUB_ERROR_CODE() {
        return SUB_ERROR_CODE;
    }

    public void setSUB_ERROR_CODE(int SUB_ERROR_CODE) {
        this.SUB_ERROR_CODE = SUB_ERROR_CODE;
    }
    /* TYPE 4 KEEPALIVE */
    /* TYPE 4 KEEPALIVE */
    /* TYPE 4 KEEPALIVE */

}
