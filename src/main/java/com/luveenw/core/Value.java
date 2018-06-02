package com.luveenw.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Value {
    @JsonProperty("author")
    private String author;

    @JsonProperty("categories")
    private List<String> categories;

    @JsonProperty("id")
    String id;

    @JsonProperty("joke")
    String joke;

    public Value() {
    }

    public Value(String joke, String author, List<String> cats, String id) {
        this.joke = joke;
        this.author = author;
        this.categories = cats;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "Value{" +
                "author='" + author + '\'' +
                ", categories=" + categories +
                ", id='" + id + '\'' +
                ", joke='" + joke + '\'' +
                '}';
    }
}