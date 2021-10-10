package com.readfw.fw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DataSet extends LinkedHashMap{
	
	public DataSet() {
		
	}
	public DataSet(LinkedHashMap map) {
		super(map);
	}
	
	public DataSet(Object obj) {
		super((LinkedHashMap)obj);
	}
	
	public void setString(Object key, String value) {
		super.put(key, value);
	}
	
	public String getString(String key) {
		Object o = super.get(key);
		
		if(o == null) {
			return "";
		}
		return o.toString();
	}
	
	public Object getList(Object key) {
		ArrayList list = (ArrayList)super.get(key);
		
		LinkedHashMap<Integer, Object> o = new LinkedHashMap<>();
		for (int i = 0; i < list.size(); i++) {
			o.put(i, list.get(i));
		}
		
		if(o == null) {
			return "";
		}
		return o;
	}
	
	public Object get(Object key) {
		Object o = super.get(key);
		
		if(o == null) {
			return "";
		}
		return o;
	}

}