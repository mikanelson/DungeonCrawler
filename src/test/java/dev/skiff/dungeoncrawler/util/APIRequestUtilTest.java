package dev.skiff.dungeoncrawler.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class APIRequestUtilTest {

    @Test
    public void canGetAPI() {
        String test = null;
        try {
            test = APIRequestUtil.getAPIJson("https://www.dnd5eapi.co/api/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(test);
    }

    @Test
    public void cannotGetAPI() {
        String test = null;
        try {
            test = APIRequestUtil.getAPIJson("invalid-url");
        } catch (IOException e) {
            // expected error
        }
        Assertions.assertNull(test);
    }

    @Test
    public void canParseJSON() {
        String test;
        JSONObject testJSONObj = null;
        try {
            test = APIRequestUtil.getAPIJson("https://www.dnd5eapi.co/api/");
            testJSONObj = APIRequestUtil.parseJSONString(test);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(testJSONObj);
    }

    @Test
    public void cannotParseJSON() {
        String test = "alkdfjaldskfas";
        JSONObject testJSONObj = null;
        try {
            testJSONObj = APIRequestUtil.parseJSONString(test);
        } catch (ParseException e) {
            //expected error
        }
        Assertions.assertNull(testJSONObj);
    }

}
