package network.http.custom.processor;

import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.transformer.HttpInputTransformer;

public class InputDataProcessor implements HttpInputTransformer<CustomObject> {

    @Override
    public CustomObject transform(BarbatusHttpRequest input) {
        return new CustomObject(input.getStringBody().concat("/CUSTOM"));
    }

}
