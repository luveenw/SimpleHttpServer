package com.luveenw.views;

import com.luveenw.core.Value;
import io.dropwizard.views.View;

import java.util.Collections;
import java.util.List;

public class JokeView extends View {
    private final Value joke;

    public JokeView(Value joke) {
        super("joke.ftl");

        if (joke == null) {
            this.joke = new Value("Could not get a joke. Sorry!", "The Admins", Collections.emptyList(), "");
        } else {
            this.joke = joke;
        }
    }

    public String getText() {
        return joke.getJoke();
    }

    public String getAuthor() {
        return joke.getAuthor();
    }

    public List<String> getCategories() {
        return joke.getCategories();
    }

    public String getId() {
        return joke.getId();
    }
}
