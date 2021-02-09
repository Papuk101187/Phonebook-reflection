package org.example.services.implementation.contact;

import org.example.entity.Contact;
import org.example.services.ContactService;

import java.util.ArrayList;
import java.util.List;

public class InMemoryContactService implements ContactService {

    List<Contact> contacts = new ArrayList<>();


    @Override
    public String add(Contact contact) {
        String status;
        if (contacts.add(contact) == true) {
            status = "Контакт в массив добавлен";
        } else {
            status = "Контакт в массив не добавлен";
        }
        return status;
    }

    @Override
    public List<Contact> searchContact(String contactName) {

        List<Contact> listcontact = new ArrayList<>();

        System.out.println("А "+contactName);


        String conta = contactName;

        for (Contact con : contacts) {

            if (con.getName().contains(conta)) {
                System.out.println("con.getName() "+con.getName());
                listcontact.add(con);
            }

        }
        return listcontact;
    }

    @Override
    public List<Contact> getAllcontact() {

        return contacts;
    }

    @Override
    public boolean checkingService() {
        return false;
    }


}
