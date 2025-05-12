package com.picpay.services;

import com.picpay.domain.user.User;
import com.picpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;


    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(
//                    "https://util.devi.tools/api/v1/notify",
//                    notificationRequest,
//                    String.class
//            );
//
//            if (response.getStatusCode() != HttpStatus.OK) {
//                System.out.println("Erro ao enviar notificação. Status: " + response.getStatusCode());
//                throw new Exception("Serviço de notificação está fora do ar.");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Falha ao enviar notificação: " + e.getMessage());
//            throw new Exception("Falha no serviço de notificação.");
//        }

        // simulando um pagamento, pois a API está indisponível
        System.out.println("Pagamento realizado com sucesso.");
    }
}
