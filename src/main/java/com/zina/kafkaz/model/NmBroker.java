package com.zina.kafkaz.model;

/**
 * @author : lacinazina
 * @created : 12/28/20, Monday
 **/
public class NmBroker {
    private final int id;
    private final String host;
    private final int port;
    private final boolean controller;
    private final String rack;

    public NmBroker(int id, String host, int port, String rack, boolean controller) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.rack = rack;
        this.controller = controller;
    }

    public int getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getRack() {
        return rack;
    }

    public boolean isController() {
        return controller;
    }
}
