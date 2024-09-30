package com.bidkoi.koiauction.payload.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KoiCreationRequest {

    Double length;
    String varieties;
    String age;
    String sex;
    String image;
    String description;
    Double initialPrice;
    Double finalPrice;

}
