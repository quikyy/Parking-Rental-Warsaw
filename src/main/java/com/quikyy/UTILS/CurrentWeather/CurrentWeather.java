package com.quikyy.UTILS.CurrentWeather;

import com.quikyy.UTILS.AppDetailsRepostiory;
import lombok.AllArgsConstructor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
@Service
public class CurrentWeather {
    private final AppDetailsRepostiory appDetailsRepostiory;
    public static final BigDecimal temp0 = new BigDecimal(0);

    public BigDecimal getCurrentWeather() {
        String apikey = appDetailsRepostiory.findAppDetailsByTypeEquals("api_key").getDetails();
        Optional <BigDecimal> temp = Optional.empty();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Warsaw&units=metric&appid=" + apikey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("HttpResponseCode: " + responseCode);
            }
            else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                JSONParser parser = new JSONParser();

                JSONObject data_obj = (JSONObject) parser.parse(inline);

                JSONObject obj = (JSONObject) data_obj.get("main");

                temp = Optional.of(new BigDecimal(String.valueOf(obj.get("temp"))).setScale(0, RoundingMode.FLOOR));
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(temp.isPresent()){
            return temp.get();
        }
        else {
            return null;
        }

    }
}