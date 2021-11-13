package fr.faluche.codum.util;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility Class");
    }

    public static final boolean checkIfResourceExists(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("HEAD");
        int code = conn.getResponseCode();
        conn.disconnect();
        return code == 200;
    }
}
