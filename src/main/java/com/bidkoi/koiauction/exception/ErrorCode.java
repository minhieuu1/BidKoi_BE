package com.bidkoi.koiauction.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    USER_EXISTED(422,"User already existed!"),
    EMAIL_EXISTED(422, "Email already existed"),
    USER_NOT_FOUND(404,"User not found!"),
    USERNAME_INVALID(400,"Username must be between 8 and 16 characters"),
    PHONE_INVALID(400,"Phone number must be 10 characters"),
    UNAUTHENTICATED(401,"Invalid username or password!"),  //Unauthorized
    INVALID_EMAIL(400,"Invalid email address!"),
    INVALID_CURRENT_PASSWORD(400, "Invalid current password!!!"),  //Bad Request
    SENDMAIL_FAILED(405, "Can not send email!!!"),
    ;
    int code;
    String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
