package com.nttdata.bootcamp.msaccounts.infraestructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.msaccounts.model.Account;

import reactor.core.publisher.Flux;

@Repository
public interface IAccountReactiveRepository extends ReactiveMongoRepository<Account, String> {
    public Flux<Account> findByNroDoc(String nroDoc);
   // public Flux<Account>  findByTypeAccount(String typeAccount);//(String nroDoc);//, String fec1, String fec2);
   public Flux<Account> listar(String nroDocu); 


}
