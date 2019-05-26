package network.http.authenticator.token;

import org.barbatus.math.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic not ? No, disgusting !
 */
public class BasicTokenManager {

    private final List<String> tokens = new ArrayList<>();

    public String generateToken(short length) {
        byte asciiStartIndex = 65, asciiEndIndex = 122;

        StringBuilder token = new StringBuilder();

        for (short i = 0; i < length; i++)
            token.append((char) RandomUtils.between(asciiStartIndex, asciiEndIndex));

        this.tokens.add(token.toString());
        return token.toString();
    }

    public boolean isValidToken(String token) {
        return this.tokens.contains(token);
    }

}
