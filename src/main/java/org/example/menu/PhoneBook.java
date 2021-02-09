package org.example.menu;
import org.example.entity.User;
import org.example.menu.points.AddContactMenuitem;
import org.example.menu.points.ExistMenuitem;
import org.example.menu.points.GetAllContactMenuitem;
import org.example.menu.points.SearchnameMenuitem;
import org.example.services.ContactService;
import org.example.services.UsersService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {


    List<MenuItem> lists = new ArrayList();
    ContactService contactService;
    UsersService usersService;
    User user;
    String status;


    public PhoneBook(ContactService contactServ, UsersService usersService, User use) throws IOException {
        this.user = use;
        this.contactService = contactServ;
        this.usersService = usersService;
        lists.add(new AddContactMenuitem(contactService));
        lists.add(new SearchnameMenuitem(contactService));
        lists.add(new GetAllContactMenuitem(contactService));
        lists.add(new ExistMenuitem(contactService));
    }


    private void showMenu() throws IOException, InterruptedException {
        Menu menu = new Menu(lists);
        menu.starting();

    }


    private void runProgram() throws IOException, InterruptedException {


        while (true) {

            System.out.println("PhoneBook.runProgram");

            if (contactService.checkingService() == true) {
                user = getDataUser(user);
                if (usersService.login(user) == null) {
                    System.out.println("Логин отсутсвует");
                    System.out.println("Просим ввести данные для регистрации");
                    user = getDataUser(user);
                    status = usersService.register(user);
                    System.out.println(status);
                }{
                    showMenu();
                }

            }else {
                showMenu();
            }

        }

    }


    private User getDataUser(User user) throws IOException {
        getText("Просим ввести данные аккаунта");
        String login;
        String password;
        String date;
        login = getLogin();
        password = getPassword();
        date = getDate();
        User us = user;

        us.setLogin(login);
        us.setPassword(password);
        us.setDate_born(date);
        return us;
    }

    private void getText(String s) {
        System.out.println(s);
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

    public void start() throws IOException, InterruptedException {
        runProgram();
    }


}
