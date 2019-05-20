package br.com.mdev.camelsplit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe de exemplo de um cliente com n contratos
 */
public class Customer {

    private String name;
    private List<String> contracts;

    public Customer(String name) {
        this.name = name;
        this.contracts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getContracts() {
        return Collections.unmodifiableList(contracts);
    }

    public Customer add(String contract) {
        if (contract != null) {
            contracts.add(contract);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", contracts=" + contracts.size() +
                '}';
    }
}
