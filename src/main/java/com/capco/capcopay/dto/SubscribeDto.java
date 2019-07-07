package com.capco.capcopay.dto;

import java.util.StringJoiner;

public class SubscribeDto {

    private String email;

    public SubscribeDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", SubscribeDto.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .toString();
    }
}
