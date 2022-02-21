package dev.skiff.dungeoncrawler.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

public class APIRequestUtil {
    public static String getAPIJson(String u) throws IOException {
        URL url = new URL(u);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection conn = (HttpURLConnection) urlConnection;
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        StringBuilder result = new StringBuilder();
        if (responseCode == 200) {
            Scanner s = new Scanner(url.openStream());
            while (s.hasNext()) {
                result.append(s.nextLine());
            }
        } else {
            throw new RuntimeException("Failed to fetch JSON from URL. Response code: " + responseCode);
        }
        return result.toString();
    }

    public static JSONObject parseJSONString(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(json);
    }

    public static JSONObject getRandomJSONObject(JSONObject jObj) throws ParseException {
        JSONArray jArray = (JSONArray) jObj.get("results");
        Random rng = new Random();
        return (JSONObject) jArray.get(rng.nextInt(jArray.size()));
    }
}
