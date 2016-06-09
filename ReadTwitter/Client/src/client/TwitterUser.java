package client;

import java.util.ArrayList;

public class TwitterUser {
    public long id=0;
    public String tweets="";
    public String name="";
    public String screenName="";
    public ArrayList<String> hashtags = new ArrayList<String>();
    public String inReplyTo="";
    public String inReplyToID = "";


    public TwitterUser(long id, String name,String screenName, String tweets) {
        super();
        this.id = id;
        this.tweets = tweets;
        this.name = name;
        this.screenName = screenName;
//        this.inReplyTo = inReplyTo;
//        this.inReplyToID = inReplyToID;
    }
    
    public TwitterUser(long id, String name,String screenName, String tweets, ArrayList<String> hashtags, String inReplyTo, String inReplyToID) {
        super();
        this.id = id;
        this.tweets = tweets;
        this.name = name;
        this.hashtags = hashtags;
        this.screenName = screenName;
        this.inReplyTo=inReplyTo;
        this.inReplyToID = inReplyToID;
    }
    
    public TwitterUser(String name,String screenName, long id, ArrayList<String> hashtags) {
        super();
        this.id = id;
        this.hashtags = hashtags;
        this.name = name;
        this.screenName = screenName;
//        this.inReplyTo=inReplyTo;
//        this.inReplyToID = inReplyToID;
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
    
    public void addHT(String ht){
        hashtags.add(ht);
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
    
    public String getScreenName() {
        return screenName;
    }
    
    public String getInReplyTo() {
        return inReplyTo;
    }
    
    public String getInReplyToID() {
        return inReplyToID;
        
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
