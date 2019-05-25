package network.http.handler.route.user.get;

import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/get")
public class UserGet extends BarbatusHttpHandler {

    @Override
    public boolean filter(BarbatusHttpRequest request, BarbatusHttpResponse response) {
        if (request.getMethod().toLowerCase().equals("get")) {
            return true;
        } else {
            response.sendHeaders(404);
            return false;
        }
    }

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {

    }

}
