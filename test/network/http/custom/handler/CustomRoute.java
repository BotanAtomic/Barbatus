package network.http.custom.handler;

import network.http.custom.processor.CustomObject;
import network.http.custom.processor.InputDataProcessor;
import network.http.custom.processor.OutputDataProcessor;
import org.barbatus.annotation.InputProcessor;
import org.barbatus.annotation.OutputProcessor;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpMethod;
import org.barbatus.network.http.handler.BarbatusHttpHandler;
import org.barbatus.network.http.transformer.HttpInputTransformer;
import org.barbatus.network.http.transformer.HttpOutputTransformer;

@BarbatusRoute(value = "/custom", method = HttpMethod.POST)
public class CustomRoute extends BarbatusHttpHandler {

    @InputProcessor
    private HttpInputTransformer<CustomObject> inputProcessor;

    @OutputProcessor
    private HttpOutputTransformer<CustomObject> outputProcessor;

    public CustomRoute() {
        this.inputProcessor = new InputDataProcessor();
        this.outputProcessor = new OutputDataProcessor();
    }

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        CustomObject object = (CustomObject) request.getBody(); //use custom input processor

        response.send(object); // use custom output processor
    }

}
