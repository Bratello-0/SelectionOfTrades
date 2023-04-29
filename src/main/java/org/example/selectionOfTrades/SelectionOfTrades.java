package org.example.selectionOfTrades;

import org.example.selectionOfTrades.configs.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class SelectionOfTrades {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SelectionOfTrades.class, args);
    }
}