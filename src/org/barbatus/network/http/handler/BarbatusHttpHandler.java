package org.barbatus.network.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.barbatus.console.Console;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.annotations.HttpServer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;

public abstract class BarbatusHttpHandler implements HttpHandler {

    @HttpServer
    protected BarbatusHttpServer server;

    @Override
    public void handle(HttpExchange exchange) {
        try {
            BarbatusHttpRequest request = new BarbatusHttpRequest(exchange, server.getPreProcessor());
            BarbatusHttpResponse response = new BarbatusHttpResponse();
            handle(request, response);
        } catch (Exception e) {
            Console.error(e);
        }
    }

    public abstract void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception;

}
