package com.luveenw.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Joke {
    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    @JsonProperty("value")
    private Value value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", value=" + value +
                '}';
    }
}