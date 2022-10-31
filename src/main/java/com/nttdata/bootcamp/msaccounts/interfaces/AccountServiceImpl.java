package com.nttdata.bootcamp.msaccounts.interfaces;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.bootcamp.msaccounts.dto.AccountTransactionDTO;
import com.nttdata.bootcamp.msaccounts.dto.ListAccountTransactionDTO;
import com.nttdata.bootcamp.msaccounts.infraestructure.IAccountReactiveRepository;
import com.nttdata.bootcamp.msaccounts.infraestructure.IAccountRepository;
import com.nttdata.bootcamp.msaccounts.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountReactiveRepository reactiveRepo;
    @Autowired
    private IAccountRepository repository;

    //LR
    @Autowired
    private RestTemplate rest;
    @Autowired
    private static final Logger Log = LoggerFactory.getLogger(IAccountRepository.class);

    @Override
    public Mono<Account> createAccount(Mono<Account> account) {
        return account.flatMap(reactiveRepo::insert);
    }

    @Override
    public Flux<Account> findAllAccount() {
        return reactiveRepo.findAll().delayElements(Duration.ofSeconds(1)).log();
    }

    @Override
    public Mono<Account> updateAccount(Account account) {
        return reactiveRepo.findById(account.getId())
                .map(ba -> account)
                .flatMap(reactiveRepo::save);
    }

    @Override
    public boolean deleteAccount(String id) {
        Mono<Account> baExist = reactiveRepo.findById(id);
        if (baExist.block() != null) {
            reactiveRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Account> findAccountByNroDoc(String nroDoc) {
        return repository.findByNroDoc(nroDoc);
    }

    @Override
    public List<Account> findAccountByNroDocAndTypeAccount(String nroDoc, String typeAccount) {
        return repository.findByNroDocAndTypeAccount(nroDoc, typeAccount);
    }

    @Override
    public Optional<Account> findByNroAccount(String nroAccount) {
        return repository.findByNroAccount(nroAccount);
    }

/////////////////////////
@Override
    public Flux<Account> findByNroDocProduct(String nroDoc) {
        Log.info(nroDoc.toString());
        return reactiveRepo.findByNroDoc(nroDoc);    
    }
   /*  @Override
    public Flux<Account> findByTypeAccount(String nroDoc){ //}, String fec1, String fec2) {
       
        return reactiveRepo.findByTypeAccount(nroDoc); //, fec1, fec2);
    }
*/


    @Override
    public Flux<Account> findByNroDocAccount(String nroDoc) {
    Log.info(nroDoc.toString());

        return reactiveRepo.findByNroDoc(nroDoc);
    }   
    @Override
    public ListAccountTransactionDTO findBynroAccount (String nroAccount) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("nroAccount", nroAccount);
        String uri = "http://localhost:8090/api/accounts_transactions/{nroAccount}";

       // ResponseEntity<List<AccountTransactionDTO> >dto = rest.exchange(uri,HttpMethod.GET,null,
       // new ParameterizedTypeReference<List<AccountTransactionDTO>>() {            
       // },param);
       ResponseEntity<List<AccountTransactionDTO>> response = rest.exchange(
        uri,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<AccountTransactionDTO>>() {
        }, param);
        ListAccountTransactionDTO dto = ListAccountTransactionDTO.builder()
        .transactions(response.getBody())
        .build();

        return dto;
    }
//

   

  

    

   

  
   
}
