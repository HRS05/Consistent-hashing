package com.hrs.hasher;

import com.hrs.service.NodeService;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashRouter<T extends NodeService> {
    private final SortedMap<Integer, T> ring = new TreeMap<>();
    private final int replicationFactorForNodes;

    public ConsistentHashRouter(int replicationFactorForNodes) {
        this.replicationFactorForNodes = replicationFactorForNodes;
    }

    public ConsistentHashRouter(List<T> nodes, int replicationFactorForNodes) {
        this.replicationFactorForNodes = replicationFactorForNodes;
        for (T node : nodes) {
            this.add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < this.replicationFactorForNodes; i++) {
            int hash = node.getHashCode(Integer.toString(i));
            ring.put(hash, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < this.replicationFactorForNodes; i++) {
            int hash = node.getHashCode(Integer.toString(i));
            ring.remove(hash);
        }
    }

    public T getRoute(String key) {
        if (ring.isEmpty()) return null;

        int hashKey = NodeService.hashKey(key);
        // return values whose keys are greater than ot equal to hashKey
        SortedMap<Integer, T> tailMap = ring.tailMap(hashKey);
        Integer nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(nodeHash);
    }

}
