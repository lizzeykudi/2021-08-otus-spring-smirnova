package ru.otus.spring.integration.domain;

public class TransactionRequest {
    private final String request;

    public TransactionRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "request='" + request + '\'' +
                '}';
    }
}
