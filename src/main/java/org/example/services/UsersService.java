package org.example.services;

import org.example.entity.User;

import java.io.IOException;
import java.util.List;

public interface UsersService {

    String getToken();
    String register(User user) throws IOException, InterruptedException;
    String login(User user) throws IOException, InterruptedException;

}
