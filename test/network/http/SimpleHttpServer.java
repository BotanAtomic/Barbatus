package network.http;

import network.http.handler.JsonExample;
import network.http.handler.HtmlExample;
import org.barbatus.console.Console;
import org.barbatus.core.Barbatus;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.network.http.BarbatusHttpServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class SimpleHttpServer {


    public static void main(String[] args) throws Exception {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("Simple HTTP Server");

        BarbatusHttpServer server = BarbatusHttpServer
                .newInstance()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .enableCors(true)
                .setTimeout(3000)
                .setHandlers(JsonExample.class, HtmlExample.class);

        server.start();
    }


}
