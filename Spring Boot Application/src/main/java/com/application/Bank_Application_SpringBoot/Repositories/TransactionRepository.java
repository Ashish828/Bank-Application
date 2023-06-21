package com.application.Bank_Application_SpringBoot.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.Bank_Application_SpringBoot.Model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

}
