package org.barbatus.network.http.enums;

public enum HttpMethod {
    GET,
    HEAD,
    POST,
    PUT,
    DELETE,
    CONNECT,
    OPTIONS,
    TRACE,
    ANY;

    public boolean is(String method) {
        return name().toLowerCase().equals(method.toLowerCase());
    }
}
