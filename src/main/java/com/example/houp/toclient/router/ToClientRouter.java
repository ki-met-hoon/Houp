package com.example.houp.toclient.router;

import com.example.houp.toclient.handler.ToClientHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ToClientRouter {
    @Bean
    public RouterFunction<ServerResponse> routeToClient(ToClientHandler handler) {
        return route()
                .POST("/disease-predictions", handler::handleUserInfo)
                .build();
    }
}
