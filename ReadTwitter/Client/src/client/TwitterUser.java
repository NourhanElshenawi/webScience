package client;

import java.util.ArrayList;

public class TwitterUser {
    public long id=0;
    public String tweets="";
    public String name="";
    public ArrayList<String> hashtags = new ArrayList<String>();


    public TwitterUser(long id, String name, String tweets) {
        super();
        this.id = id;
        this.tweets = tweets;
        this.name = name;
    }
    
    public TwitterUser(long id, String name, String tweets, ArrayList<String> hashtags) {
        super();
        this.id = id;
        this.tweets = tweets;
        this.name = name;
        this.hashtags = hashtags;
    }
    
    public TwitterUser(String name, long id, ArrayList<String> hashtags) {
        super();
        this.id = id;
        this.hashtags = hashtags;
        this.name = name;
    }
    
    public TwitterUser(long id) {
        super();
        this.id = id;
    }
    
    public void addTweet (String tweet){
        String temp = tweets;
//        System.out.println("Temp"+temp);
        this.tweets = temp + "\n" + tweet;
        
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

    public String getTweets() {
        return tweets;
    }
    
    public ArrayList<String> getHashtags() {
        
        return hashtags;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        // TODO Implement this method
        String temp = "ID: " + id + "\n Name: " + name + "\n Tweets: \n" + tweets + "\n HASHTAGS: \n";
        for(int i=0; i<hashtags.size(); i++){
            temp = temp + hashtags.get(i) + "\n";
        }
        return temp;
    }

}
