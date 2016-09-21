package com.mwzhang.bgp.core;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.net.InetAddress;
import java.util.*;

/**
 * Utility functions for Update objects, such as comparisons.
 * <p/>
 * Created by Mingwei Zhang on 4/24/14.
 */
public class UpdateUtils {

    /**
     * Compare two Updates to test if all the attributes are equal.
     *
     * @param u1 Update 1
     * @param u2 Update 2
     * @return true if every attribute of the two updates are the same.
     */
    public static boolean isEquals(Update u1, Update u2) {
        return Objects.equals(u1.getCollector(), u2.getCollector()) &&
                Objects.equals(u1.getPeerIp(), u2.getPeerIp()) &&
                Objects.equals(u1.getMessageType(), u2.getMessageType()) &&
                Objects.equals(u1.getBGP_ATTR().getORIGIN(), u2.getBGP_ATTR().getORIGIN()) &&
                Objects.equals(u1.getBGP_ATTR().getLOCAL_PREF(), u2.getBGP_ATTR().getLOCAL_PREF()) &&
                Objects.equals(u1.getBGP_ATTR().getCOMMUNITY(), u2.getBGP_ATTR().getCOMMUNITY()) &&
                Objects.equals(u1.getBGP_ATTR().isATOMIC_AGGREGATE(), u2.getBGP_ATTR().isATOMIC_AGGREGATE()) &&
                Objects.equals(u1.getBGP_ATTR().getAGGREGATOR_IP(), u2.getBGP_ATTR().getAGGREGATOR_IP()) &&
                Objects.equals(u1.getBGP_ATTR().getMULTI_EXIT_DISC(), u2.getBGP_ATTR().getMULTI_EXIT_DISC()) &&
                Objects.equals(u1.getBGP_ATTR().getAS_PATH(), u2.getBGP_ATTR().getAS_PATH()) &&
                Objects.equals(u1.getBGP_ATTR().getNEXT_HOP(), u2.getBGP_ATTR().getNEXT_HOP());
    }


    /**
     * Compare two Updates' AS_PATH and NEXT_HOP attribtues.
     *
     * @param u1 Update 1
     * @param u2 Update 2
     * @return true if two updates' AS_PATH and NEXT_HOP attributes are the same.
     */
    public static boolean hasSamePath(Update u1, Update u2) {
        return Objects.equals(u1.getBGP_ATTR().getAS_PATH(), u2.getBGP_ATTR().getAS_PATH()) &&
                Objects.equals(u1.getBGP_ATTR().getNEXT_HOP(), u2.getBGP_ATTR().getNEXT_HOP());

    }

    /**
     * Compare the attributes of Update 1 and 2 for equality, except AS_PATH and NEXT_HOP attributes.
     *
     * @param u1 Update 1
     * @param u2 Update 2
     * @return true if all attributes of the two updates are equal, not including AS_PATH and NEXT_HOP
     */
    public static boolean hasSameAttributes(Update u1, Update u2) {
        return Objects.equals(u1.getCollector(), u2.getCollector()) &&
                Objects.equals(u1.getPeerIp(), u2.getPeerIp()) &&
                Objects.equals(u1.getMessageType(), u2.getMessageType()) &&
                Objects.equals(u1.getBGP_ATTR().getORIGIN(), u2.getBGP_ATTR().getORIGIN()) &&
                Objects.equals(u1.getBGP_ATTR().getLOCAL_PREF(), u2.getBGP_ATTR().getLOCAL_PREF()) &&
                Objects.equals(u1.getBGP_ATTR().getCOMMUNITY(), u2.getBGP_ATTR().getCOMMUNITY()) &&
                Objects.equals(u1.getBGP_ATTR().isATOMIC_AGGREGATE(), u2.getBGP_ATTR().isATOMIC_AGGREGATE()) &&
                Objects.equals(u1.getBGP_ATTR().getAGGREGATOR_IP(), u2.getBGP_ATTR().getAGGREGATOR_IP()) &&
                Objects.equals(u1.getBGP_ATTR().getMULTI_EXIT_DISC(), u2.getBGP_ATTR().getMULTI_EXIT_DISC());

    }

    public static boolean isAADup(Update u1, Update u2) {
        // NOTE: here we use Objects.equals function to deal with null objects.
        return Objects.equals(u1.getBGP_ATTR().getAS_PATH(), u2.getBGP_ATTR().getAS_PATH()) &&
                Objects.equals(u1.getBGP_ATTR().getNEXT_HOP(), u2.getBGP_ATTR().getNEXT_HOP()) &&
                Objects.equals(u1.getBGP_ATTR().getMULTI_EXIT_DISC(), u2.getBGP_ATTR().getMULTI_EXIT_DISC()) &&
                Objects.equals(u1.getBGP_ATTR().getCOMMUNITY(), u2.getBGP_ATTR().getCOMMUNITY());
    }

    public static void removeResetPrefixes(Update u, HashSet<IpPrefix> prefixes) {
        // Below is a stupid bug.
        //List<String> new_subnets = new ArrayList<String>(u.getNLRISet());
        Set<IpPrefix> new_subnets = new HashSet<>();
        for (IpPrefix subnet : u.getNLRISet()) {
            if (!prefixes.contains(subnet)) {
                new_subnets.add(subnet);
            }
        }
        u.setNLRI(new_subnets);
    }

