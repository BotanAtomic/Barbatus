package org.barbatus.network.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.barbatus.annotations.PostProcessor;
import org.barbatus.annotations.PreProcessor;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.console.Console;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.annotations.HttpServer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;

public abstract class BarbatusHttpHandler implements HttpHandler {

    @HttpServer
    protected BarbatusHttpServer server;

    protected HttpExchange root;

    @PreProcessor()
    private Transformer<BarbatusHttpRequest, ?> preProcessor;

    @PostProcessor
    private Transformer<Object, byte[]> postProcessor;

    @Override
    public void handle(HttpExchange exchange) {
        try {
            this.root = exchange;
            BarbatusHttpRequest request = new BarbatusHttpRequest(exchange, preProcessor);
            BarbatusHttpResponse response = new BarbatusHttpResponse(exchange, postProcessor);
            handle(request, response);
        } catch (Exception e) {
            Console.error(e);
        }
    }

    public abstract void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception;

}
