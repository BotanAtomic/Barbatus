package network.http.html;

import network.http.html.handler.HtmlFileRoute;
import network.http.html.handler.RawHtmlRoute;
import org.barbatus.console.Console;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.core.Barbatus;
import org.barbatus.exception.BarbatusException;
import org.barbatus.network.http.BarbatusHttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class HtmlHttpServer {


    public static void main(String[] args) throws BarbatusException, IOException {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("HTML HTTP Server");

        new BarbatusHttpServer()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .setHandlers(HtmlFileRoute.class, RawHtmlRoute.class)
                .start();
    }


}
