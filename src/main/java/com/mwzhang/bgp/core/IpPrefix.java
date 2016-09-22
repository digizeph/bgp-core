package com.mwzhang.bgp.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Mingwei Zhang on 3/26/15.
 * IP Prefix class
 */
public class IpPrefix {

    public InetAddress ipAddress;
    public int prefixLength;
    public int prefixType;

    public CIDRUtils subnetUtils;

    public IpPrefix(InetAddress ipAddress, int prefixLength) {
        this.ipAddress = ipAddress;
        this.prefixLength = prefixLength;
        prefixType = ipAddress.getAddress().length == 4 ? 1 : 2;    // IPv4 - 1, IPv6 - 2
        try {
            subnetUtils = new CIDRUtils(String.format("%s/%d", ipAddress.getHostAddress(), prefixLength));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //subnetUtils.setInclusiveHostCount(true);
    }

    public IpPrefix(String s, int prefixLength) {
        try {
            this.ipAddress = InetAddress.getByName(s);
            this.prefixLength = prefixLength;
            prefixType = ipAddress.getAddress().length == 4 ? 1 : 2;    // IPv4 - 1, IPv6 - 2
            subnetUtils = new CIDRUtils(String.format("%s/%d", ipAddress.getHostAddress(), prefixLength));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /**
     * Determine if the current prefix is a super-prefix of the given prefix.
     * If the given prefix's low and high addresses are all in the range of the current prefix,
     * then the given prefix is part of the current prefix,
     * i.e. the current prefix is a super-prefix of the given prefix
     * <p>
     * Note: will return false if given a same prefix
     *
     * @param prefix given prefix
     * @return true if the current prefix is a super-prefix of the given prefix.
     */
    public boolean isSuperPrefixOf(IpPrefix prefix) {
        if (this.equals(prefix))
            return false;

        CIDRUtils info1 = this.subnetUtils;
        CIDRUtils info2 = prefix.subnetUtils;

        boolean result;
        try {
            result = info1.isInRange(info2.getNetworkAddress()) && info1.isInRange(info2.getBroadcastAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    /**
     * Determine if the current prefix is a super-prefix of the given prefix.
     * If the current prefix's low and high addresses are all in the range of the given prefix,
     * then the current prefix is part of the given prefix,
     * i.e. the current prefix is a sub-prefix of the given prefix
     * <p>
     * Note: will return false if given a same prefix
     *
     * @param prefix given prefix
     * @return true if the current prefix is a sub-prefix of the given prefix.
     */
    public boolean isSubPrefixOf(IpPrefix prefix) {
        if (this.equals(prefix))
            return false;

        CIDRUtils info1 = this.subnetUtils;
        CIDRUtils info2 = prefix.subnetUtils;

        boolean result;
        try {
            result = info2.isInRange(info1.getNetworkAddress()) && info2.isInRange(info1.getBroadcastAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s/%d", this.ipAddress.getHostAddress(), this.prefixLength);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(ipAddress)
                .append(prefixLength)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IpPrefix))
            return false;
        if (obj == this)
            return true;

        IpPrefix rhs = (IpPrefix) obj;
        return new EqualsBuilder().
                append(ipAddress, rhs.ipAddress).
                append(prefixLength, rhs.prefixLength).
                isEquals();
    }
}
