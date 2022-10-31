package com.nttdata.bootcamp.msaccounts.dto;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountTransactionDTO {
  private String id;
  private String nroAccount;
  private String type;
  private String detail;
  private Double transactionAmount;
  private Date transactionDate;
  private Double comission;
  private Double before;
  private Double transactionLater;
  

}
