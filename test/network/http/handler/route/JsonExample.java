package network.http.handler.route;

import org.barbatus.network.http.annotations.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;
import org.json.JSONObject;

@BarbatusRoute(value = "/hello", secure = true)
public class JsonExample extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        JSONObject object = request.getJsonObjectBody();

        response.sendJsonObject(object);
    }

}
