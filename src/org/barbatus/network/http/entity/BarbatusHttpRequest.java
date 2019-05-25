package org.barbatus.network.http.entity;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.barbatus.common.pair.StringPair;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.console.Console;
import org.barbatus.utils.URLUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class BarbatusHttpRequest {

    private final HttpExchange exchange;
    private final Transformer<BarbatusHttpRequest, ?> processor;

    public BarbatusHttpRequest(HttpExchange exchange, Transformer<BarbatusHttpRequest, ?> processor) {
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

    public StringPair getQuery(String name) {
        return getQuery().stream().filter(pair -> pair.getKey().equals(name))
                .findAny().orElse(new StringPair());
    }

    public Object getBody() {
        if (processor != null)
            return processor.transform(this);

        Console.warn(getClass().getSimpleName(), "you must define a pre-processor for use custom body");
        return null;
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
            int availableBytes = exchange.getRequestBody().available();

            if(availableBytes == 0) {
                return new byte[0];
            }

            byte[] data = new byte[availableBytes];
            if (exchange.getRequestBody().read(data) > 0)
                return data;
            else throw new IOException("Failed to read input stream");
        } catch (IOException e) {
            Console.error(e);
            return new byte[0];
        }
    }

    public Headers getHeaders() {
        return exchange.getRequestHeaders();
    }

    public void addHeader(String key, String value) {
        getHeaders().add(key, value);
    }

    public void resetHeader() {
        getHeaders().clear();
    }

    public boolean isHeaderPresent(String key) {
        return getHeaders().containsKey(key);
    }

}
