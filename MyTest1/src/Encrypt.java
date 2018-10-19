import java.security.*;

import javax.crypto.Cipher;

/** 
 * 功能简述: 使用RSA非对称加密/解密. 
 * @throws Exception 
 */  
class a{ 
public void test04() throws Exception {  
    String plainText = "Hello , world !";  
      
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");  
    keyPairGenerator.initialize(1024);  
    KeyPair keyPair = keyPairGenerator.generateKeyPair();  
      
    PublicKey publicKey = keyPair.getPublic();  
    PrivateKey privateKey = keyPair.getPrivate();  
      
    Cipher cipher = Cipher.getInstance("rsa");  
    SecureRandom random = new SecureRandom();  
      
    cipher.init(Cipher.ENCRYPT_MODE, privateKey, random);  
    byte[] cipherData = cipher.doFinal(plainText.getBytes());  
    System.out.println("cipherText : " + new BASE64Encoder().encode(cipherData));  
    //gDsJxZM98U2GzHUtUTyZ/Ir/NXqRWKUJkl6olrLYCZHY3RnlF3olkWPZ35Dwz9BMRqaTL3oPuyVq  
    //sehvHExxj9RyrWpIYnYLBSURB1KVUSLMsd/ONFOD0fnJoGtIk+T/+3yybVL8M+RI+HzbE/jdYa/+  
    //yQ+vHwHqXhuzZ/N8iNg=  
  
    cipher.init(Cipher.DECRYPT_MODE, publicKey, random);  
    byte[] plainData = cipher.doFinal(cipherData);  
    System.out.println("plainText : " + new String(plainData));  
    //Hello , world !  
      
    Signature signature  = Signature.getInstance("MD5withRSA");  
    signature.initSign(privateKey);  
    signature.update(cipherData);  
    byte[] signData = signature.sign();  
    System.out.println("signature : " + new BASE64Encoder().encode(signData));  
    //ADfoeKQn6eEHgLF8ETMXan3TfFO03R5u+cQEWtAQ2lRblLZw1DpzTlJJt1RXjU451I84v3297LhR  
    //co64p6Sq3kVt84wnRsQw5mucZnY+jRZNdXpcbwh2qsh8287NM2hxWqp4OOCf/+vKKXZ3pbJMNT/4  
    ///t9ewo+KYCWKOgvu5QQ=  
  
    signature.initVerify(publicKey);  
    signature.update(cipherData);  
    boolean status = signature.verify(signData);  
    System.out.println("status : " + status);  
    //true  
}
}