package com.quikyy.UTILS.CurrentWeather;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@NoArgsConstructor
@Component
public class CurrentWeather {
    @Value("${myapp.details.weatherApiKey}")
    private String apikey;

    public Optional<String> getTemperatureFromAPI(HttpServletResponse response) {
        Optional <String> temp = Optional.empty();
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
                StringBuilder stringBuilder = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                   stringBuilder.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(String.valueOf(stringBuilder));
                JSONObject obj = (JSONObject) data_obj.get("main");

                temp = Optional.of(String.valueOf(obj.get("temp")));
                temp = formatTemperature(temp);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        saveWeatherToUserCookies(response, temp);
        return temp;
    }

    public Optional<String> formatTemperature(Optional<String> temp){
        int indexOfDotInTemp = temp.get().indexOf(".");
        temp = Optional.of(temp.get().substring(0, indexOfDotInTemp) + "??C");
        return temp;
    }

    public void saveWeatherToUserCookies(HttpServletResponse response, Optional <String> temp){
        if(temp.isPresent()){
            Cookie cookie = new Cookie("weatherTemp", temp.get());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }
    }

    public String getWeatherFromUserCookies(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Optional<String> weatherTemp = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("weatherTemp")).map(cookie -> cookie.getValue()).findAny();
            if (weatherTemp.isPresent()) {
                return weatherTemp.get();
            } else {
                if (getTemperatureFromAPI(response).isPresent()) {
                    return getTemperatureFromAPI(response).get();
                } else {
                    return "";
                }
            }
        }
        else {
            if (getTemperatureFromAPI(response).isPresent()) {
                return getTemperatureFromAPI(response).get();
            } else {
                return "";
            }
        }

        }


}