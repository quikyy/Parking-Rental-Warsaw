package com.quikyy.UTILS.CurrentWeather;

import com.quikyy.UTILS.AppDetailsRepostiory;
import lombok.AllArgsConstructor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
@Service
public class CurrentWeather {
    private final AppDetailsRepostiory appDetailsRepostiory;
    Optional <BigDecimal> temp = Optional.empty();

    public void getCurrentWeatherFromAPI() {
        System.out.println("Looking for weather....");
        String apikey = appDetailsRepostiory.findAppDetailsByTypeEquals("api_key").getDetails();
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
    }

    public void saveWeatherToUserCookie(HttpServletResponse response, Model model){
        getCurrentWeatherFromAPI();
        if(temp.isPresent()){
            Cookie cookie = new Cookie("weatherTemp", String.valueOf(temp.get()));
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            model.addAttribute("currentWeather", temp.get());

        }
        else {
            model.addAttribute("currentWeather", "0");
        }
    }

    public void getWeather(HttpServletResponse response, HttpServletRequest request, Model model){
        Optional<String> weather = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("weatherTemp")).map(cookie -> cookie.getValue()).findAny();

        if(weather.isPresent()){
            model.addAttribute("currentWeather", weather.get());
        }
        else {
            saveWeatherToUserCookie(response, model);
        }
    }


}