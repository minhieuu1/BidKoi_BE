package com.bidkoi.koiauction.service;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.payload.request.LoginRequest;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.payload.response.LoginResponse;

public interface IAccountService {
   AccountDTO createAccount(AccountCreationRequest request);
   LoginResponse login(LoginRequest request);
}
