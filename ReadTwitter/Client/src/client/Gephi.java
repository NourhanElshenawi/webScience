package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gephi {
    public static void main(String[] args) { 
        String S="";
        HashMap hm = new HashMap();
         HashMap csv = new HashMap();
JSONParser parser = new JSONParser();
//	try {
//
//	    FileReader fr = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
//            BufferedReader br = new BufferedReader(fr);
//	    while (br.readLine() != null){
//                    Object obj = parser.parse(br.readLine());
//                    JSONObject jsonObject = (JSONObject) obj;
//                    JSONObject user = (JSONObject) jsonObject.get("user");
//                hm.put(user.get("id"), user.get("name"));
//                
//                }
//	    BufferedReader br2 = new BufferedReader(fr);
//            
//	   
//            
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//        
    try{
        
    	    FileReader fr2 = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
    	    BufferedReader br2 = new BufferedReader(fr2);
    	    BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\nourhan\\Desktop\\tesing123.csv"));
    	    while (br2.readLine() != null){
    	            Object obj = parser.parse(br2.readLine());
    	            JSONObject jsonObject = (JSONObject) obj;
    	            JSONObject user = (JSONObject) jsonObject.get("user");
    	    if(jsonObject.get("in_reply_to_user_id") != null){
                
                out.write(addConnection((String) user.get("screen_name"), (String) jsonObject.get("in_reply_to_screen_name")));
                out.newLine();
//                S = S + addConnection((String) user.get("name"), (String) jsonObject.get("in_reply_to_screen_name"));    
//                System.out.println("String: " + S);
    	    }
    	        
    	        }
        out.close();
        
    	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
    
    //////////////////
    
         
    
      
     }
    
    
    public static String addConnection(String name, String reply){
        
        String temp = name + "," + reply; 
        System.out.println(temp);
        return temp;
    }

}
    
