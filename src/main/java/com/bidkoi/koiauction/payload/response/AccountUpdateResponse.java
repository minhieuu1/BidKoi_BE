package com.bidkoi.koiauction.payload.response;


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
public class AccountUpdateResponse {

    String firstname;
    String lastname;
    String gender;
    String email;
    String phone;
    Date birthday;
}
