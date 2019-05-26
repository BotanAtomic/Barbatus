package network.http.authenticator;

import network.http.authenticator.handler.LoginRoute;
import network.http.authenticator.handler.SecureRoute;
import network.http.authenticator.token.BasicTokenManager;
import org.barbatus.console.Console;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.core.Barbatus;
import org.barbatus.exception.BarbatusException;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.authenticator.BarbatusAuthenticator;
import org.barbatus.network.http.enums.HttpStatus;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class SecureHttpServer {


    public static void main(String[] args) throws BarbatusException, IOException {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("Secure HTTP Server");

        BasicTokenManager tokenManager = new BasicTokenManager();

        BarbatusAuthenticator<BasicTokenManager> authenticator = new BarbatusAuthenticator<BasicTokenManager>()
                        .onAuthenticate((request, tokeManager) -> {
                            String token = request.getHeaders().getFirst("token");
                            return token != null && tokeManager.isValidToken(token);
                        }, tokenManager)
                        .onFail((response -> response.sendStatus(HttpStatus.UNAUTHORIZED)));

        new BarbatusHttpServer()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .setAuthenticator(authenticator)
                .inject(tokenManager)
                .setHandlers(SecureRoute.class, LoginRoute.class)
                .start();
    }


}
