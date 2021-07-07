# kgssecretkey

This fancy bit of software encrypts or decrypts a file using a secret key from a JKS

## Build

Just run `mvn clean package`

## Prerequesites

Initialize a keystore file. **Reagardless what you set on the other fields in key, the alias must be "keytool"**
`keytool -genkeypair -alias keytool -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=Markus Kremer, OU=voidmain, O=voidmain, L=Langenscheid, ST=Unknown, C=DE" -validity 100 -keystore keystore.jks -storepass 123456`

## Usage

The app consumes five commandline parameters.

1. Appmode: which could be "e" for encryption or "d" for decryption
2. Password: the keystores password
3. Keystore file path
4. Input file path
5. Output file path

so a command would be something
like: `java -jar app.jar e mypassword c:\temp\keystore.jks c:\temp\in.put c:\Temp\out.put`

