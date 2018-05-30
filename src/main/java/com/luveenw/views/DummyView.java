package com.luveenw.views;

import io.dropwizard.views.View;

public class DummyView extends View {
    private final String firstName;
    private final String lastName;

    public DummyView(String fName, String lName) {
        super("dummy.ftl");
        this.firstName = fName;
        this.lastName = lName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
