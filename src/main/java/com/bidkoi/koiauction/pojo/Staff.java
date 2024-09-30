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
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "StaffID")
    String staffID;
    @Column(name = "First_name")
    String firstName;
    @Column(name = "Last_name")
    String lastName;


}
