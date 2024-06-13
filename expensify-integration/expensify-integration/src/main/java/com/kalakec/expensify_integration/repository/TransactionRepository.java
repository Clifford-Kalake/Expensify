package com.kalakec.expensify_integration.repository;

import com.kalakec.expensify_integration.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
