package org.example.configuration.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConfigLoader {

    public void setValueObject(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Prop.class)) {
                Prop annotation = field.getAnnotation(Prop.class);
                String propName = annotation.value();
                String value = propName;
                field.setAccessible(true);
                field.set(obj, value);
            }
        }
    }


    private Object createObject(Class clazz) {
        try {
            Constructor constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractedProps(Object object, Properties properties) throws IllegalAccessException {

        Class clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Prop.class)) {
                Prop annotation = field.getAnnotation(Prop.class);
                String propName = annotation.value();
                String value = propName;
                String values = properties.getProperty(propName);
                System.out.println(propName);
                field.setAccessible(true);
                field.set(object, values);
            }
        }
    }

    public <T> T getSystemProps(Class<T> clazz) throws IllegalAccessException {
        Object object = createObject(clazz); // создали объект по получив конструктор

        extractedProps(object, System.getProperties()); // засетили получив из property
        return (T) object;
    }

    public <T> T getFileProps(Class<T> clazz, String file) {
        try {
            try (InputStream inputStream = new FileInputStream(file)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                Object object = createObject(clazz);
                extractedProps(object, properties);
                return (T) object;
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
