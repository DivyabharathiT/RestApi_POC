package com.rest.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.rest.steps.Traveltaxsteps;

public class Baseclass {
      public static String crudloc = System.getProperty("user.dir")+ "\\CRUD.properties";
      
	public static String getProperty(String key, String propertylocation) throws IOException {
		String Value;
		Properties Prop = new Properties();
		File Location = new File(propertylocation);
		FileReader File = new FileReader(Location);
		Prop.load(File);
		return Value = Prop.getProperty(key).split(" ")[0];
		
	}
	public static String readJson() throws Throwable {
        byte[] input =Files.readAllBytes(Paths.get("createtravel.json"));
        String inputJson = new String(input);
        return inputJson;
    }
	
}