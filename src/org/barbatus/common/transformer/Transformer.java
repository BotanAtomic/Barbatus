package org.barbatus.common.transformer;

public interface Transformer<I, O> {

    O transform(I input);

}
