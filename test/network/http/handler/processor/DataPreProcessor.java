package network.http.handler.processor;

import network.http.DefaultTransporter;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

public class DataPreProcessor implements Transformer<BarbatusHttpHandler, DefaultTransporter> {

    @Override
    public DefaultTransporter transform(BarbatusHttpHandler input) {
        return null;
    }

}
