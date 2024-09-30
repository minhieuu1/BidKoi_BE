package com.bidkoi.koiauction.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KoiDTO {
    Integer id;
    Double length;
    String varieties;
    String age;
    String sex;
    String image;
    String description;
    Double initialPrice;
    Double finalPrice;
}
