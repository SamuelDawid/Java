package com.YellowFlash.Library.Manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LibraryManagerApplication {

	public static void main(String[] args) {

        SpringApplication.run(LibraryManagerApplication.class, args);

	}
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }


}
