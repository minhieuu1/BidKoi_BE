package com.bidkoi.koiauction.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Koi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KoiID")
    Integer id;
    Double length;
    String varieties;
    String age;
    String sex;
    String image;
    String description;
    @Column(name = "Initial_price")
    Double initialPrice;
    @Column(name = "Final_price")
    Double finalPrice;
//    @ManyToOne
//    Breeder breeder;
}
