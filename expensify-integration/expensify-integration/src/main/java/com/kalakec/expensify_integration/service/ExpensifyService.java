package com.kalakec.expensify_integration.service;

import com.kalakec.expensify_integration.model.Transaction;
import com.kalakec.expensify_integration.model.Vendor;
import com.kalakec.expensify_integration.repository.TransactionRepository;
import com.kalakec.expensify_integration.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ExpensifyService {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final String baseUrl = "https://integrations.expensify.com";

    public void fetchAndStoreVendorData() {
        List<Vendor> vendors = fetchVendors();
        vendorRepository.saveAll(vendors);

        List<Transaction> transactions = fetchTransactions();
        transactionRepository.saveAll(transactions);
    }

    private List<Vendor> fetchVendors() {
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = oAuthService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = baseUrl + "/fetchVendors";

        ResponseEntity<Vendor[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Vendor[].class);
        return List.of(response.getBody());
    }

    private List<Transaction> fetchTransactions() {
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = oAuthService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = baseUrl + "/fetchTransactions";

        ResponseEntity<Transaction[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Transaction[].class);
        return List.of(response.getBody());
    }
}
