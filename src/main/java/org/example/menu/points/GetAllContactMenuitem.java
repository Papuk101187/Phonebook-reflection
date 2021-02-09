package org.example.menu.points;

import org.example.entity.Contact;
import org.example.entity.User;
import org.example.menu.MenuItem;
import org.example.services.ContactService;
import org.example.services.UsersService;

import java.io.IOException;

public class GetAllContactMenuitem implements MenuItem {

    ContactService service;

    public GetAllContactMenuitem(ContactService contactService) {
        this.service=contactService;
    }

    @Override
    public void doAction() throws IOException, InterruptedException {
        java.util.List<Contact> contactList;
        contactList =  service.getAllcontact();
        System.out.println(contactList);
    }

    @Override
    public String getName() {
        return "Получение всех контактов ";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
