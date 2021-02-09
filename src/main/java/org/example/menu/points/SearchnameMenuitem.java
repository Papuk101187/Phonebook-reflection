package org.example.menu.points;

import org.example.entity.User;
import org.example.menu.MenuItem;
import org.example.services.ContactService;
import org.example.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchnameMenuitem implements MenuItem {

    ContactService service;

    public SearchnameMenuitem(ContactService contactService) {

        this.service=contactService;

    }

    @Override
    public void doAction() throws IOException, InterruptedException {
        System.out.println("Введите имя для поиска контакта");
        BufferedReader names = new BufferedReader(new InputStreamReader(System.in)); //
        String name = names.readLine();

        System.out.println(service.searchContact(name));

        System.out.println("Все найденные контакты  " + null);
    }

    @Override
    public String getName() {
        return "Поиск контакта по имени";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
