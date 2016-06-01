package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
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
//         ArrayList<String> tags = new ArrayList<String>();
	try {

	    FileReader fr = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
            BufferedReader br = new BufferedReader(fr);

        while (br.readLine() != null){
            Object obj = parser.parse(br.readLine());
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject user = (JSONObject) jsonObject.get("user");
            JSONObject entities = (JSONObject) jsonObject.get("entities");
            long userID = (Long) user.get("id");
            
            JSONArray hashtags = (JSONArray) entities.get("hashtags");
                    Iterator i = hashtags.iterator();
            ArrayList<String> tags = new ArrayList<String>();
            if(userID == 1538983531){
                System.out.println("ID: " +userID);
            }
                    while (i.hasNext()) {
//                System.out.println("SIZE: " + hashtags.size());
//                for(int i=0; i<hashtags.size(); i++){
                        JSONObject test = (JSONObject) i.next();
//                        System.out.println(test);
                        tags.add((String) test.get("text"));
//                        System.out.println("HASHTAGS: \n" + test.get("text"));
                    }
            
            
//            if(userID == 2193594854L){
//                System.out.println(user);
//                System.out.println(jsonObject.get("text"));
//            }
            
            if(hm.containsKey(userID) == true){
                TwitterUser temp = (TwitterUser) hm.get(userID);
                temp.addTweet((String) jsonObject.get("text"));
            } 
            else
                hm.put(userID, new TwitterUser(userID, (String) user.get("name"), (String) jsonObject.get("text"), tags));
            

        }
            
            System.out.println("HashMap:\n"+ hm.get(1538983531L).toString());
		

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}

     /////////////////
//     for (Map.Entry<Long, TwitterUser> entry : hm.entrySet()) {
//         Long key = entry.getKey();
//         TwitterUser value = entry.getValue();
//         String textName = value.getName();
//         String textTweets = value.getTweets();
//         
//         String location = "C:\\Users\\nourhan\\Desktop\\TestingTweets\\" + key + "Tweets.txt";         
//
//            try {
//                BufferedWriter out = new BufferedWriter(new FileWriter(location));
//                out.write(textTweets);
//                out.close();
//            } catch (IOException e) {
//            }
//     }
        
        for (Map.Entry<Long, TwitterUser> entry : hm.entrySet()) {
         Long key = entry.getKey();
         TwitterUser value = entry.getValue();
         String textName = value.getName();
         ArrayList<String> textTags = value.getHashtags();
         
         String location = "C:\\Users\\nourhan\\Desktop\\TestingTags\\" + key + "Tags.txt";         

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(location));
                
                for(int i=0; i<textTags.size(); i++){
                    out.write(textTags.get(i));
                    out.newLine();
                }
                System.out.println(textTags);
                out.close();
            } catch (IOException e) {
            }
     }
     
     
     
     
     }

}
    

