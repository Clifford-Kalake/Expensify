package com.kalakec.expensify_integration.repository;

import com.kalakec.expensify_integration.model.Transaction;
import com.kalakec.expensify_integration.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRepository extends MongoRepository<Vendor, String> {
}
