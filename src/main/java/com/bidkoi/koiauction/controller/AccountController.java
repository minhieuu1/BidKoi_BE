package com.bidkoi.koiauction.controller;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.dto.BidderDTO;
import com.bidkoi.koiauction.payload.request.AccountUpdateRequest;
import com.bidkoi.koiauction.payload.request.LoginRequest;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.payload.request.UpdatePasswordRequest;
import com.bidkoi.koiauction.payload.response.AccountUpdateResponse;
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
@CrossOrigin
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

    @GetMapping("/view/{id}")
    public ResponseEntity<Optional<Account>> getAccountByID(@PathVariable String id){
        Optional<Account> account = iAccountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

//    @PutMapping("/update-account/{id}")
//    public AccountUpdateResponse updateAccount(@PathVariable String id, @RequestBody AccountUpdateRequest request){
//        return iAccountService.updateAccount(id,request);
//    }

    @PutMapping("/update-profile/{accountId}")
    public ResponseEntity<BidderDTO> updateProfile(@PathVariable String accountId, @RequestBody BidderDTO bidderDTO) {
        BidderDTO updatedProfile = iAccountService.updateProfile(accountId, bidderDTO);
        return ResponseEntity.ok(updatedProfile);
    }


    @PostMapping("/update-password/{accountId}")
    public ResponseEntity<String> updatePassword(
            @PathVariable String accountId,
            @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        iAccountService.updatePassword(accountId, updatePasswordRequest);
        return ResponseEntity.ok("Password updated successfully.");
    }
}
