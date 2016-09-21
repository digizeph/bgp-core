package com.mwzhang.bgp.core;

import com.google.common.base.Joiner;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mingwei Zhang on 8/16/2015.
 */
public class BgpAttributes {
    /**
     * Attributes
     * [2]: http://tools.ietf.org/html/rfc4271#section-4.3
     */
    private int ORIGIN; // 0-IGP, 1-EGP, 2-INCOMPLETE
    private InetAddress NEXT_HOP;
    private int MULTI_EXIT_DISC;
    private int LOCAL_PREF;
    private boolean ATOMIC_AGGREGATE = false;   // length 0, defined in ref[2] 5.1.6
    private int AGGREGATOR_ASN; // length 6, last ASN that formed aggregate route, followed by the IP of that router.
    private InetAddress AGGREGATOR_IP;
    private List<Integer> AS_PATH = new ArrayList<>();
    private Set<Integer> COMMUNITY = new HashSet<>();

    public int getORIGIN() {
        return ORIGIN;
    }

    public void setORIGIN(int ORIGIN) {
        this.ORIGIN = ORIGIN;
    }

    public InetAddress getNEXT_HOP() {
        return NEXT_HOP;
    }

    public void setNEXT_HOP(InetAddress NEXT_HOP) {
        this.NEXT_HOP = NEXT_HOP;
    }

    public int getMULTI_EXIT_DISC() {
        return MULTI_EXIT_DISC;
    }

    public void setMULTI_EXIT_DISC(int MULTI_EXIT_DISC) {
        this.MULTI_EXIT_DISC = MULTI_EXIT_DISC;
    }

    public int getLOCAL_PREF() {
        return LOCAL_PREF;
    }

    public void setLOCAL_PREF(int LOCAL_PREF) {
        this.LOCAL_PREF = LOCAL_PREF;
    }

    public boolean isATOMIC_AGGREGATE() {
        return ATOMIC_AGGREGATE;
    }

    public void setATOMIC_AGGREGATE(boolean ATOMIC_AGGREGATE) {
        this.ATOMIC_AGGREGATE = ATOMIC_AGGREGATE;
    }

    public int getAGGREGATOR_ASN() {
        return AGGREGATOR_ASN;
    }

    public void setAGGREGATOR_ASN(int AGGREGATOR_ASN) {
        this.AGGREGATOR_ASN = AGGREGATOR_ASN;
    }

    public InetAddress getAGGREGATOR_IP() {
        return AGGREGATOR_IP;
    }

    public void setAGGREGATOR_IP(InetAddress AGGREGATOR_IP) {
        this.AGGREGATOR_IP = AGGREGATOR_IP;
    }

    public Set<Integer> getCOMMUNITY() {
        return COMMUNITY;
    }

    public void addCOMMUNITY(int c) {
        this.COMMUNITY.add(c);
    }

    public String getASPathString() {
        Joiner joiner = Joiner.on(",");
        return joiner.join(this.getAS_PATH()).intern();
    }

    public List<Integer> getAS_PATH() {
        return AS_PATH;
    }

    public void setAS_PATH(List<Integer> AS_PATH) {
        this.AS_PATH = AS_PATH;
    }

    public void addAS_PATH(int path) {
        if (path != 0) {// adjustment for 4 byte AS
            if (this.AS_PATH.size() == 0 || this.AS_PATH.get(this.AS_PATH.size() - 1) != path)
                this.AS_PATH.add(path);
        }
    }

}
