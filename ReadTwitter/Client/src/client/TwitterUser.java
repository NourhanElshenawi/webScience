package client;

public class TwitterUser {
    public long id=0;
    public String tweets="";
    public String name="";


    public TwitterUser(long id, String name, String tweets) {
        super();
        this.id = id;
        this.tweets = tweets;
        this.name = name;
    }
    
    public TwitterUser(long id) {
        super();
        this.id = id;
    }
    
    public void addTweet (String tweet){
        String temp = tweets;
        System.out.println("Temp"+temp);
        this.tweets = temp + "\n" + tweet;
        
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

    public String getTweets() {
        return tweets;
    }

    @Override
    public String toString() {
        // TODO Implement this method
        return "ID: " + id + "\n Name: " + name + "\n Tweets: \n" + tweets;
    }

}