package org.example.configuration.reflection;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Properties;

public class BuildFileReflection {

    String file;
    ConfigLoader configLoader = new ConfigLoader();
    HttpClient httpClient = HttpClient.newBuilder().build();


    public String buildFile() throws IllegalAccessException, IOException {
        ConfigFile propertys = configLoader.getSystemProps(ConfigFile.class);
        file = "app-"+propertys.getProfile()+".properties";
        System.out.println("Load file "+file);

        Properties properties = new Properties();
        properties.load(new FileInputStream(file)); // добавляю в properties
        return file;

}


}
