/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encryption;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

/**
 *
 * @author BHATTI
 */
public class encrypt {
    
    private static String algorithm = "AES";
    private byte[] keyValue;
    
    public encrypt(String key){
        keyValue = key.getBytes();
    }
    
    public String encrypt(String toEncrypt) throws Exception {
      // create a binary key from the argument key (seed)
      SecretKeySpec key = generateKey();
  
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance(algorithm);
  
      // initialize the cipher with the key
      cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
  
      // enctypt!
      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
      String encryptedValue = Base64.getEncoder().encodeToString(encrypted); 
      return encryptedValue;
   }
  
   public String decrypt(String toDecrypt) throws Exception {
      // create a binary key from the argument key (seed)
      SecretKeySpec key = generateKey();
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance(algorithm);
  
      // initialize the cipher with the key
      cipher.init(Cipher.DECRYPT_MODE, (java.security.Key) key);
  
      // decrypt!
      byte[] decodedValue = new BASE64Decoder().decodeBuffer(toDecrypt);
      byte[] decValue = cipher.doFinal(decodedValue);
      String decryptedValue = new String(decValue);
      return decryptedValue;
   }
   
   private SecretKeySpec generateKey() throws Exception {
       SecretKeySpec key = new SecretKeySpec(keyValue,algorithm);
       return key;
   }

}
