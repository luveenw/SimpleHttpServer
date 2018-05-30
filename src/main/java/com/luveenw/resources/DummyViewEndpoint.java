package com.luveenw.resources;

import com.luveenw.views.DummyView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloTo/{firstName}/{lastName}")
@Produces(MediaType.TEXT_HTML)
public class DummyViewEndpoint {
    @GET
    public DummyView getPerson(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return new DummyView(firstName, lastName);
    }
}
