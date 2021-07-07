# kgssecretkey

This fancy bit of software encrypts or decrypts a file using a secret key from a JKS

## Build

Just run `mvn clean package`

## Prerequesites

Initialize a keystore file.
`keytool -genkeypair -alias keytool -keyalg RSA -keysize 2048 -dname "CN=Markus Kremer, OU=voidmain, O=voidmain, L=Langenscheid, ST=Unknown, C=DE"  -keypass 123456 -validity 100 -storetype JKS -keystore keystore.jks -storepass abcdef`

## Usage

The app consumes five commandline parameters.

1. Appmode: which could be "e" for encryption or "d" for decryption
2. Password: the keystores password
3. Keystore file path
4. Input file path
5. Output file path

so a command would be something
like: `java -jar app.jar e mypassword c:\temp\keystore.jks c:\temp\in.put c:\Temp\out.put`

