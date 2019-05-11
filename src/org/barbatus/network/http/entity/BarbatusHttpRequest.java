package org.barbatus.network.http.entity;

import com.sun.net.httpserver.HttpExchange;
import org.barbatus.common.StringPair;
import org.barbatus.console.Console;
import org.barbatus.network.http.processor.BarbatusHttpProcessor;
import org.barbatus.utils.URLUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class BarbatusHttpRequest {

    private final HttpExchange exchange;
    private final BarbatusHttpProcessor processor;

    public BarbatusHttpRequest(HttpExchange exchange, BarbatusHttpProcessor processor) {
        this.exchange = exchange;
        this.processor = processor;
    }

    public InetSocketAddress getLocalAddress() {
        return exchange.getLocalAddress();
    }

    public InetSocketAddress getRemoteAddress() {
        return exchange.getRemoteAddress();
    }

    public String getProtocol() {
        return exchange.getProtocol();
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }

    public List<StringPair> getQuery() {
        return URLUtils.parseQuery(exchange.getRequestURI().getRawQuery());
    }

    public Object getBody() {
        return processor.transform(this);
    }

    public JSONObject getJsonObjectBody() {
        return new JSONObject(getStringBody());
    }

    public JSONArray getJsonArrayBody() {
        return new JSONArray(getStringBody());
    }

    public String getStringBody() {
        return new String(getBinaryBody());
    }

    public InputStream getStreamBody() {
        return exchange.getRequestBody();
    }

    public byte[] getBinaryBody() {
        try {
            byte[] data = new byte[exchange.getRequestBody().available()];
            if (exchange.getRequestBody().read(data) > 0)
                return data;
            else throw new IOException("Failed to read input stream");
        } catch (IOException e) {
            Console.error(e);
            return new byte[0];
        }
    }

}
