package com.epam.maven;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Request class, creates a request and transforms it into string if succeeded
 *
 * @author Andrei_Gordeev
 */

public class Request {
    private static final Logger log = Logger.getLogger(Request.class); /** Output/logging at C:/TMP */

    /**
     * bashQuote - string containing URL. Response will contain either a null string, or a null errorCode.
     */
    public static Response run(String bashQuote) {
        Integer code = null;
        String message = null;
        try {
            URL url = new URL(bashQuote);
            StringBuffer content = new StringBuffer();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            message = content.toString();
        } catch (MalformedURLException e) {
            code = 1;
        } catch (IOException e) {
            code = 2;
        } finally {
            return new Response(code, message);
        }
    }
}
