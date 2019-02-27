package com.esliceu.socket.socketserver;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocketServerApplication {

    @Value("${socket.io.port}")
    private Integer socketIOPort;

    public static void main(String[] args) {
        SpringApplication.run(SocketServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(){
        return args -> {
            Configuration config = new Configuration();
            config.setHostname("localhost");
            config.setPort(socketIOPort);

            final SocketIOServer server = new SocketIOServer(config);
            server.addEventListener("chatMessage", ChatObject.class, (client, data, ackRequest) -> {
                // broadcast messages to all clients
                server.getBroadcastOperations().sendEvent("chatMessage", data);
            });

            server.start();


        };
    }

}
