package ru.snowreplicator.order_approving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderApprovingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApprovingApplication.class, args);
    }

}
