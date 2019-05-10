package org.barbatus.network.http;

import org.barbatus.network.http.handler.BarbatusHttpHandler;
import org.barbatus.network.http.processor.BarbatusHttpProcessor;

import java.util.ArrayList;
import java.util.List;

public class BarbatusHttpServer {

    private int port = 80;
    private int timeout = 120;
    private boolean enableCors = false;

    private List<BarbatusHttpHandler> handlers = new ArrayList<>();

    private BarbatusHttpProcessor<?> preProcessor;

    private BarbatusHttpServer() {

    }

    public static BarbatusHttpServer newInstance() {
        return new BarbatusHttpServer();
    }

    public int getPort() {
        return port;
    }


    public BarbatusHttpServer setPort(int port) {
        this.port = port;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public BarbatusHttpServer setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public boolean isCorsEnabled() {
        return enableCors;
    }

    public BarbatusHttpServer enableCors(boolean enableCors) {
        this.enableCors = enableCors;
        return this;
    }

    public BarbatusHttpServer setPreProcessor(BarbatusHttpProcessor<?> preProcessor) {
        this.preProcessor = preProcessor;
        return this;
    }

    public BarbatusHttpProcessor<?> getPreProcessor() {
        return preProcessor;
    }


    public List<BarbatusHttpHandler> getHandlers() {
        return handlers;
    }
}
