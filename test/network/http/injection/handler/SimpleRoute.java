package network.http.injection.handler;

import network.http.injection.MyObject;
import org.barbatus.injector.annotation.Inject;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute("/simple")
public class SimpleRoute extends BarbatusHttpHandler {

    @Inject("myObject")
    private MyObject injectedObject;

    @Inject
    private String singletonClass;

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        response.sendString(injectedObject.getValue().concat(singletonClass), HttpStatus.OK);
    }

}
