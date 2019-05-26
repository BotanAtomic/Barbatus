package org.barbatus.network.http.annotation;

import org.barbatus.network.http.enums.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BarbatusRoute {

    String value() default "/";

    boolean secure() default false;

    HttpMethod method() default HttpMethod.ANY;
}
