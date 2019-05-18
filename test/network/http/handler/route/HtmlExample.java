package network.http.handler.route;

import org.barbatus.network.http.annotations.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/test")
public class HtmlExample extends BarbatusHttpHandler {

    @Override
    public boolean filter(BarbatusHttpRequest request, BarbatusHttpResponse response) {
        if (request.getMethod().toLowerCase().equals("get")) {
            return true;
        } else {
            response.sendStatus(HttpStatus.NOT_FOUND);
            return false;
        }
    }

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        response.sendString("<html><b>Hello</b></html>");
    }

}

