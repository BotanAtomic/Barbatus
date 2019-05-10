package org.barbatus.network.http.processor;

import org.barbatus.network.http.entity.BarbatusHttpRequest;

public interface BarbatusHttpProcessor<T> {

    T transform(BarbatusHttpRequest request);

}
