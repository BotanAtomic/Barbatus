package org.barbatus.network.http.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.barbatus.annotation.InputProcessor;
import org.barbatus.annotation.OutputProcessor;
import org.barbatus.console.Console;
import org.barbatus.injector.annotation.Inject;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.annotation.HttpServer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpMethod;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.filter.Filter;
import org.barbatus.network.http.transformer.HttpInputTransformer;
import org.barbatus.network.http.transformer.HttpOutputTransformer;

public abstract class BarbatusHttpHandler implements HttpHandler, Filter {

    @HttpServer
    protected BarbatusHttpServer server;

    private HttpExchange root;

    @InputProcessor
    private HttpInputTransformer<?> inputProcessor;

    @OutputProcessor
    private HttpOutputTransformer<Object> outputProcessor;

    @Inject
    private boolean secure;

    @Inject
    private HttpMethod method;

    @Override
    public void handle(HttpExchange exchange) {
        try {
            this.root = exchange;
            BarbatusHttpRequest request = new BarbatusHttpRequest(exchange, inputProcessor);
            BarbatusHttpResponse response = new BarbatusHttpResponse(exchange, outputProcessor);

            if (server.isCorsEnabled()) {
                Headers headers = response.getHeaders();
                headers.add("Access-Control-Allow-Methods", "*");
                headers.add("Access-Control-Allow-Origin", "*");
            }

            if (secure && server.getAuthenticator() != null && !server.getAuthenticator().authenticate(request, response)) {
                response.sendStatus(HttpStatus.UNAUTHORIZED);
                return;
            }

            if (method != HttpMethod.ANY && !method.is(request.getMethod())) {
                response.sendStatus(HttpStatus.NOT_FOUND);
                return;
            }

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