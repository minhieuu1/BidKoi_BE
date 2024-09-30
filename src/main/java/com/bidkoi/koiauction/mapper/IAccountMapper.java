package com.bidkoi.koiauction.mapper;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.payload.request.AccountUpdateRequest;
import com.bidkoi.koiauction.payload.response.AccountUpdateResponse;
import com.bidkoi.koiauction.pojo.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    Account toAccount(AccountCreationRequest accountCreationRequest);
    AccountDTO toAccountDTO(Account account);



//    void updateAccountFromRequest(AccountUpdateRequest request, @MappingTarget Account account);
//    AccountUpdateResponse toAccountUpdateResponse(Account account);
}
