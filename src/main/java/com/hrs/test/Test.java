package com.hrs.test;

import com.hrs.bloom.Bloom;
import com.hrs.hasher.ConsistentHashRouter;
import com.hrs.service.NodeService;

import java.util.*;

public class Test {
    public Test() {
    }


    public void test() {
        NodeService ns1 = new NodeService("Node_1", "127.0.0.1", "8080");
        NodeService ns2 = new NodeService("Node_2", "193.4.6.2", "8001");
        NodeService ns3 = new NodeService("Node_3", "140.0.1.6", "7070");
        NodeService ns4 = new NodeService("Node_4", "177.0.9.1", "5001");

        ConsistentHashRouter<NodeService> router = new ConsistentHashRouter<>(Arrays.asList(ns1, ns2, ns3, ns4), 20);
        List<String> keys = Arrays.asList(
                "198.0.4.8", "190.0.1.7", "192.168.1.1", "172.16.0.2", "10.0.0.3",
                "203.0.113.5", "198.51.100.6", "203.0.113.7", "198.51.100.8", "172.16.254.1",
                "10.0.0.10", "192.168.0.11", "198.51.100.12", "203.0.113.13", "198.51.100.14",
                "192.0.2.15", "203.0.113.16", "198.51.100.17", "203.0.113.18", "192.0.2.19"
        );
        for (String key : keys)
            System.out.println(key + " is routed to " + router.getRoute(key).getId());
    }


}
