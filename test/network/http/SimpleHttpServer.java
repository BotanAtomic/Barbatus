package network.http;

import network.http.handler.SimpleObject;
import network.http.handler.route.HtmlExample;
import network.http.handler.route.JsonExample;
import network.http.handler.route.ProcessorExample;
import network.http.handler.route.user.get.UserGet;
import org.barbatus.console.Console;
import org.barbatus.console.enums.ConsoleLevel;
import org.barbatus.core.Barbatus;
import org.barbatus.network.http.BarbatusHttpServer;
import org.barbatus.network.http.authenticator.BarbatusAuthenticator;
import org.barbatus.network.http.enums.HttpStatus;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class SimpleHttpServer {


    public static void main(String[] args) throws Exception {
        Console.setLevel(ConsoleLevel.TRACE);
        Barbatus.setApplicationName("Simple HTTP Server");

        BarbatusHttpServer server = BarbatusHttpServer
                .newInstance()
                .setAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080))
                .setExecutor(Executors.newCachedThreadPool())
                .enableCors(true)
                .setBacklog(200)
                .setAuthenticator(new BarbatusAuthenticator<>()
                        .onAuthenticate((request, object) -> {
                            String token = request.getHeaders().getFirst("token");
                            return true;
                            //return token != null && token.equals("YOUR_LOGIC_TOKEN");
                        }, null)
                        .onFail(response -> response.sendStatus(HttpStatus.NOT_FOUND))
                )
                .inject("myObject", new SimpleObject("myObject"))
                .inject("myObject1", new SimpleObject("myObject1"))
                .setHandlers(JsonExample.class, HtmlExample.class, ProcessorExample.class, UserGet.class)
                .start();


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            if (scanner.nextLine().equals("stop")) {
                server.stop(5);
            }
        }

    }


}
