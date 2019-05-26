package network.http.custom.processor;

import org.barbatus.common.transformer.Transformer;

public class OutputDataProcessor implements Transformer<CustomObject, byte[]> {

    @Override
    public byte[] transform(CustomObject input) {
        return input.getData().getBytes();
    }

}
