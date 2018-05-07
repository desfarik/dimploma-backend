package com.medical.area;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MedicalAreaJavaApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MedicalAreaJavaApplication.class, args);
    }

    /**
     * Method that runs on app initialization. It will parse and insert the questions in the DB
     * on every app initialization
     */
    @Override
    public void run(String... strings) throws Exception {
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
