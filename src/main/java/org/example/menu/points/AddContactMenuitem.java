package org.example.menu.points;

import org.example.entity.Contact;
import org.example.entity.User;
import org.example.menu.MenuItem;
import org.example.services.ContactService;
import org.example.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddContactMenuitem implements MenuItem {

    ContactService service;

    public AddContactMenuitem(ContactService contactService) {
        this.service=contactService;
    }

    @Override
    public void doAction() throws IOException, InterruptedException {

        System.out.println("Введите type контакта [пример email]");
        BufferedReader t = new BufferedReader(new InputStreamReader(System.in)); //
        String type = t.readLine();
        System.out.println("Введите value контакта [пример vasia@i.ua]");
        BufferedReader v =new BufferedReader(new InputStreamReader(System.in)); //
        String value = v.readLine();
        System.out.println("Введите name контакта [пример vasia]");
        BufferedReader n = new BufferedReader(new InputStreamReader(System.in)); //
        String name = n.readLine();
        Contact contact = new Contact(type,value,name);

        String status = service.add(contact);
        System.out.println(status);


        }


    @Override
    public String getName() {
        return "Добавить контакт";
    }


    @Override
    public boolean closeAfter() {
        return false;
    }



    private User userAutorization(User newUz) throws IOException {

        System.out.println("Просим ввести данные вашего аккаунта");

        String login;
        String password;
        String date;
        login = getLogin();
        password = getPassword();
        date = getDate();
        User newUzer = new User(login,password,date);

        return newUzer;
    }

    private String getDate() throws IOException {
        System.out.println("Введите Вашу дату рождения в формате [yyyy-MM-dd] ");
        BufferedReader dat = new BufferedReader(new InputStreamReader(System.in));
        return dat.readLine();
    }

    private String getPassword() throws IOException {
        System.out.println("Введите password ");
        BufferedReader passw = new BufferedReader(new InputStreamReader(System.in)); //
        return passw.readLine();

    }

    private String getLogin() throws IOException {
        System.out.println("Введите login ");
        BufferedReader log = new BufferedReader(new InputStreamReader(System.in)); //
        return log.readLine();
    }

}
