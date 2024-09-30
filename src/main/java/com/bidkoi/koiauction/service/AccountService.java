package com.bidkoi.koiauction.service;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.dto.BidderDTO;
import com.bidkoi.koiauction.payload.request.AccountUpdateRequest;
import com.bidkoi.koiauction.payload.request.LoginRequest;
import com.bidkoi.koiauction.exception.AppException;
import com.bidkoi.koiauction.exception.ErrorCode;
import com.bidkoi.koiauction.mapper.IAccountMapper;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.payload.request.UpdatePasswordRequest;
import com.bidkoi.koiauction.payload.response.AccountUpdateResponse;
import com.bidkoi.koiauction.payload.response.LoginResponse;
import com.bidkoi.koiauction.pojo.Account;
import com.bidkoi.koiauction.pojo.Bidder;
import com.bidkoi.koiauction.repository.IAccountRepository;
import com.bidkoi.koiauction.repository.IBidderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccountService implements IAccountService {

    IAccountRepository iAccountRepository;
    IBidderRepository iBidderRepository;
    IAccountMapper iAccountMapper;
    PasswordEncoder passwordEncoder;
    IEmailService iEmailService;


    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public AccountDTO createAccount(AccountCreationRequest request) {
        if (iAccountRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        else if(iAccountRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        Account account = iAccountMapper.toAccount(request);
        account.setPassword(this.passwordEncoder.encode(request.getPassword()));

        //Verify user code email

        var token = generateToken(String.valueOf(account));
        iEmailService.send(account.getUsername(), account.getEmail(), "Thank you for creating an account. Please verify your email!", token);

        return iAccountMapper.toAccountDTO(iAccountRepository.save(account));
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        var user = iAccountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(request.getUsername());
        return LoginResponse.builder()
                .token(token)
                .id(user.getId())
                .success(true)
                .build();
    }

    @Override
    public Optional<Account> getAccountById(String id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public BidderDTO updateProfile(String accountId, BidderDTO bidderDTO) {
        Account account = iAccountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Bidder bidder = iBidderRepository.findByAccountId(account.getId())
                .orElse(new Bidder());  // Nếu không tìm thấy, tạo mới một Bidder

        // Cập nhật thông tin trong Bidder
        bidder.setFirstname(bidderDTO.getFirstname());
        bidder.setLastname(bidderDTO.getLastname());
        bidder.setGender(bidderDTO.getGender());
        bidder.setBirthday(bidderDTO.getBirthday());
        bidder.setAddress(bidderDTO.getAddress());


        account.setEmail(bidderDTO.getEmail());
        account.setPhone(bidderDTO.getPhone());
        bidder.setEmail(account.getEmail());
        bidder.setPhone(account.getPhone());

        bidder.setAccount(account); // Liên kết lại với Account

        iAccountRepository.save(account);
        iBidderRepository.save(bidder);

        BidderDTO updatedBidderDTO = BidderDTO.builder()
                .firstname(bidder.getFirstname())
                .lastname(bidder.getLastname())
                .gender(bidder.getGender())
                .email(account.getEmail())
                .phone(account.getPhone())
                .birthday(bidder.getBirthday())
                .address(bidder.getAddress())
                .build();

        return updatedBidderDTO;
    }

//    @Override
//    public AccountUpdateResponse updateAccount(String id, AccountUpdateRequest request) {
//        Account account = iAccountRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//
//        iAccountMapper.updateAccountFromRequest(request, account);
//
//        return iAccountMapper.toAccountUpdateResponse(iAccountRepository.save(account));
//    }



    @Override
    public void updatePassword(String accountId, UpdatePasswordRequest updatePasswordRequest) {

        Account account = iAccountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));


        if (!passwordEncoder.matches(updatePasswordRequest.getCurrentPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CURRENT_PASSWORD);
        }


        // Cập nhật password
        account.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        iAccountRepository.save(account);
    }

    @Override
    public void verifyAccount(String email, String token) {
        Account account = iAccountRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        //account.setStatus(true);
        //account.setRole(Role.MEMBER.name());
        iAccountRepository.save(account);

    }


    private String generateToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("BidKoi.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token");
            throw new RuntimeException(e);
        }
    }
}
