package client;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
    public static void main(String[] args) {
        
//        JSONParser parser = new JSONParser();
//        
//        try {
//            Object obj = parser.parse(new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7"));
//            
//            JSONObject jsonObject= (JSONObject) obj;
//            
//            String user = (String) jsonObject.get("user");
//            System.out.println("!!!!\n" + user);
//    
//        } catch(FileNotFoundException e){
//            e.printStackTrace();
//            
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
JSONParser parser = new JSONParser();

	try {

		Object obj = parser.parse(new FileReader("C:\\Users\\nourhan\\Desktop\\testingtwitter.json.7"));

		JSONObject jsonObject = (JSONObject) obj;

		JSONObject user = (JSONObject) jsonObject.get("user");
            long userID = (Long) user.get("id");
		System.out.println(user);
            System.out.println(userID);

//		long age = (Long) jsonObject.get("age");
//		System.out.println(age);

		// loop array
//		JSONArray user = (JSONArray) jsonObject.get("user");
//		Iterator<String> iterator = user.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}

     }

}
    

