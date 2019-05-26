package network.http.custom;

import network.http.custom.handler.CustomRoute;
import org.barbatus.console.Console;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.core.Barbatus;
import org.barbatus.exception.BarbatusException;
import org.barbatus.network.http.BarbatusHttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class CustomHttpServer {


    public static void main(String[] args) throws BarbatusException, IOException {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("Custom HTTP Server");

        new BarbatusHttpServer()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .setHandlers(CustomRoute.class)
                .start();
    }


}
