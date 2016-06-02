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

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
    
    private int[] array;
        private int[] tempMergArr;
        private int length;
        
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
//            if(userID == 1538983531){
//                System.out.println("ID: " +userID);
//            }
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
            
//            System.out.println("HashMap:\n"+ hm.get(1538983531L).toString());
		

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
            
            if(textTags.size()>5){
         int[] sorting = new int[textTags.size()] ;
            ArrayList<String> temp = new ArrayList<String>();
         
         for(int i=0; i<textTags.size(); i++){
             
         sorting[i] = tagFrequency(textTags.get(i),textTags);
         }
            
         ReadJson mms = new ReadJson();
         mms.sort(sorting);
            
         for(int i =0; i<5; i++){
             for(int j=0; j<textTags.size(); j++){
                 if(sorting[i] == tagFrequency(textTags.get(j),textTags)){
                     temp.add((String)textTags.get(j));
                 }
             }
         }
            
         String location2 = "C:\\Users\\nourhan\\Desktop\\TestingTopTags\\" + key + "Top5Tags.txt";         

            try {
                BufferedWriter out2 = new BufferedWriter(new FileWriter(location2));
                
                
                
                for(int h=0; h<5; h++){
                    String y = temp.get(h);
                    out2.write(y + "," + sorting[h]);
                    out2.newLine();
                }
                out2.close();
            } catch (IOException e) {
            }
            
         }
         
//         String location = "C:\\Users\\nourhan\\Desktop\\TestingTags\\" + key + "Tags.txt";         
//
//            try {
//                BufferedWriter out = new BufferedWriter(new FileWriter(location));
//                if(textTags.size()>5){
//                }
//                for(int i=0; i<textTags.size(); i++){
//                    out.write(textTags.get(i));
//                    out.newLine();
//                }
//                System.out.println(textTags);
//                out.close();
//            } catch (IOException e) {
//            }
     }
     
     
     
     
     }
    
    public static int tagFrequency(String word, ArrayList<String> t){
        
        int k=0;
        
        for(String c: t){
                if(c.compareToIgnoreCase(word) == 0) {
                    k= k+1;
                }
        }
        System.out.println(k + "KKKKKKKKKK");
        return k;
        
        
    }
    
    
    
    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }
    
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
    
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
    
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    
    }
  

}
    

