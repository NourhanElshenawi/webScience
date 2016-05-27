package client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
    public static void main(String[] args) {
        
        HashMap hm = new HashMap();
        
        
JSONParser parser = new JSONParser();
         String S="";
         String name="";
         long ID=0;
         int counter=0;
//         TwitterUser temp = new TwitterUser(2193594854L);
	try {

	    FileReader fr = new FileReader("C:\\Users\\nourhan\\Desktop\\tweets.json.7");
            BufferedReader br = new BufferedReader(fr);

        while (br.readLine() != null){
            Object obj = parser.parse(br.readLine());
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject user = (JSONObject) jsonObject.get("user");
            long userID = (Long) user.get("id");
//            System.out.println("\n"+ user + "\n");
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
            /////////////////////////////////////////////
//            System.out.println(user);
//            System.out.println(userID);
            
//            if( userID == 2193594854L ){
//                name = (String) user.get("name");
//                ID = userID;
//                String f = (String) jsonObject.get("text");
//                 concat method did not work
//                S = S + "\n" + f;
                hm.put(userID, new TwitterUser(userID, (String) user.get("name"), (String) jsonObject.get("text")));
//                hm.put(userID, temp);
//                temp.addTweet(S);
//                System.out.println("HELLOOO" + jsonObject.get("text"));
//                System.out.println(jsonObject);
                
//                System.out.println("FINAL!!!!" + temp.getTweets());
//            }
            
            if( userID == 2193594854L){
                counter++;
            }

        }
            
            System.out.println("COUNTER: " +counter + "\n" );
            
	    System.out.println("All Tweets belonging to " + name+ " with user id: "+ ID +": \n" + S);
            System.out.println("HashMap:\n"+ hm.get(2193594854L).toString());
		
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
    

