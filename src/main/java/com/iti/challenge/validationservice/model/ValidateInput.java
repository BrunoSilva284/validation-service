package com.iti.challenge.validationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateInput {

    @JsonProperty("pass")
    private String pass = null;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
