package com.issoft.training.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties properties;
    private static final String propFile = "src/test/resources/conf.properties";

    static {
        try {
            fileInputStream = new FileInputStream(propFile);
            properties = new Properties();
            properties.load(fileInputStream);
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
        return properties.getProperty(key); }

    public static void setProperty(String key, String value) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(propFile);
            properties.setProperty(key, value);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}