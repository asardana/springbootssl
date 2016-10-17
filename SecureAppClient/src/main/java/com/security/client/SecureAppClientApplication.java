package com.security.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecureAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppClientApplication.class, args);
		System.out.println("Connecting to secure site");

		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

		/*
		 * System.setProperty("javax.net.ssl.keyStore",
		 * "C:\\Users\\Aman\\STSProj\\AppSecurity\\clientkeystore.p12");
		 * System.setProperty("javax.net.ssl.keyStorePassword", "client");
		 * System.setProperty("javax.net.ssl.trustStore",
		 * "C:\\Users\\Aman\\STSProj\\AppSecurity\\clienttruststore.p12");
		 * System.setProperty("javax.net.ssl.trustStorePassword", "client");
		 */
		FileInputStream keystoreInputStream = null;
		FileInputStream truststoreInputStream = null;
		try {
			
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystoreInputStream = new FileInputStream("C:\\Users\\Aman\\STSProj\\AppSecurity\\clientkeystore.p12");
			keystore.load(keystoreInputStream, "client".toCharArray());
			
			keystoreInputStream.close();
			
			KeyStore trustore = KeyStore.getInstance(KeyStore.getDefaultType());
			truststoreInputStream = new FileInputStream("C:\\Users\\Aman\\STSProj\\AppSecurity\\clienttruststore.p12");
			trustore.load(truststoreInputStream, "client".toCharArray());
			
			SSLContext sslcontext = SSLContexts.custom().useProtocol("TLS")
					.loadKeyMaterial(keystore, "client".toCharArray())
					.loadTrustMaterial(trustore, null).build();

			HostnameVerifier hostnameverifier = null;
			
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslcontext,
					null, null, hostnameverifier);

			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);

			RestTemplate restTemp = new RestTemplate(requestFactory);

			String greetings = restTemp
					.getForObject("https://localhost:8443/SecureAppServer/test/security/hello/aman123", String.class);

			System.out.println("Received greetings from secured server ---> " + greetings);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (keystoreInputStream != null) {
				try {
					keystoreInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (truststoreInputStream != null) {
				try {
					truststoreInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
