package org.barbatus.network.http;

import com.sun.net.httpserver.HttpServer;
import org.barbatus.console.Console;
import org.barbatus.network.http.annotations.BarbatusRoute;
import org.barbatus.network.http.exception.BarbatusHttpException;
import org.barbatus.network.http.handler.BarbatusHttpHandler;
import org.barbatus.network.http.processor.BarbatusHttpProcessor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class BarbatusHttpServer {

    private InetSocketAddress address = new InetSocketAddress(InetAddress.getLoopbackAddress(), 80);

    private int timeout = 120;
    private boolean enableCors = false;
    private Executor executor;

    private List<BarbatusHttpHandler> handlers;

    private BarbatusHttpProcessor<?> preProcessor;

    private HttpServer root;

    private BarbatusHttpServer() {

    }

    public static BarbatusHttpServer newInstance() {
        return new BarbatusHttpServer();
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public BarbatusHttpServer setAddress(InetSocketAddress address) {
        this.address = address;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public BarbatusHttpServer setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public boolean isCorsEnabled() {
        return enableCors;
    }

    public BarbatusHttpServer enableCors(boolean enableCors) {
        this.enableCors = enableCors;
        return this;
    }

    public BarbatusHttpProcessor<?> getPreProcessor() {
        return preProcessor;
    }

    public BarbatusHttpServer setPreProcessor(BarbatusHttpProcessor<?> preProcessor) {
        this.preProcessor = preProcessor;
        return this;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public List<BarbatusHttpHandler> getHandlers() {
        return handlers;
    }

    public BarbatusHttpServer setHandlers(Class<? extends BarbatusHttpHandler>... handlers) throws BarbatusHttpException {
        this.handlers = new ArrayList<>();
        for (Class<? extends BarbatusHttpHandler> handler : handlers) {
            try {
                BarbatusHttpHandler httpHandler = handler.newInstance();

                Field[] variables = handler.getSuperclass().getDeclaredFields();

                for (Field variable : variables) {
                    if (variable.isAnnotationPresent(org.barbatus.network.http.annotations.HttpServer.class)) {
                        variable.setAccessible(true);
                        variable.set(httpHandler, this);
                        variable.setAccessible(false);
                    }
                }

                this.handlers.add(httpHandler);
            } catch (InstantiationException e) {
                throw new BarbatusHttpException(String.format("Invalid class {%s}", handlers.getClass().getName()));
            } catch (IllegalAccessException e) {
                throw new BarbatusHttpException(String.format("Invalid constructor for " +
                        "class {%s}", handlers.getClass().getName()));
            }
        }
        return this;
    }

    public void start() throws IOException {
        root = HttpServer.create(this.address, 0);
        root.setExecutor(this.executor);

        for (BarbatusHttpHandler handler : this.handlers) {
            BarbatusRoute parameters = handler.getClass().getAnnotation(BarbatusRoute.class);
            root.createContext(parameters.value(), handler);

            Console.debug(getClass().getSimpleName(),
                    String.format("Context {%s} %s -> %s", parameters.value(),
                            parameters.secure() ? "[secured]" : "", handler.getClass().getName()));
        }

        root.start();
        Console.info(getClass().getSimpleName(), String.format("Server successfully bound on [%s]", this.address.toString()));
    }

    public void stop(int delay) {
        this.root.stop(delay);
    }

    public void stopNow() {
        this.root.stop(0);
    }

}
