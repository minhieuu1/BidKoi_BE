package com.bidkoi.koiauction.service;

public interface IEmailService {
    void send(String name, String to, String subject, String token);
}
