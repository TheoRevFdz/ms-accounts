package com.nttdata.bootcamp.msaccounts.interfaces;

import java.util.List;
import java.util.Optional;


import com.nttdata.bootcamp.msaccounts.dto.ListAccountTransactionDTO;
import com.nttdata.bootcamp.msaccounts.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {
    public Mono<Account> createAccount(Mono<Account> account);

    public Flux<Account> findAllAccount();

    public Mono<Account> updateAccount(Account account);

    public boolean deleteAccount(String id);

    public List<Account> findAccountByNroDoc(String nroDoc);

    public List<Account> findAccountByNroDocAndTypeAccount(String nroDoc, String typeAccount);

    public Optional<Account> findByNroAccount(String nroAccount);


    //
    public Flux<Account> findByNroDocAccount(String nroDoc);
    public Flux<Account> findByNroDocProduct(String nroDoc);
    //public Flux<Account> findByTypeAccount(String nroDoc); //,String fec1,String fec2); 

    public ListAccountTransactionDTO findBynroAccount (String nroAccount);




    //public List<AccountTransactionDTO> findBynroAccount (String nroAccount) ;
//
    //public Optional<Account> findBynroDoc(String nroAccount);
    //public List<Account> findBynroDoc(String nroDoc);
   // public List<Account> findBynroDoc(String nroDoc);
  
    


}
