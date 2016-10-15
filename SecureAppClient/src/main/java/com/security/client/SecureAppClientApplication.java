package com.security.client;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecureAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppClientApplication.class, args);
		System.out.println("Connecting to secure site");
		
		HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);
		
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Aman\\STSProj\\AppSecurity\\clientkeystore.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "client");
		System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Aman\\STSProj\\AppSecurity\\clienttruststore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "client");
		
		RestTemplate restTemp = new RestTemplate();
		
		String greetings = restTemp.getForObject("https://localhost:8443/SecureAppServer/test/security/hello/aman123", String.class);
		
		System.out.println("Received greetings from secured server ---> " + greetings );
	}
}
