package com.example.livecode;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.json.Json;
import java.math.BigDecimal;
import java.util.*;
import java.io.StringReader;

import java.io.BufferedReader;
import java.io.FileReader;

public class MyConfigSource implements ConfigSource{
   String fileLocation ="/Users/emilyjiang/demo/CustomConfigSource.json";
    Map<String, String> m = new HashMap<String, String>();

    @Override
    public Set<String> getPropertyNames() {
        System.out.println("Read this: " + m.keySet());
        if (m.isEmpty()){
            try {
                readFile(fileLocation);
            } catch (Exception e) {
                
                e.printStackTrace();
            } 
        }
        return m.keySet();
    
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "My customer config source";
    }

    public void readFile(String fileName) throws Exception {
        String jsonData = "";
        try {
          BufferedReader br = new BufferedReader(new FileReader(fileName));
          StringBuilder sb = new StringBuilder();
          String line = br.readLine();
          while (line != null) {
            sb.append(line);
            line = br.readLine();
          }
          jsonData = sb.toString();
          br.close();
        } catch (Exception e) {
          e.printStackTrace();
          throw e;
        }
        

        System.out.println("file: " + jsonData);
      
        JsonParser parser = Json.createParser(new StringReader(jsonData));
        String key = null;
        while (parser.hasNext()) {
        final Event event = parser.next();
        switch (event) {
        case KEY_NAME:
            key = parser.getString();
            break;
        case VALUE_STRING:
            String string = parser.getString();
            m.put(key, string);
            break;
        case VALUE_NUMBER:
            BigDecimal number = parser.getBigDecimal();
            m.put(key, number.toString());
            break;
        
        default:
            break;
        }
    }
    parser.close();

    }

    @Override
    public String getValue(String propertyName) {
        return m.get(propertyName);
    } 
}


