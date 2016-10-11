# springbootssl

This repository contains 2 spring boot projects to test mutual authentication (2 way)
```
Keytool Commands

1) Generate server key and self signed server certificate

keytool -genkey -alias serverkey -keystore C:\Users\Aman\STSProj\AppSecurity\serverkeystore.p12 -keyalg RSA -storetype PKCS12

2) Generate client key and self signed client certificate

keytool -genkey -alias clientkey -keystore C:\Users\Aman\STSProj\AppSecurity\clientkeystore.p12 -keyalg RSA -storetype PKCS12

3) Export the server certificate

keytool -export -alias serverkey -file C:\Users\Aman\STSProj\AppSecurity\servercert.cer -keystore C:\Users\Aman\STSProj\AppSecurity\serverkeystore.p12

4) Export the client certificate

keytool -export -alias clientkey -file C:\Users\Aman\STSProj\AppSecurity\clientcert.cer -keystore C:\Users\Aman\STSProj\AppSecurity\clientkeystore.p12

5) Import the server certificate into client truststore

keytool -importcert -file C:\Users\Aman\STSProj\AppSecurity\servercert.cer -keystore C:\Users\Aman\STSProj\AppSecurity\clienttruststore.p12 -alias servercert

6) Import the client certificate into server truststore

keytool -importcert -file C:\Users\Aman\STSProj\AppSecurity\clientcert.cer -keystore C:\Users\Aman\STSProj\AppSecurity\servertruststore.p12 -alias clientcert

```
