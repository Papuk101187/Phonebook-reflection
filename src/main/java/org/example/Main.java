package org.example;

import org.example.configuration.ApplicationGetPropertys;
import org.example.configuration.ConfigLoader;
import org.example.configuration.CreateService;
import org.example.configuration.reflection.BuildFileReflection;
import org.example.entity.User;
import org.example.menu.PhoneBook;
import org.example.services.ContactService;
import org.example.services.UsersService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException {


        ConfigLoader configLoader = new ConfigLoader(); // загружает properties из файла


        BuildFileReflection buildFileReflection = new BuildFileReflection(); // создали класс который с помощью рефлексии получает файл
        String configFile = buildFileReflection.buildFile();

        System.out.println("configFile = "+configFile);
        ApplicationGetPropertys properties = configLoader.load(configFile); // единый класс с properties

        CreateService createService = new CreateService(properties); // создаём сервис по нашим properties
        createService.BuildService(); // строим сервис

        ContactService contactService = createService.getContactservice(); // получаем сервис по контактам
        UsersService usersService = createService.getUsersService(); // получаем сервис по user

        PhoneBook phoneBook = new PhoneBook(contactService, usersService, new User()); // наша телефонная книга
        phoneBook.start();


    }

}
