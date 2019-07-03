package org.barbatus.network.http.entity;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.barbatus.console.Console;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.transformer.HttpOutputTransformer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class BarbatusHttpResponse {

    private final HttpExchange exchange;
    private final HttpOutputTransformer<Object> processor;

    public BarbatusHttpResponse(HttpExchange exchange, HttpOutputTransformer<Object> processor) {
        this.exchange = exchange;
        this.processor = processor;
    }

    public void sendJsonArray(JSONArray json) {
        sendJsonArray(json, HttpStatus.OK);
    }

    public void sendJsonArray(JSONArray json, HttpStatus status) {
        sendJsonArray(json, status.code());
    }

    public void sendJsonArray(JSONArray json, int code) {
        addHeader("Content-Type", "application/json");
        sendString(json.toString(), Charset.defaultCharset(), code);
    }

    public void sendJsonObject(JSONObject json) {
        sendJsonObject(json, HttpStatus.OK);
    }

    public void sendJsonObject(JSONObject json, HttpStatus status) {
        sendJsonObject(json, status.code());
    }

    public void sendJsonObject(JSONObject json, int code) {
        addHeader("Content-Type", "application/json");
        sendString(json.toString(), Charset.defaultCharset(), code);
    }

    public void sendFile(File file) {
        sendFile(file, HttpStatus.OK);
    }

    public void sendFile(File file, HttpStatus status) {
        sendFile(file, status.code());
    }

    public void sendFile(File file, int code) {
        if (file == null || !file.exists())
            return;

        try {
            sendString(new String(Files.readAllBytes(file.toPath())), code);
        } catch (IOException e) {
            Console.error(getClass().getSimpleName(), e);
        }
    }

    public void sendString(String s) {
        sendString(s, Charset.defaultCharset(), HttpStatus.OK);
    }

    public void sendString(String s, HttpStatus status) {
        sendString(s, Charset.defaultCharset(), status.code());
    }

    public void sendString(String s, int code) {
        sendString(s, Charset.defaultCharset(), code);
    }

    public void sendString(String s, Charset charset) {
        sendString(s, charset, HttpStatus.OK);
    }

    public void sendString(String s, Charset charset, HttpStatus status) {
        sendString(s, charset, status.code());
    }

    public void sendString(String s, Charset charset, int code) {
        sendBinary(s.getBytes(charset), code);
    }

    public void sendBinary(byte[] bytes, HttpStatus status) {
        sendBinary(bytes, status.code());
    }

    public void sendBinary(byte[] bytes, int code) {
        try {
            exchange.sendResponseHeaders(code, bytes.length);
            OutputStream stream = exchange.getResponseBody();
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            Console.error(getClass().getSimpleName(), e);
        }
    }

    public void send(Object object) {
        send(object, HttpStatus.OK);
    }

    public void send(Object object, HttpStatus status) {
        send(object, status.code());
    }

    public void send(Object object, int code) {
        byte[] bytes;

        if (processor != null)
            bytes = processor.transform(object);
        else {
            Console.warn(getClass().getSimpleName(), "you must define a post-processor for use custom body");
            bytes = new byte[0];
        }
        sendBinary(bytes, code);
    }

    public Headers getHeaders() {
        return exchange.getResponseHeaders();
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


    public void sendHeaders(HttpStatus status) {
        sendHeaders(status.code(), -1);
    }

    public void sendHeaders(int code) {
        sendHeaders(code, -1);
    }

    public void sendHeaders(HttpStatus status, int responseLength) {
        sendHeaders(status.code(), responseLength);
    }

    public void sendHeaders(int code, int responseLength) {
        try {
            exchange.sendResponseHeaders(code, responseLength);
        } catch (IOException e) {
            Console.error(getClass().getSimpleName(), e);
        }
    }

    public void sendStatus(HttpStatus status) {
        sendStatus(status.code());
    }

    public void sendStatus(int code) {
        try {
            exchange.sendResponseHeaders(code, -1);
            getStream().close();
        } catch (IOException e) {
            Console.error(getClass().getSimpleName(), e);
        }
    }


    public OutputStream getStream() {
        return exchange.getResponseBody();
    }

}
