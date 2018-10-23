/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author 2124519
 */
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
    @Value("${local.server.port}")
    private int port;
    
    @Value("${local.server.host}")
    private String host;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //CloudAmqp
        config.enableStompBrokerRelay("/topic");
        config.setApplicationDestinationPrefixes("/app");        
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOrigins("*").withSockJS();
        
    }
    

}
