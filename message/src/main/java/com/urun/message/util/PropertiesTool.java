package com.urun.message.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesTool {

	private String path;
	private Properties properties=new Properties();
	public PropertiesTool(String fileName)
	{
	 path=fileName;
	 InputStream inStream=null;

     try {
       inStream=PropertiesTool.class.getResourceAsStream("/"+fileName);
       BufferedReader bf = new BufferedReader(new InputStreamReader(inStream)); 
       properties.load(bf);
       bf.close();
     } catch (Exception e) {
         throw new RuntimeException("找不到配置文件"+fileName);
     }
	}
	public String getValue(String key) throws IOException 
	{
		return properties.getProperty(key);
    }
}
