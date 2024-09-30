package com.bidkoi.koiauction.utils;

import org.springframework.web.util.UriComponentsBuilder;

public class EmailUtils {
    private static final String VERIFY_EMAIL_PATH = "/verify-email";

    public static String getVerificationUrl(String recipientEmail, String token){
        return UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
                .path(VERIFY_EMAIL_PATH)
                .queryParam("token", token)
                .queryParam("email", recipientEmail)
                .toUriString();
    }
}
