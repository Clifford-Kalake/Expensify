package com.kalakec.expensify_integration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "vendors")
public class Vendor {

    @Id
    private String id;
    private String name;
    private double amountPaid;
    private List<String> invoices;
    private List<String> contracts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public List<String> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<String> invoices) {
        this.invoices = invoices;
    }

    public List<String> getContracts() {
        return contracts;
    }

    public void setContracts(List<String> contracts) {
        this.contracts = contracts;
    }
}
