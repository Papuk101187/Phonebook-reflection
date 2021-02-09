package org.example.services;

import org.example.entity.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactService {

    String add (Contact contact) throws IOException, InterruptedException;
    List<Contact> searchContact(String name)throws IOException, InterruptedException;
    List<Contact> getAllcontact() throws IOException, InterruptedException;
    public boolean checkingService();

}
