package com.picpay.dtos;

import java.math.BigDecimal;

// campos imutaveis
public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
