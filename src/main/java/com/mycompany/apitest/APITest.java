package com.mycompany.apitest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class fetches random dog image information from 'The Dog API'.
 * @author Alex Jasper
 */
public class APITest { 
    public static void main(String[] args) throws Exception {
        String jsonResponse = fetchFromAPI("https://api.thedogapi.com/v1/images/search");
        parseAndPrintDogInfo(jsonResponse);
    }

    /**
     * Fetches data from the given API URL.
     * 
     * @param apiUrl The URL of the API to fetch data from.
     * @return A String containing the raw JSON response from the API.
     * @throws Exception If there is an issue connecting to or reading from the API.
     */
    private static String fetchFromAPI(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream responseStream = connection.getInputStream();
        Scanner scanner = new Scanner(responseStream).useDelimiter("\\A");  // "\\A" matches the beginning of the input, effectively reading the whole stream

        return scanner.hasNext() ? scanner.next() : "";

    }

    /**
     * Parses the provided JSON response to extract dog image information and print it.
     * 
     * @param jsonResponse A String containing the raw JSON response to be parsed.
     */
    private static void parseAndPrintDogInfo(String jsonResponse) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        JSONObject dogObject = jsonArray.getJSONObject(0);

        System.out.println("ID: " + dogObject.getString("id"));
        System.out.println("Image URL: " + dogObject.getString("url"));
        System.out.println("Width: " + dogObject.getInt("width"));
        System.out.println("Height: " + dogObject.getInt("height"));
    }
}
