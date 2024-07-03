package com.curso_loiane.crud_spring.enums;

public enum Category {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    MOBILE("Mobile");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {  //get bem util
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
