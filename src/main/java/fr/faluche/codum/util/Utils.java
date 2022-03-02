package fr.faluche.codum.util;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility Class");
    }
    private static final List<String> city = new ArrayList<>(Arrays.asList("city","villes","ville","cities"));



    public static boolean checkIfResourceExists(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("HEAD");
        int code = conn.getResponseCode();
        conn.disconnect();
        return code == 200;
    }

    //TODO finish function
    public boolean getName(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("HEAD");

        //BufferedReader reader = new BufferedReader();

        conn.disconnect();
        return false;

    }



    public static boolean isCityTopic(String name){
        return city.contains(name.toLowerCase());

    }
}
