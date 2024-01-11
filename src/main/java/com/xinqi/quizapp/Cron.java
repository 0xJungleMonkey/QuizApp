package com.xinqi.quizapp;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Cron {

    private final String backendUrl = "https://quiz-backend-r9pj.onrender.com/api/Question/questions?category=General%20Knowledge&difficulty=easy";

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 12 minutes
        scheduler.scheduleAtFixedRate(this::restartServer, 0, 2, TimeUnit.MINUTES);
    }

    private void restartServer() {
        try {
            URL url = new URL(backendUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Perform a GET request
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Server started");
            } else {
                System.out.println("Failed to restart server with status code: " + statusCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Error during restart: " + e.getMessage());
        }
    }
}

