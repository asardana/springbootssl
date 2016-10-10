# springbootssl
Contains spring boot projects to test mutual authentication

Keytool Commands

1) Generate a Key and self signed certificate(.keystore file)

keytool.exe -genkey -alias tomcat -keyalg RSA

2) List the content of a keystore

keytool -list -v -keystore c:\users\aman\.keystore

3)  Export the certificate from an existing keystore

keytool -export -alias tomcat -file c:\users\aman\tomcat.cer -keystore c:\users\aman\.keystore

4)  Import the existing key from JKS keystore to PKCS12 keystore (JKS -> PKCS12)

keytool -v -importkeystore -srckeystore C:\Users\Aman\STSProj\AppSecurity\truststore.jks -srcstoretype JKS -srcalias tomcatclient -destkeystore C:\Users\Aman\STSProj\AppSecurity\truststore.p12 -deststoretype PKCS12 -destalias tomcatclient

5) Import the certificate to a PKCS12 trust store 

keytool -importcert -file c:\users\aman\tomcat.cer -keystore C:\Users\Aman\STSProj\AppSecurity\truststore.p12  -alias "tomcatclient"

6) Generate a key and certificate in PKCS12 format

keytool -genkey -alias clientcert -keystore C:\Users\Aman\STSProj\AppSecurity\clientkeystore.p12 -keyalg RSA -keysize 2048 -storetype PKCS12
