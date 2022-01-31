package ru.otus.spring.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.TransactionRequest;
import ru.otus.spring.integration.domain.TransactionStatus;

import java.util.Collection;

@MessagingGateway
public interface Bank {

    @Gateway(requestChannel = "transferRequestChannel", replyChannel = "transferResponseChannel")
    Collection<TransactionStatus> process(Collection<TransactionRequest> transactionRequests);
}
