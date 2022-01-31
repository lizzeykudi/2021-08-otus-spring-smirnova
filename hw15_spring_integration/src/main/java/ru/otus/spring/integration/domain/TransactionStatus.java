package ru.otus.spring.integration.domain;

public class TransactionStatus {

    private final String status;

    public TransactionStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
