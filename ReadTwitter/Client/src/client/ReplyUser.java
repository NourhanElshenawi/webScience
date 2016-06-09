package client;

import java.util.ArrayList;

public class ReplyUser {
    
    public String id="";
    public String screenName="";
    public ArrayList<String> ht= new ArrayList<String>();


    public ReplyUser(String id, String screenName, String ht) {
        super();
        this.id = id;
        this.ht.add(ht);
        this.screenName = screenName;
//        this.inReplyTo = inReplyTo;
//        this.inReplyToID = inReplyToID;
    }
    
    public ReplyUser(String id, String screenName) {
        super();
        this.id = id;
        this.screenName = screenName;
    //        this.inReplyTo = inReplyTo;
    //        this.inReplyToID = inReplyToID;
    }
    
    
    public void addHT (String ht){
//        String temp = ht;
//        System.out.println("Temp"+temp);
        this.ht.add(ht);
        
    }

    public ArrayList<String> getHT() {
        return ht;
        
    }
    
    
    public String getScreenName() {
        return screenName;
    }
    
}
