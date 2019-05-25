package org.barbatus.network.http.authenticator;

import org.barbatus.console.Console;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;

public class BarbatusAuthenticator<T> {

    private T object;
    private Authenticator<T> authenticator;
    private OnFail onFail;

    public BarbatusAuthenticator onAuthenticate(Authenticator<T> authenticator, T object) {
        this.authenticator = authenticator;
        this.object = object;
        return this;
    }

    public BarbatusAuthenticator onFail(OnFail onFail) {
        this.onFail = onFail;
        return this;
    }

    public boolean authenticate(BarbatusHttpRequest request, BarbatusHttpResponse response) {
        if(authenticator == null) {
            Console.warn(this.getClass().getSimpleName(), "Empty authenticator");
            return false;
        }

        if (!authenticator.authenticate(request, object)) {
            if (onFail != null)
                onFail.perform(response);
            else
                Console.warn(this.getClass().getSimpleName(), "You must define a failure callback");
            return false;
        }

        return true;
    }


    public interface OnFail {

        void perform(BarbatusHttpResponse response);

    }

    public interface Authenticator<T> {

        boolean authenticate(BarbatusHttpRequest request, T object);

    }

}
