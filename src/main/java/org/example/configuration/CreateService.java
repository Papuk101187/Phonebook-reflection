package org.example.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.configuration.ApplicationGetPropertys;
import org.example.services.ContactService;
import org.example.services.UsersService;
import org.example.services.implementation.contact.ApiContactService;
import org.example.services.implementation.contact.FileContactService;
import org.example.services.implementation.contact.InMemoryContactService;
import org.example.services.implementation.user.ApiUserService;

import java.net.http.HttpClient;

@Data
public class CreateService {

    ApplicationGetPropertys properties;

    public ContactService getContactservice() {
        return contactservice;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    ContactService contactservice;
    UsersService usersService ;

    ObjectMapper objectMapper = new ObjectMapper();
    HttpClient client = HttpClient.newBuilder().build();


    public CreateService(ApplicationGetPropertys prop) {
        this.properties = prop;
    }


    public void BuildService(){
        if(properties.getWorkmode()=="file"){
            contactservice = new FileContactService(properties.getFile());
        }

        switch (properties.getWorkmode()) {

            case "file":
                contactservice = new FileContactService(properties.getFile());
                break;
            case "api":
                usersService = new ApiUserService(
                        properties.getBaseURLregistration(),
                        properties.getBaseURLlogin(),
                        objectMapper,
                        client);
                contactservice= new ApiContactService(
                        usersService,
                        objectMapper,
                        client,properties.getBaseURLadd(),
                        properties.getBaseURLsearch(),
                        properties.getBaseURLget());
                break;
            case "memory":
                contactservice = new InMemoryContactService();

        }
    }
    }


