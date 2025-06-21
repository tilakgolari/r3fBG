package com.example.reacthree.dto;


import lombok.Builder;


@Builder
public class StripeResponse {

    private String SessionId;
    private String SessionUrl;

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getSessionUrl() {
        return SessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        SessionUrl = sessionUrl;
    }

    public StripeResponse(String sessionId, String sessionUrl) {
        SessionId = sessionId;
        SessionUrl = sessionUrl;
    }


    public StripeResponse() {
    }
}
