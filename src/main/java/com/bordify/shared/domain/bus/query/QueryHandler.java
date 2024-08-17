package com.bordify.shared.domain.bus.query;

public interface QueryHandler<Q extends Query, R extends Response> {

    public R handle(Q query);
}
