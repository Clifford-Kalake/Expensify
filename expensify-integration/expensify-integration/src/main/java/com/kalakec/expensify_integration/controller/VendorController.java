package com.kalakec.expensify_integration.controller;

import com.kalakec.expensify_integration.service.ExpensifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

    @Autowired
    private ExpensifyService expensifyService;

    @GetMapping("/fetch-vendors")
    public String fetchVendors() {
        expensifyService.fetchAndStoreVendorData();
        return "Vendor data fetched and stored!";
    }
}
