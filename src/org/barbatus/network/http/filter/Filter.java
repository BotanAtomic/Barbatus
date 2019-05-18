package org.barbatus.network.http.filter;

import org.barbatus.network.http.entity.BarbatusHttpRequest;
import org.barbatus.network.http.entity.BarbatusHttpResponse;

public interface Filter {

    boolean filter(BarbatusHttpRequest request, BarbatusHttpResponse response);

}
