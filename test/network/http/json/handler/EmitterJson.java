package network.http.json.handler;

import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;
import org.json.JSONObject;

@BarbatusRoute("/json/emitter")
public class EmitterJson extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        JSONObject object = new JSONObject();
        object.put("key", "value");
        response.sendJsonObject(object, HttpStatus.OK);
    }

}
