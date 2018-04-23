package com.beyondbell.bugisoft.Utilities.Converters;

import java.util.ArrayList;

public class ObjectConverter {

	public static ArrayList<String> objectArrayListToStringArrayList(ArrayList<Object> objectArrayList) {
		// TODO Add Safety
		ArrayList<String> stringArrayList = new ArrayList<>();
		for (Object object : objectArrayList) {
			stringArrayList.add((String) object);
		}

		return stringArrayList;
	}
}
