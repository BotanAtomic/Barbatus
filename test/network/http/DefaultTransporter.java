package network.http;

import org.barbatus.common.pair.StringPair;

import java.util.List;

public class DefaultTransporter {
    private List<StringPair> queries;
    private String method, protocol;
    private String body;

    public DefaultTransporter(List<StringPair> queries, String method, String protocol, String body) {
        this.queries = queries;
        this.method = method;
        this.protocol = protocol;
        this.body = body;
    }

    public List<StringPair> getQueries() {
        return queries;
    }

    public String getMethod() {
        return method;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getBody() {
        return body;
    }
}
