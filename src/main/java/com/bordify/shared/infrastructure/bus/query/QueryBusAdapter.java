package com.bordify.shared.infrastructure.bus.query;

import com.bordify.shared.domain.bus.query.Query;
import com.bordify.shared.domain.bus.query.QueryBus;
import com.bordify.shared.domain.bus.query.QueryHandler;
import com.bordify.shared.domain.bus.query.Response;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class QueryBusAdapter implements QueryBus {

    private final ApplicationContext context;
    private Map<Class<? extends Query>, QueryHandler> queries = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, QueryHandler> handlers = context.getBeansOfType(QueryHandler.class);

        handlers.forEach((nameBean, queryHandler) -> {
            Class<?> queryHandlerClass = queryHandler.getClass();
            ParameterizedType genericInterface = (ParameterizedType) queryHandlerClass.getGenericInterfaces()[0];

            Class<?> queryType = (Class<?>) genericInterface.getActualTypeArguments()[0];
            Class<?> responseType = (Class<?>) genericInterface.getActualTypeArguments()[1];

            if (Query.class.isAssignableFrom(queryType)) {
                queries.put((Class<? extends Query>) queryType, queryHandler);
                System.out.println("Added to queries: " + queryType.getName() + " -> " + queryHandler.getClass().getName());
            }
        });
    }

    @Override
    public Response ask(Query query) {
        QueryHandler handler = queries.get(query.getClass());
        return handler.handle(query);
    }
}
