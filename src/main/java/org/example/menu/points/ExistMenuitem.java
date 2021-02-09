package org.example.menu.points;

import org.example.entity.User;
import org.example.menu.MenuItem;
import org.example.services.ContactService;
import org.example.services.UsersService;

import java.io.IOException;

public class ExistMenuitem implements MenuItem {

    ContactService service;

    public ExistMenuitem(ContactService contactService) {
    }


    @Override
    public void doAction() throws IOException, InterruptedException {

    }

    @Override
    public String getName() {
        return "Выйти из програмы";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
