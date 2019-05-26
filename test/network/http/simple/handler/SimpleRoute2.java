package network.http.simple.handler;

import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute("/simple2")
public class SimpleRoute2 extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        response.sendStatus(HttpStatus.OK);
    }

}
