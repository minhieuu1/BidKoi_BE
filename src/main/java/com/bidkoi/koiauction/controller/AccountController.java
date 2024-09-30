package com.bidkoi.koiauction.controller;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.payload.request.LoginRequest;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.payload.response.ApiResponse;
import com.bidkoi.koiauction.payload.response.LoginResponse;
import com.bidkoi.koiauction.pojo.Account;
import com.bidkoi.koiauction.service.IAccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@CrossOrigin()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    IAccountService iAccountService;

    @PostMapping("/register")
    ApiResponse<AccountDTO> register(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountDTO>builder().data(iAccountService.createAccount(request)).build();
    }

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest loginDTO) {
        return iAccountService.login(loginDTO);
    }


}
