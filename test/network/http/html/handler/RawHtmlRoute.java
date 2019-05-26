package network.http.html.handler;

import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

import java.io.File;

@BarbatusRoute("/html/raw")
public class RawHtmlRoute extends BarbatusHttpHandler {

    @Override
    public void handle(BarbatusHttpRequest request, BarbatusHttpResponse response) throws Exception {
        response.sendString("<html>\n" +
                "<form action=\"\">\n" +
                "    <fieldset>\n" +
                "        <legend>Personal information:</legend>\n" +
                "        First name:<br>\n" +
                "        <input type=\"text\" name=\"firstname\" value=\"Mickey\"><br>\n" +
                "        Last name:<br>\n" +
                "        <input type=\"text\" name=\"lastname\" value=\"Mouse\"><br><br>\n" +
                "        <input type=\"submit\" value=\"Submit\">\n" +
                "    </fieldset>\n" +
                "</form>\n" +
                "</html>");
    }

}
