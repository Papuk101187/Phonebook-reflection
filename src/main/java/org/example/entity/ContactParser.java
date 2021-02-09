package org.example.entity;

import org.example.entity.Contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactParser {


    public Contact deserializationСontact(String contact) {
        Contact contact1 = null;
        String contacts = contact;
        Pattern pattern = Pattern.compile("([a-zA-Z]+)..([a-zA-Z]+)..(([a-zA-Z].+))..");

        Matcher matcher = pattern.matcher(contacts);
        while (matcher.find()) {
            contact1 = new Contact(matcher.group(2), matcher.group(3), matcher.group(1));
        }

        return contact1;
    }


    public String deserializationСontact(Contact contact) {
        String type = contact.type;
        String value = contact.value;
        String name = contact.name;

        return name + " [" + type + " :" + value + "],";

    }




}
