package client;

import java.io.BufferedReader;
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
        
        
        
        
JSONParser parser = new JSONParser();

	try {

	    FileReader fr = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
            BufferedReader br = new BufferedReader(fr);

        while (br.readLine() != null){
            Object obj = parser.parse(br.readLine());
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject user = (JSONObject) jsonObject.get("user");
            long userID = (Long) user.get("id");
            System.out.println(user);
            System.out.println(userID);

        }
		
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
    

