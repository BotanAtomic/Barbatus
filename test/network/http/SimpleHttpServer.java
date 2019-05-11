package network.http;

import network.http.handler.HelloHandler;
import org.barbatus.core.Barbatus;
import org.barbatus.enums.ConsoleLevel;
import org.barbatus.network.http.BarbatusHttpServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class SimpleHttpServer {


    public static void main(String[] args) throws Exception {
        Barbatus.setConsoleLevel(ConsoleLevel.TRACE);

        BarbatusHttpServer server = BarbatusHttpServer
                .newInstance()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .enableCors(true)
                .setTimeout(3000)
                .setHandlers(HelloHandler.class);

        server.start();
    }


}
