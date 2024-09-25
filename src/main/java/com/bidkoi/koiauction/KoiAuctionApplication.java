package com.bidkoi.koiauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KoiAuctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoiAuctionApplication.class, args);
    }

}
