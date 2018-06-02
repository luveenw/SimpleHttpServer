package com.luveenw.resources;

import com.luveenw.views.JokeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Path("/joke")
@Produces(MediaType.TEXT_HTML)
public class JokeByIdEndpoint {
    @GET
    public JokeView getJokeById(@PathParam("id") String jokeId) {
        /*Client client = ClientBuilder.newClient();
        String result = client.target(
                "https://jokes-api.herokuapp.com/api/joke/").request().get(String.class);

        return new JokeView(result);*/
        return null;
    }
}
