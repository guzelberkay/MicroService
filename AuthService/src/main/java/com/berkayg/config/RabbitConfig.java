package com.berkayg.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * Öncelikle bir exchange kuyruk ve bindingKey oluşturmalıyız.
     */
    String directExchangeAuth ="direct-exchange-auth";
    String queueAuth ="queue-auth";
    String saveBindingKey ="save-auth-key";

    /**
     * DirectExchange oluşturup yönetimini @Bean anotasyonu ile Springe bırakıyoruz.
     * @return
     */
    @Bean
   public DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }

    /**
     * Kuyruk oluşturup yönetimini @Bean anotasyonu ile Springe bırakıyoruz.
     * @return
     */
    @Bean
   public Queue queueAuth(){
        return new Queue(queueAuth);
    }

    @Bean
    public Binding bindingSaveDirectExchange(Queue queueAuth, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueAuth).to(directExchangeAuth).with(saveBindingKey);
    }
}
