package kwan.ie.listpostsbyranking.presentation.user;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Token {
    private final String basicToken;

    public String getBasicToken() {
        return basicToken;
    }

    @JsonCreator
    public Token(String basicToken) {
        this.basicToken = basicToken;
    }
}
