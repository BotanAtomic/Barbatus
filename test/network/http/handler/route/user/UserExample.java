package network.http.handler.route.user;

import org.barbatus.network.http.annotations.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/user")
public class UserExample extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {

    }

}
