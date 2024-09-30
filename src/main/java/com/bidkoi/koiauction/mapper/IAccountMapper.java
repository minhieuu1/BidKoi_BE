package com.bidkoi.koiauction.mapper;

import com.bidkoi.koiauction.dto.AccountDTO;
import com.bidkoi.koiauction.payload.request.AccountCreationRequest;
import com.bidkoi.koiauction.pojo.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    Account toAccount(AccountCreationRequest accountCreationRequest);
    AccountDTO toAccountDTO(Account account);


}
