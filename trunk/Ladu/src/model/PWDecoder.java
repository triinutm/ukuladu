package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PWDecoder {

	
	 public String hashPassword(String password){
		  String hashedPassword=null;
		  if(password==null) return null;
		  try {
		   MessageDigest digest=MessageDigest.getInstance("MD5");
		   digest.update(password.getBytes(), 0, password.length());
		   hashedPassword=new BigInteger(1,digest.digest()).toString(16);
		   
		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();
		   
		  }
		  
		  return hashedPassword;
		 }

}
