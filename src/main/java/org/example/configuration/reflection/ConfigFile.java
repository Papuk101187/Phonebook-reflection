package org.example.configuration.reflection;


import lombok.Data;

@Data
public class ConfigFile {

    @Prop("app.profile")
    private String profile;
}
