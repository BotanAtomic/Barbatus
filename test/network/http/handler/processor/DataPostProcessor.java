package network.http.handler.processor;

import network.http.DefaultTransporter;
import org.barbatus.common.transformer.Transformer;

public class DataPostProcessor implements Transformer<DefaultTransporter, byte[]> {

    @Override
    public byte[] transform(DefaultTransporter input) {
        return null;
    }

}
