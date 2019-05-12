package network.http.handler.processor;

import network.http.DefaultTransporter;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;

public class DataPreProcessor implements Transformer<BarbatusHttpRequest, DefaultTransporter> {

    @Override
    public DefaultTransporter transform(BarbatusHttpRequest request) {
        return new DefaultTransporter(request.getQuery(), request.getMethod(),
                request.getProtocol(), request.getStringBody());
    }

}
