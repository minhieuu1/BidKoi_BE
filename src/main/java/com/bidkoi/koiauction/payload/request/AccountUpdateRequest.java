package com.bidkoi.koiauction.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {


    String firstname;
    String lastname;
    String gender;
    @Email(message = "INVALID_EMAIL")
    String email;
    @Size(min = 10,max = 10,message = "PHONE_INVALID")
    String phone;
    Date birthday;
}
