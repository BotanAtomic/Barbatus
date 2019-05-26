package network.http.custom.processor;

import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;

public class InputDataProcessor implements Transformer<BarbatusHttpRequest, CustomObject> {

    @Override
    public CustomObject transform(BarbatusHttpRequest input) {
        return new CustomObject(input.getStringBody().concat("/CUSTOM"));
    }

}
