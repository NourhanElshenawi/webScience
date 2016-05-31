package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
    public static void main(String[] args) {
        
        HashMap<Long, TwitterUser> hm = new HashMap<Long, TwitterUser>();
        
        
JSONParser parser = new JSONParser();
         String S="";
         String name="";
         long ID=0;
	try {

	    FileReader fr = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
            BufferedReader br = new BufferedReader(fr);

        while (br.readLine() != null){
            Object obj = parser.parse(br.readLine());
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject user = (JSONObject) jsonObject.get("user");
            long userID = (Long) user.get("id");
            if(userID == 2193594854L){
                System.out.println(user);
                System.out.println(jsonObject.get("text"));
            }
            
            if(hm.containsKey(userID) == true && userID == 2193594854L){
                TwitterUser temp = (TwitterUser) hm.get(userID);
                System.out.println("Adding a second tweet \n");
                System.out.println((String) jsonObject.get("text")+"\n");
                temp.addTweet((String) jsonObject.get("text"));
                temp.getTweets();
            } else
                hm.put(userID, new TwitterUser(userID, (String) user.get("name"), (String) jsonObject.get("text")));
            

        }
            
            System.out.println("HashMap:\n"+ hm.get(2193594854L).toString());
		

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}

     /////////////////
     for (Map.Entry<Long, TwitterUser> entry : hm.entrySet()) {
         Long key = entry.getKey();
         TwitterUser value = entry.getValue();
         String textName = value.getName();
         String textTweets = value.getTweets();
         
         String location = "C:\\Users\\nourhan\\Desktop\\TestingTweets" + textName + "Tweets.csv";         

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(location));
                out.write(textTweets);
            } catch (IOException e) {
            }
            // ...
     }
     
     }

}
    

