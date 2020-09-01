package org.film.house.cinema.util;

public class DataValidator2 {

	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isPassword(String val) {
		String passregex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$";

		if (val.matches(passregex)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isPhoneNo(String val) {
		String regex = "^[0-9][0-9]{10}$"; 
		if (val.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	
	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
