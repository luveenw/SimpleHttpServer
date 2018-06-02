package com.luveenw.resources;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.luveenw.core.Joke;
import com.luveenw.views.JokeView;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Path("/joke")
@Produces(MediaType.TEXT_HTML)
public class JokeEndpoint {
    private static final String BASE_URL = "https://jokes-api.herokuapp.com/api";
    private static final String RANDOM_JOKE_PATH = "/joke";
    private static final String BY_ID_PATH = "/joke/%s";

    @GET
    public JokeView getRandomJoke() {
        return requestJoke(RANDOM_JOKE_PATH);
    }

    @GET
    @Path("/{id}")
    public JokeView getJokeById(@PathParam("id") String id) {
        return requestJoke(String.format(BY_ID_PATH, id));
    }

    private static JokeView requestJoke(String path) {
        Joke result = null;
        Client client = ClientBuilder.newClient();

        try {
            result = client
                    .target(BASE_URL + path)
                    .register(JacksonJsonProvider.class)
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(Joke.class);
        } catch (ProcessingException | IllegalStateException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }

        return new JokeView(result == null ? null : result.getValue());
    }

    private static Client getClient() {
        Client client = ClientBuilder.newClient();

        client.register(JacksonJsonProvider.class);

        return client;
    }
}
