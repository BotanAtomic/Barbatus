package network.http.json.handler;

import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;
import org.json.JSONObject;

@BarbatusRoute("/json/echo")
public class EchoJson extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        JSONObject object = request.getJsonObjectBody();

        if (object != null)
            response.sendJsonObject(object);
        else
            response.sendStatus(HttpStatus.BAD_REQUEST);
    }

}
