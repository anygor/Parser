package com.epam.maven;

import org.apache.log4j.Logger;
import parser.Parser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/** Main class, initiates the whole program
 * @author Andrei Gordeev
 */
public class Main {
    /** Logger is used for output and logging to C:/TMP */
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String args[]) {
        int quoteNumber;
        String content;
        String bash;
        Response response;

        Scanner input;

        FileInputStream fileInputStream;
        Properties property = new Properties();

        try{
            do {
                log.info("Set the number of quote to read, else type '-1' to quit");
                input = new Scanner(System.in);
                quoteNumber = input.nextInt();
                if (quoteNumber == -1) break;
                fileInputStream = new FileInputStream("src/main/resources/config.properties");
                property.load(fileInputStream);
                bash = property.getProperty("db.url");
                bash += Integer.toString(quoteNumber);
                response = Request.run(bash);
                if (response.getErrorCode() != null) {
                    log.error("Error code: "+ response.getErrorCode());
                }
                else {
                    content = response.getResponseContent();
                    String quote = new String(response.getResponseContent());
                    quote = Parser.parse(quote);
                    log.info("\n" + quote);
                }
            } while (quoteNumber != -1);
        }
        catch(FileNotFoundException e) {
            log.error("No config file found");
        }
        catch (IOException e){
            log.error("Input/Output Error");
        }
    }
}
