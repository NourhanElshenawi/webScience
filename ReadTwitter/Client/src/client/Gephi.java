package client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gephi {
    public static void main(String[] args) { 
        String S="";
        HashMap hm = new HashMap();
         HashMap csv = new HashMap();
JSONParser parser = new JSONParser();
	try {

	    FileReader fr = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
            BufferedReader br = new BufferedReader(fr);
	    while (br.readLine() != null){
                    Object obj = parser.parse(br.readLine());
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONObject user = (JSONObject) jsonObject.get("user");
                hm.put(user.get("id"), user.get("name"));
            if(jsonObject.get("in_reply_to_user_id") != null){
                
            }
                
                }
	    BufferedReader br2 = new BufferedReader(fr);
        while (br2.readLine() != null){
System.out.println("HIIIIII");                        
            Object obj2 = parser.parse(br2.readLine());
            JSONObject jsonObject2 = (JSONObject) obj2;
            JSONObject user2 = (JSONObject) jsonObject2.get("user");
            
            long userID = (Long) user2.get("id");
            long inReply = (Long) user2.get("in_reply_to_user_id");
            
            if(jsonObject2.get("in_reply_to_user_id") != null){
                csv.put(user2.get("name"), hm.get(inReply));
                System.out.println("USER: " + userID + " REPLIED TO: " + inReply);
                System.out.println(jsonObject2.get("in_reply_to_user_id"));
            }
            

        }
            
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}

     }

}
    
