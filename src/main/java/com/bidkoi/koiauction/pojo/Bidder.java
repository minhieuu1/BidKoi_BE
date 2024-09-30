package com.bidkoi.koiauction.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Bidder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "BidderID")
    String bidderID;
    @Column(name = "First_name")
    String firstName;
    @Column(name = "Last_name")
    String lastName;
    @Column(name = "Birthday")
    Date dob;
}
