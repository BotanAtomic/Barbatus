package network.http.authenticator.handler;

import network.http.authenticator.token.BasicTokenManager;
import org.barbatus.injector.annotation.Inject;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.enums.HttpStatus;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

@BarbatusRoute(value = "/login")
public class LoginRoute extends BarbatusHttpHandler {

    @Inject
    private BasicTokenManager tokenManager;

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        //your logic login here
        //generate new token

        response.sendString(tokenManager.generateToken((short) 10), HttpStatus.OK);
    }

}
