package org.example.services.implementation.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Contact;
import org.example.services.implementation.dto.RequestContactName;
import org.example.services.implementation.dto.ResponceContacts;
import org.example.services.ContactService;
import org.example.services.UsersService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiContactService implements ContactService {

    UsersService usersServic;
    ObjectMapper objectMapp;
    public String urladd;
    public String urlget;
    public String urlsearch;

    HttpClient httpClie;
    boolean check=false;

    public boolean checkingService() {
        return check;
    }

    public ApiContactService(UsersService usersService,
                             ObjectMapper objectMapper,
                             HttpClient client,
                             String urladdcontact,
                             String urlsearchcontact,
                             String urlget) {
        this.usersServic = usersService;
        this.check=true;
        this.objectMapp=objectMapper;
        this.httpClie=client;
        this.urladd=urladdcontact;
        this.urlsearch=urlsearchcontact;
        this.urlget=urlget;

    }

    public String add(Contact contact1) throws IOException, InterruptedException {


        String Token = usersServic.getToken();
        String status;

        System.out.println("Token "+Token);


        String uzers = objectMapp.writeValueAsString(contact1);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/add"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + Token)
                .POST(HttpRequest.BodyPublishers.ofString(uzers))
                .build();

        HttpResponse<String> response = httpClie.send(request, HttpResponse.BodyHandlers.ofString());
        String responce = response.body();

        if (responce.contains("ok")) {
            status = "Контакт добавлен на сервер";
        } else {
            status = "Контакт не добавлен на сервер";
        }

        System.out.println(responce);

return status;
    }

    public List<Contact> searchContact(String name) throws IOException, InterruptedException {

        List<Contact> contacts = null;

        RequestContactName requestContactName = new RequestContactName();
        requestContactName.setName(name);


        ObjectMapper objectMapper = new ObjectMapper();
        String status;

        String uzers = objectMapper.writeValueAsString(requestContactName);
        System.out.println(uzers);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlsearch))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + usersServic.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(uzers))
                .build();

        HttpResponse<String> response = httpClie.send(request, HttpResponse.BodyHandlers.ofString());
        status = response.body();

        ResponceContacts usersService = objectMapper.readValue(status, ResponceContacts.class);
        contacts = usersService.getContacts();

        return contacts;

    }

    public List<Contact> getAllcontact() throws IOException, InterruptedException {


        ObjectMapper objectMapper = new ObjectMapper();
        List<Contact> contacts;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlget))
                .GET()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + usersServic.getToken())
                .build();

        HttpResponse<String> response = httpClie.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String status = response.body();


        ResponceContacts usersService = objectMapper.readValue(status, ResponceContacts.class);
        contacts = usersService.getContacts();

        return contacts;


    }
}
