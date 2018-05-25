package com.luveenw.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldEndpoint {
    private static final Map<String, String> DUMMY_MAP = new HashMap<>();

    static {
        DUMMY_MAP.put("1", "hello world!");
        DUMMY_MAP.put("2", "how are you?");
    }

    public HelloWorldEndpoint() {
    }

    @GET
    public Map<String, String> sayHello() {

        return DUMMY_MAP;
    }
}
