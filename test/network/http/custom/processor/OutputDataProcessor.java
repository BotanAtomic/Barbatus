package network.http.custom.processor;

import org.barbatus.network.http.transformer.HttpOutputTransformer;

public class OutputDataProcessor implements HttpOutputTransformer<CustomObject> {

    @Override
    public byte[] transform(CustomObject input) {
        return input.getData().getBytes();
    }

}
