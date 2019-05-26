package network.http.custom.handler;

import network.http.custom.processor.CustomObject;
import network.http.custom.processor.InputDataProcessor;
import network.http.custom.processor.OutputDataProcessor;
import org.barbatus.annotation.InputProcessor;
import org.barbatus.annotation.OutputProcessor;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpMethod;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/custom", method = HttpMethod.POST)
public class CustomRoute extends BarbatusHttpHandler {

    /**
     * Processors must be defined on runtime (for dependency injection)
     */

    @InputProcessor
    private Transformer<BarbatusHttpRequest, ?> inputProcessor = new InputDataProcessor();

    @OutputProcessor
    private Transformer<?, byte[]> outputProcessor = new OutputDataProcessor();

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        CustomObject object = (CustomObject) request.getBody();

        response.send(object);
    }

}
