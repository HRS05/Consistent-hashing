package com.hrs.service;


import java.util.Objects;
import java.nio.charset.StandardCharsets;


public class NodeService {
    private final String id;
    private final String host;
    private final String port;

    private final static MurMurHash murMurHash = new MurMurHash(8383, StandardCharsets.UTF_8);

    public static int hashKey(String key) {
        return murMurHash.getHash(key);
    }

    public NodeService(String id, String host, String port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    public  int getHashCode(String... values) {
        String key = this.id+this.host+this.port;
        for (String value : values)
            key += value;
        return murMurHash.getHash(key);
    }

    public String getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
