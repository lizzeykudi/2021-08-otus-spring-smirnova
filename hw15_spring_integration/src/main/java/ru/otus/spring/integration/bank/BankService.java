package ru.otus.spring.integration.bank;

import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.TransactionRequest;
import ru.otus.spring.integration.domain.TransactionStatus;

@Service
public class BankService {

    public TransactionStatus transfer(TransactionRequest transactionRequest) throws Exception {
        System.out.println("transactionRequest = " + transactionRequest);
        TransactionStatus status = new TransactionStatus("ok");
        System.out.println("status = " + status);
        return status;
    }
}
