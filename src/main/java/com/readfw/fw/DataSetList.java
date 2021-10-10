package com.readfw.fw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DataSetList extends ArrayList{
	
	public DataSetList() {
		
	}
	
	public DataSetList(ArrayList map) {
		super(map);
	}
	
	public DataSetList(Object obj) {
		super((ArrayList)obj);
	}
	
	public DataSet getDataSet(int index) {
		DataSet singleData = new DataSet(super.get(index));
				
		return singleData;
		
	}
	
}
