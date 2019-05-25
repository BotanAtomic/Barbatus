package org.barbatus.network.http;

import com.sun.net.httpserver.HttpServer;
import org.barbatus.annotation.InputProcessor;
import org.barbatus.annotation.OutputProcessor;
import org.barbatus.common.transformer.Transformer;
import org.barbatus.console.Console;
import org.barbatus.exception.BarbatusException;
import org.barbatus.injector.annotation.Inject;
import org.barbatus.network.http.annotation.BarbatusRoute;
import org.barbatus.network.http.authenticator.BarbatusAuthenticator;
import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.exception.BarbatusHttpException;
import org.barbatus.network.http.handler.BarbatusHttpHandler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class BarbatusHttpServer {

    private InetSocketAddress address = new InetSocketAddress(InetAddress.getLoopbackAddress(), 80);

    private int backlog = 0;
    private boolean enableCors = false;
    private Executor executor;

    private List<BarbatusHttpHandler> handlers;

    private BarbatusAuthenticator authenticator;

    private HttpServer root;

    private Map<String, Object> dependencies = new HashMap<>();


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

    public int getBacklog() {
        return backlog;
    }

    public BarbatusHttpServer setBacklog(int backlog) {
        this.backlog = backlog;
        return this;
    }

    public boolean isCorsEnabled() {
        return enableCors;
    }

    public BarbatusHttpServer enableCors(boolean enableCors) {
        this.enableCors = enableCors;
        return this;
    }

    public BarbatusAuthenticator getAuthenticator() {
        return this.authenticator;
    }

    public BarbatusHttpServer setAuthenticator(BarbatusAuthenticator authenticator) {
        this.authenticator = authenticator;
        return this;
    }

    public BarbatusHttpServer setExecutor(Executor executor) {
        this.executor = executor;
        return this;
    }

    public List<BarbatusHttpHandler> getHandlers() {
        return handlers;
    }


    public BarbatusHttpServer setHandlers(Class<? extends BarbatusHttpHandler>... handlers) throws BarbatusHttpException {
        this.handlers = new ArrayList<>();
        for (Class<? extends BarbatusHttpHandler> handler : handlers) {
            try {
                BarbatusHttpHandler httpHandler = handler.newInstance();
                BarbatusRoute parameters = handler.getAnnotation(BarbatusRoute.class);

                Field[] superClassVariables = handler.getSuperclass().getDeclaredFields();
                Field[] variables = handler.getDeclaredFields();

                Transformer<BarbatusHttpRequest, ?> inputProcessor = null;
                Transformer<Object, byte[]> outputProcessor = null;

                for (Field variable : variables) {
                    variable.setAccessible(true);
                    if (variable.isAnnotationPresent(InputProcessor.class)) {
                        inputProcessor = (Transformer<BarbatusHttpRequest, ?>) variable.get(httpHandler);
                    } else if (variable.isAnnotationPresent(OutputProcessor.class)) {
                        outputProcessor = (Transformer<Object, byte[]>) variable.get(httpHandler);
                    } else if (variable.isAnnotationPresent(Inject.class)) {
                        Inject injectionParameters = variable.getAnnotation(Inject.class);
                        Object obj = null;

                        if (!injectionParameters.value().isEmpty()) {
                            obj = dependencies.get(injectionParameters.value());
                        } else {
                            for (Object value : dependencies.values())
                                if (value.getClass().equals(variable.getType())) {
                                    obj = value;
                                    break;
                                }
                        }

                        if (obj == null)
                            Console.warn(getClass().getSimpleName(), String.format(
                                    "Injection of null dependency for field '%s' in class %s",
                                    variable.getName(), handler.getName()));

                        variable.set(httpHandler, obj);


                    }
                    variable.setAccessible(false);
                }

                for (Field variable : superClassVariables) {
                    variable.setAccessible(true);
                    if (variable.isAnnotationPresent(org.barbatus.network.http.annotation.HttpServer.class)) {
                        variable.set(httpHandler, this);
                    } else if (variable.isAnnotationPresent(InputProcessor.class)) {
                        variable.set(httpHandler, inputProcessor);
                    } else if (variable.isAnnotationPresent(OutputProcessor.class)) {
                        variable.set(httpHandler, outputProcessor);
                    } else if(variable.isAnnotationPresent(Inject.class)) {
                        if(variable.getName().equals("secure")) {
                            variable.set(httpHandler, parameters.secure());
                        } else if(variable.getName().equals("method")) {
                            variable.set(httpHandler, parameters.method());
                        }
                    }
                    variable.setAccessible(false);
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

    public BarbatusHttpServer inject(Object object) throws BarbatusException {
        for (Object instance : dependencies.values())
            if (instance.getClass().equals(object.getClass()))
                throw new BarbatusException("Can not inject the same class twice without name");

        this.dependencies.put(String.valueOf(System.nanoTime()), object);
        return this;
    }

    public BarbatusHttpServer inject(String name, Object object) {
        this.dependencies.put(name, object);
        return this;
    }

    public BarbatusHttpServer start() throws IOException {
        root = HttpServer.create(this.address, this.backlog);
        root.setExecutor(this.executor);

        for (BarbatusHttpHandler handler : this.handlers) {
            BarbatusRoute parameters = handler.getClass().getAnnotation(BarbatusRoute.class);

            if (parameters != null) {
                root.createContext(parameters.value(), handler);

                Console.debug(getClass().getSimpleName(),
                        String.format("Context { %s } %s-> %s", parameters.value(),
                                parameters.secure() ? "[secured] " : "", handler.getClass().getName()));
            } else {
                Console.error(getClass().getSimpleName(),
                        String.format("Cannot create context for class {%s} : missing annotation %s",
                                handler.getClass().getSimpleName(), BarbatusRoute.class.getName()));
            }
        }

        root.start();
        Console.info(getClass().getSimpleName(), String.format("Server successfully bound on [%s]", this.address.toString()));
        return this;
    }

    public void stop(int delay) {
        Console.info(getClass().getSimpleName(),
                String.format("Server will be stopped in %d seconds", delay));
        this.root.stop(delay);
        Console.info(getClass().getSimpleName(), "Server successfully stopped");
    }

    public void stopNow() {
        this.root.stop(0);
        Console.info(getClass().getSimpleName(), "Server successfully stopped");
    }

}
