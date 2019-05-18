package org.barbatus.network.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.barbatus.annotations.InputProcessor;
import org.barbatus.annotations.OutputProcessor;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.console.Console;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.annotations.HttpServer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.filter.Filter;

public abstract class BarbatusHttpHandler implements HttpHandler, Filter {

    @HttpServer
    protected BarbatusHttpServer server;

    private HttpExchange root;

    @InputProcessor
    private Transformer<BarbatusHttpRequest, ?> inputProcessor;

    @OutputProcessor
    private Transformer<Object, byte[]> outputProcessor;

    @Override
    public void handle(HttpExchange exchange) {
        try {
            this.root = exchange;
            BarbatusHttpRequest request = new BarbatusHttpRequest(exchange, inputProcessor);
            BarbatusHttpResponse response = new BarbatusHttpResponse(exchange, outputProcessor);

            if (filter(request, response))
                handle(request, response);
        } catch (Exception e) {
            Console.error(e);
        }
    }

    @Override
    public boolean filter(BarbatusHttpRequest request, BarbatusHttpResponse response) {
        return true;
    }

    public abstract void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception;

    public HttpExchange getRoot() {
        return root;
    }
}