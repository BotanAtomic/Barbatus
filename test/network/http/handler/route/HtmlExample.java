package network.http.handler.route;

import network.http.handler.SimpleObject;
import org.barbatus.console.Console;
import org.barbatus.injector.annotation.Inject;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/test")
public class HtmlExample extends BarbatusHttpHandler {

    @Inject("myObject")
    private SimpleObject test;

    @Override
    public boolean filter(BarbatusHttpRequest request, BarbatusHttpResponse response) {
        Console.info(test.toString());
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

