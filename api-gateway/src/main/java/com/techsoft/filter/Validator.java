package com.techsoft.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;


import java.util.List;
import java.util.function.Predicate;

@Component
public class Validator {
    public static final List<String> openEndPoints = List.of(
            "/registerUser",
            "/generateToken",
            "/validateToken"
    );
    public Predicate<ServerHttpRequest> isSecure = serverRequest -> openEndPoints.stream().noneMatch(uri->serverRequest.getURI().getPath().contains(uri));
}
