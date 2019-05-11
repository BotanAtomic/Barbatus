package network.http.handler;

import org.barbatus.common.StringPair;
import org.barbatus.console.Console;
import org.barbatus.network.http.annotations.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/hello")
public class HelloHandler extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        for (StringPair stringPair : request.getQuery()) {
            Console.info(String.format("%s / %s", stringPair.getKey(), stringPair.getValue()));
        }
    }

}
