package org.barbatus.network.http.filter;

import java.util.concurrent.ConcurrentLinkedQueue;

public class FilterGroup extends ConcurrentLinkedQueue<Filter> {

    private FilterGroup() {

    }

    public static FilterGroup create() {
        return new FilterGroup();
    }

    public FilterGroup append(Filter filter) {
        super.add(filter);
        return this;
    }



}
