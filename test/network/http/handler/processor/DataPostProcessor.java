package network.http.handler.processor;

import network.http.DefaultTransporter;
import org.barbatus.common.transformer.Transformer;

public class DataPostProcessor implements Transformer<DefaultTransporter, byte[]> {

    @Override
    public byte[] transform(DefaultTransporter input) {
        StringBuilder builder = new StringBuilder()
                .append("Method : ").append(input.getMethod()).append('\n')
                .append("Protocol : ").append(input.getProtocol()).append('\n')
                .append("Queries : \n");

        input.getQueries().forEach(query -> builder.append(query.getKey()).append('/')
                .append(query.getValue()).append('\n'));

        return builder.toString().getBytes();
    }

}
