package com.bidkoi.koiauction.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Breeder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "BreederID")
    String breederID;
    @Column(name = "Breeder_name")
    String breederName;
    String address;
//    @OneToMany
//    List<Koi> kois;
}
