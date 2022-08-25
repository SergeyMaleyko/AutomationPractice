package com.issoft.training.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    private static final String propFile = "src/test/resources/conf.properties";

    static {
        try {
            fileInputStream = new FileInputStream(propFile);
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }
    /**
     * returns a config value
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key); }

    public static void setProperty(String key, String value) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(propFile);
            PROPERTIES.setProperty(key, value);
            PROPERTIES.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}