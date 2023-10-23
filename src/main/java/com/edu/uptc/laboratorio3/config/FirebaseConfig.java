package com.edu.uptc.laboratorio3.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SecurityUtil;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.*;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FirebaseConfig {
	
	@Bean
	public Firestore firestore() throws IOException {
		FileInputStream serviceAccount =
				new FileInputStream("./firebase-account-info.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();

				FirebaseApp firebaseApp=  FirebaseApp.initializeApp(options);
				return FirestoreClient.getFirestore(firebaseApp);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().authenticated();
		http.oauth2ResourceServer().jwt();
		return http.build();
	}  
}
