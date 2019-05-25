package network.http.handler.route;

import network.http.handler.processor.DataPostProcessor;
import network.http.handler.processor.DataPreProcessor;
import org.barbatus.annotation.OutputProcessor;
import org.barbatus.annotation.InputProcessor;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/processor")
public class ProcessorExample extends BarbatusHttpHandler {

    @InputProcessor
    private Transformer preProcessor = new DataPreProcessor();

    @OutputProcessor
    private Transformer postProcessor = new DataPostProcessor();

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        response.send(request.getBody());
    }

}
