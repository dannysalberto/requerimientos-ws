package co.com.interkont.avanzame.utils;

public class RandomString {
	  
    public static String getAlphaNumericString(int n)
    {
  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        StringBuilder strreturn = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  

            int index = (int)(AlphaNumericString.length() * Math.random());
            strreturn.append(AlphaNumericString.charAt(index));
        }
  
        return strreturn.toString();
    }
  

}