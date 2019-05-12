package network.http.handler;

import network.http.DefaultTransporter;
import network.http.handler.processor.DataPostProcessor;
import network.http.handler.processor.DataPreProcessor;
import org.barbatus.annotations.PostProcessor;
import org.barbatus.annotations.PreProcessor;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.annotations.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/processor")
public class ProcessorExample extends BarbatusHttpHandler {

    @PreProcessor
    private Transformer<BarbatusHttpHandler, DefaultTransporter> preProcessor = new DataPreProcessor();

    @PostProcessor
    private Transformer<DefaultTransporter, byte[]> postProcessor = new DataPostProcessor();

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        response.sendString("<html><b>Hello</b></html>");
    }

}
