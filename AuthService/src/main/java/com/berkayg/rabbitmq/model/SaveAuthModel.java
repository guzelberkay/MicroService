package com.berkayg.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ÖNEMLİ!!!
 * Modeller mutlaka serileştirilebilir olmalıdır
 * Ayrıca paket ismi dahil olmak üzere bu sınıfın aynısı iletilen serviste de olmalıdır.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaveAuthModel implements Serializable {
    String username;
    String email;
    Long authId;
}
