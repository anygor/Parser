package parser;

/**
 * Parser class with one static method 'parse'
 * @author Andrei_Gordeev
 */

public class Parser {
    /** Gets rid of non-needed text of an HTTP request */
    public static String parse(String request){
        boolean isQuote = request.contains("og:description");
        String quote;
        int pos1;
        int pos2;
        int rightMargin = 25;
        int leftMargin = -24;
        if (isQuote) {
            pos1 = request.indexOf("og:description") + rightMargin;
            pos2 = request.indexOf("og:image") + leftMargin;
            quote = request.substring(pos1, pos2);
        }
        else quote = null;
        if (quote != null) {
                quote = quote.replaceAll("&#13;", "\n");
                quote = quote.replaceAll("&#10;", "");
                quote = quote.replaceAll("&quot;", "\"");
                quote = quote.replaceAll("&amp;", "&");
                quote = quote.replaceAll("&lt;", "<");
                quote = quote.replaceAll("&gt;", ">");
        }
        else quote = "Bash has no link to this quote";
        return quote;
    }
}
