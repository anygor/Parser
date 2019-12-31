package parser;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class ParserTest {

    private static FileInputStream fileInputStream;
    private static Properties property;
    private static String input;
    private static String output;

    @BeforeClass
    public static void setUp(){
        try {
            fileInputStream = new FileInputStream("src/test/resources/config.properties");
            property = new Properties();
            property.load(fileInputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_firstParse(){
        output = property.getProperty("db.output1");
        input = property.getProperty("db.input1");
        input = Parser.parse(input);
        assertEquals(input, output);
    }

    @Test
    public void test_emptyParse(){
        output = property.getProperty("db.InvalidOutput");
        input = property.getProperty("db.emptyInput");
        input = Parser.parse(input);
        assertEquals(output, input);
    }

    @Test
    public void test_invalidParse(){
        output = property.getProperty("db.InvalidOutput");
        input = property.getProperty("db.InvalidInput");
        input = Parser.parse(input);
        assertEquals(output, input);
    }
}
