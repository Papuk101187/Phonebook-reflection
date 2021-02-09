package org.example.configuration;

import org.example.entity.Contact;

import java.io.*;
import java.util.Properties;

public class ConfigLoader {

    public ApplicationGetPropertys load(String file) throws IOException {

        Properties properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ApplicationGetPropertys applicationGetPropertys = new ApplicationGetPropertys();
        applicationGetPropertys.setBaseURLlogin(properties.getProperty("api.base-authorization"));
        applicationGetPropertys.setBaseURLregistration(properties.getProperty("api.base-registration"));

        applicationGetPropertys.setBaseURLadd(properties.getProperty("api.base-addcontact"));
        applicationGetPropertys.setBaseURLsearch(properties.getProperty("api.base-searchcontact"));
        applicationGetPropertys.setBaseURLget(properties.getProperty("api.base-getcontact"));
        applicationGetPropertys.setWorkmode(properties.getProperty("app.service.workmode"));
        applicationGetPropertys.setFile(properties.getProperty("file.path"));





        return applicationGetPropertys;
    }

    public String getProfile() {
        String profile = System.getProperty("app.profile");
        if (profile == null) {
            return "dev";
        }
        return profile;
    }


}
