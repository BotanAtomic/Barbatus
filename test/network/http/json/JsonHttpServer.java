package network.http.json;

import network.http.simple.handler.SimpleRoute;
import network.http.simple.handler.SimpleRoute2;
import org.barbatus.console.Console;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.core.Barbatus;
import org.barbatus.exception.BarbatusException;
import org.barbatus.network.http.BarbatusHttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class JsonHttpServer {

    public static void main(String[] args) throws BarbatusException, IOException {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("JSON HTTP Server");

        new BarbatusHttpServer()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .setHandlers(SimpleRoute.class, SimpleRoute2.class)
                .start();
    }

}
