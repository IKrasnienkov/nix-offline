package ua.practice.reflection;


import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class InitAppProperties {
    private static Properties props;

    public static void init(AppProperties appProperties) {
        loadProperties();
        Class<?> clazz = appProperties.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                Class<?> typeOfField = field.getType();
                Object value = props.getProperty(propertyKey.value());
                try {
                    value = ConvertUtils.convert(value, typeOfField);
                    field.set(appProperties, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException | ConversionException e) {
                    System.out.println(e.getMessage());
                    try {
                        value = typeOfField.getDeclaredConstructor().newInstance();
                        field.set(appProperties, value);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException instantiationException) {
                        instantiationException.printStackTrace();
                    }
                }
            }
        }
    }

    private static void loadProperties() {
        props = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
