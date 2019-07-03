package org.barbatus.network.http.transformer;

import org.barbatus.common.transformer.Transformer;
import org.barbatus.network.http.entity.BarbatusHttpRequest;

public interface HttpInputTransformer<T> extends Transformer<BarbatusHttpRequest, T> {

}
