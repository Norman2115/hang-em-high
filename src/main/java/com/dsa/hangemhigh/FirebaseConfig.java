package com.dsa.hangemhigh;

import java.io.IOException;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

  @PostConstruct
  public FirebaseApp firebaseApp() {
    // Change the path after adding new private key
    ClassPathResource serviceAccount = new ClassPathResource("firebase/hang-em-high-firebase-adminsdk-mj3e9-3025f1eb3f.json");

    try {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
          .setDatabaseUrl("https://default.firebaseio.com")
          .build();

      FirebaseApp app = FirebaseApp.initializeApp(options);
      System.out.println("FirebaseApp initialized with name: " + app.getName());
      return app;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to initialize FirebaseApp", e);
    }
  }
}