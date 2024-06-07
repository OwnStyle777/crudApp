package org.example;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}