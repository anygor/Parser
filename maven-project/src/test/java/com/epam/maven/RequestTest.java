package com.epam.maven;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class RequestTest {

    private static FileInputStream fileInputStream;
    private static Properties property;
    private static String result1;
    private static String cash;

    @BeforeClass
    public static void setUp(){
        try {
            result1 = new String();
            fileInputStream = new FileInputStream("src/test/resources/config.properties");
            property = new Properties();
            property.load(fileInputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_firstResponse(){
        Response test_response = Request.run((property.getProperty("db.url1")));
        cash = test_response.getResponseContent();
        int rightMargin = 25;
        int leftMargin = -24;
        assertEquals(cash.substring(cash.indexOf("og:description") + rightMargin, cash.indexOf("og:image") + leftMargin), property.getProperty("db.result1"));
    }
    @Test
    public void test_failedResponse(){
        Response test_response = Request.run((property.getProperty("db.url2")));
        cash = test_response.getResponseContent();
        assertTrue(!cash.contains("og:description"));
    }
}

