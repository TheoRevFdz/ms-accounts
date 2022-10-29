package com.nttdata.bootcamp.msaccounts.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msaccounts.config.RestConfig;
import com.nttdata.bootcamp.msaccounts.dto.AccountTransactionDTO;

@Service
public class AccountTransactionServiceImpl implements IAccountTransactionService {

    @Autowired
    private RestConfig rest;

    @Override
    public ResponseEntity<AccountTransactionDTO> createTransactionByAction(AccountTransactionDTO dto, String action) {
        HttpEntity<AccountTransactionDTO> body = new HttpEntity<AccountTransactionDTO>(dto);
        String uri = String.format("http://localhost:8090/api/accounts_transactions/%s", action);
        ResponseEntity<AccountTransactionDTO> resp = rest.exchange(
                uri,
                HttpMethod.POST,
                body,
                AccountTransactionDTO.class);
        return resp;
    }

}
