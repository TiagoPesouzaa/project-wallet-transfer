package com.picpay.services;

import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.user.User;
import com.picpay.dtos.TransactionDTO;
import com.picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    // comunicação http entre serviços
    @Autowired
    private RestTemplate restTemplate;


    public Transaction createTransaction (TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized){
            throw new Exception("Transação não autorizada!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender,"Transação realizada com sucesso.");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso.");
        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
//        try {
//            ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
//
//            return response.getStatusCode() == HttpStatus.OK
//                    && "success".equals(response.getBody().get("status"));
//        } catch (Exception e) {
//            System.out.println("Erro ao consultar serviço de autorização: " + e.getMessage());
//            return false; // Não autoriza se der erro
//        }

        // simulando autorização de transação, pois a API está indisponível
        System.out.println("Transação Autorizada...");
        return true;
    }

}
