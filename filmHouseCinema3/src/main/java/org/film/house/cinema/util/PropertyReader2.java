package org.film.house.cinema.util;

import java.util.ResourceBundle;

import org.film.house.cinema.util.PropertyReader2;

public class PropertyReader2 {
	
	  private static ResourceBundle rb = ResourceBundle.getBundle("org.film.house.cinema.bundle.system");
	

	  public static String getValue(String key) {

	        String val = null;

	        try {
	            val = rb.getString(key);
	        } catch (Exception e) {
	            val = key;
	        }

	        return val;

	    }
	  
	  
	  public static String getValue(String key, String param) {
	        String msg = getValue(key);
	        msg = msg.replace("{0}", param);
	        return msg;
	    }
	  
	  
	  public static String getValue(String key, String[] params) {
	        String msg = getValue(key);
	        for (int i = 0; i < params.length; i++) {
	            msg = msg.replace("{" + i + "}", params[i]);
	        }
	        return msg;
	    }
	
	
	 public static void main(String[] args) {
	        String params =  "email" ;
	        System.out.println(PropertyReader2.getValue("requires", params));
	    }

}
