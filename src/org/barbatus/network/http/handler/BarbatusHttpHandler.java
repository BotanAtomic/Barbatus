package org.barbatus.network.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.annotations.HttpServer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;

import java.io.IOException;

public abstract class BarbatusHttpHandler implements HttpHandler {

    @HttpServer
    protected BarbatusHttpServer server;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        BarbatusHttpRequest request = new BarbatusHttpRequest(exchange, server.getPreProcessor());
        BarbatusHttpResponse response = new BarbatusHttpResponse();

        handle(request, response);
    }

    public abstract void handle(BarbatusHttpRequest request, BarbatusHttpResponse response);


}
