package org.film.house.cinema.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.film.house.cinema.bean.DropdownListBean2;

public class HTMLUtility2 {
	
	
	
	

	public static String getList(String name, String selectedVal,HashMap<String, String> map) {
		
		/*Map<String, String> treeMap = new LinkedHashMap<String, String>(map);*/
	
		StringBuffer sb = new StringBuffer(	"<select class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;
		String select = "-----Select-----";
		sb.append("<option selected value='" + select + "'>" + select+ "</option>");
		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {

				sb.append("<option selected value='" + key + "'>" + val	+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
	
public static String getList(String name, String selectedVal, List list) {
		
		Collections.sort(list);

		List<DropdownListBean2> dd = (List<DropdownListBean2>) list;

		StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "' id='" + name+ "'>");
		
		String key = null;
		
		String val = null;
		
		String select = "-----Select-----";
		
		sb.append("<option selected value='" + select + "'>" + select+ "</option>");
		
		for (DropdownListBean2 obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}

		sb.append("</select>");
		return sb.toString();
	}

}