    public static boolean hasPrefixAnnounce(Update u, IpPrefix p) {
        return u.getNLRISet().contains(p);
    }

    public static boolean hasPrefix(Update u, String p) {
        return (u.getNLRISet().contains(p) || u.getWITHDRAWNSet().contains(p));
    }

    /*
    public static boolean hasSubPrefix(Update u, String p) {
        SubnetUtils net = new SubnetUtils(p);
        for (String prefix : u.getNLRISet()) {
            SubnetUtils s = new SubnetUtils(prefix);
            s.setInclusiveHostCount(true);
            if (net.getInfo().isInRange(s.getInfo().getLowAddress()) && net.getInfo().isInRange(s.getInfo().getHighAddress())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasWithdrawSubPrefix(Update u, IpPrefix p) {
        SubnetUtils net = new SubnetUtils(p);
        for (IpPrefix prefix : u.getWITHDRAWNSet()) {
            SubnetUtils s = new SubnetUtils(prefix);
            s.setInclusiveHostCount(true);
            if (net.getInfo().isInRange(s.getInfo().getLowAddress()) && net.getInfo().isInRange(s.getInfo().getHighAddress())) {
                return true;
            }
        }
        return false;
    }
    */


    /**
     * Split a list of Update objects into lists of them organized by the peers they're received from.
     * Each peer is represented by the String of its IP address.
     * Each list of Update objects will be mapped to the corresponding peer.
     *
     * @param updates The list of the BGP Update objects, all mixed together, raw data from the collector.
     * @return A HashMap with key to be the String of peer's IP address, and value to be the list of Update objects.
     */
    public static Map<InetAddress, List<Update>> splitUpdatesByPeers(List<Update> updates, List<InetAddress> viewpointIps) {
        Map<InetAddress, List<Update>> peerUpdates = new HashMap<>();
        for (Update u : updates) {
            InetAddress peer = u.getPeerIp();
            String peerAddr = peer.getHostAddress();
            if (!viewpointIps.isEmpty()) {
                boolean exists = false;
                for (InetAddress addr : viewpointIps) {
                    if (peerAddr.equals(addr.getHostAddress())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    // Skip non-matching peers
                    //Output.pl("no matching peers!");
                    continue;
                }
            }
            if (!peerUpdates.containsKey(peer)) {
                peerUpdates.put(peer, new ArrayList<Update>());
            }
            peerUpdates.get(peer).add(u);
        }
        return peerUpdates;
    }

    /**
     * Count the prefixes and fill in the map of <Prefix,Count>
     *
     * @param updates      the set of total prefixes
     * @param prefixCounts the map that stores prefix name and count.
     */
    public static void countPrefixes(List<Update> updates, Map<IpPrefix, Integer> prefixCounts) {

        for (Update u : updates) {
            Set<IpPrefix> prefixes = u.getNLRISet();
            for (IpPrefix p : prefixes) {
                if (!prefixCounts.containsKey(p))
                    prefixCounts.put(p, 0);
                prefixCounts.put(p, prefixCounts.get(p) + 1);
            }
        }
    }

    /**
     * Using HashSet to get a unique set of prefixes from the list of updates
     *
     * @param updates the total set of updates
     * @param hashSet the HashSet that stores the unique prefixes set
     */
    public static void hashUniquePrefixes(List<Update> updates, HashSet<IpPrefix> hashSet) {

        for (Update u : updates) {
            Set<IpPrefix> prefixes = u.getNLRISet();
            for (IpPrefix p : prefixes) {
                hashSet.add(p);
            }
        }
    }

    /**
     * Get a list of lists, where each one row represents a path that does not have consecutive repeating items.
     *
     * @param updates the total set of BGP updates
     * @return a list of lists, each entry contains the simplified AS path
     */
    public static List<List<Integer>> getSimplifiedPaths(List<Update> updates) {

        List<List<Integer>> lists = new ArrayList<>();
        for (Update u : updates) {
            List<Integer> path = u.getBGP_ATTR().getAS_PATH();
            if (path.size() == 0) {
                continue;
            }
            List<Integer> newpath = new ArrayList<>();
            int last = -1;
            for (int as : path) {
                if (as != last) {
                    // not repeating
                    newpath.add(as);
                    last = as;
                }
            }
            if (newpath.size() != 0) {
                lists.add(newpath);
            }
        }

        return lists;
    }

    public static List<Update> filterUpdatesByTime(List<Update> updates, DateTime start, DateTime end) {
        List<Update> resUpdates = new ArrayList<>();
        for (Update u : updates) {
            if (updateWithinRange(u, start, end)) {
                resUpdates.add(u);
            }
        }

        return resUpdates;
    }

    public static boolean updateWithinRange(Update update, DateTime start, DateTime end) {
        DateTime uTime = new DateTime(update.getTimestamp(), DateTimeZone.UTC);

        return uTime.isAfter(start) && uTime.isBefore(end);

    }

}
