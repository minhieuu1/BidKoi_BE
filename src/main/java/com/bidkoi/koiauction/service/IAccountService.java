package com.bidkoi.koiauction.service;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.payload.request.LoginRequest;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.payload.response.LoginResponse;
import com.bidkoi.koiauction.pojo.Account;

import java.util.Optional;

public interface IAccountService {
   AccountDTO createAccount(AccountCreationRequest request);
   LoginResponse login(LoginRequest request);
//   Optional<Account> getAccountById(String id);


//   AccountUpdateResponse updateAccount(String id , AccountUpdateRequest request);
//
//   public void updatePassword(String accountId, UpdatePasswordRequest updatePasswordRequest);
}
