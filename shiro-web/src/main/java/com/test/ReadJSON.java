package com.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class ReadJSON {

	public static void main(String[] args) throws IOException {
		File file = new File(ReadJSON.class.getResource("/data.json").getFile());
		String data = FileUtils.readFileToString(file);
		JSONObject jsonObject = new JSONObject(data);
		System.out.println(jsonObject.getString("name"));
	}

}
