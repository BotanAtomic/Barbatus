package org.barbatus.math;

import java.util.Random;

public class RandomUtils {

    public static int between(int start, int end) {
        return new Random().nextInt(end - start + 1) + start;
    }

}
