package network.http.injection;

import network.http.injection.handler.SimpleRoute;
import org.barbatus.console.Console;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.core.Barbatus;
import org.barbatus.exception.BarbatusException;
import org.barbatus.network.http.BarbatusHttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InjectionHttpServer {


    public static void main(String[] args) throws BarbatusException, IOException {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("Injection HTTP Server");

        new BarbatusHttpServer()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .setHandlers(SimpleRoute.class)
                .inject("myObject", new MyObject("injected object"))    // inject named
                .inject(new String("singleton class of String"))            // inject singleton
                .start();
    }


}
