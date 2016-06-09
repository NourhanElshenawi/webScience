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

        //         1567
        // extractInfo();


        HashMap<Long, TwitterUser> hm = new HashMap<Long, TwitterUser>();
        HashMap<String, ReplyUser> rp = new HashMap<String, ReplyUser>();


        JSONParser parser = new JSONParser();
        String S = "";
        String name = "";
        long ID = 0;
        int[] ss = { 1, 5, 7 };
        // number of json file
        int number = 5;
        for (int q = 0; q < ss.length; q++) {
            number = ss[q];

            try {
                FileReader fr =
                    new FileReader("C:\\Users\\nourhan\\Desktop\\Final WebScience\\Json\\tweets.json." + number + "");
                BufferedReader br = new BufferedReader(fr);
                BufferedWriter out3 =
                    new BufferedWriter(new FileWriter("C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\u2u\\user-to-userRelationship%" +
                                                      number + "%.csv"));

                while (br.readLine() != null) {
                    //read json/parse
                    Object obj = parser.parse(br.readLine());
                    JSONObject jsonObject = (JSONObject) obj;
                    //get user object
                    JSONObject user = (JSONObject) jsonObject.get("user");
                    //get entities object that contain hashtags
                    JSONObject entities = (JSONObject) jsonObject.get("entities");
                    //get user ID
                    long userID = (Long) user.get("id");
                    //get hashtags array
                    JSONArray hashtags = (JSONArray) entities.get("hashtags");
                    //collect hashtags by iteration
                    Iterator i = hashtags.iterator();
                    ArrayList<String> tags = new ArrayList<String>();
                    //getting network information based on "in_reply_to_user_id" variable which is unique
                    if (jsonObject.get("in_reply_to_user_id") !=
                        null) {
                        //if it is a reply write the relationship into the csv file
                        //screen name is alsi unique
                        out3.write(addConnection((String) user.get("screen_name"),
                                                 (String) jsonObject.get("in_reply_to_screen_name")));
                        out3.newLine();
                        //if this user already exists in the set of users that were replied to, add hashtags used to characterize him in the rp hashmap
                        if (rp.containsKey((String) jsonObject.get("in_reply_to_user_id_str"))) {
                            while (i.hasNext()) {
                                JSONObject test = (JSONObject) i.next();
                                rp.get((String) jsonObject.get("in_reply_to_user_id_str")).addHT((String) test.get("text"));
                            }
                        } else {
                            //if he is a new user to be replied to, add him to the hashmap
                            rp.put((String) jsonObject.get("in_reply_to_user_id_str"),
                                   new ReplyUser((String) jsonObject.get("in_reply_to_user_id_str"),
                                                 (String) jsonObject.get("in_reply_to_screen_name")));
                        }
                    }
                    //collect all users
                    if (hm.containsKey(userID) == true) {
                        TwitterUser temp = (TwitterUser) hm.get(userID);
                        temp.addTweet((String) jsonObject.get("text"));
                        while (i.hasNext()) {
                            JSONObject test = (JSONObject) i.next();
                            temp.addHT((String) test.get("text"));
                        }
                    } else {
                        while (i.hasNext()) {
                            JSONObject test = (JSONObject) i.next();
                            tags.add((String) test.get("text"));
                        }
                        hm.put(userID,
                               new TwitterUser(userID, (String) user.get("name"), (String) user.get("screen_name"),
                                               (String) jsonObject.get("text"), tags,
                                               (String) jsonObject.get("in_reply_to_screen_name"),
                                               (String) jsonObject.get("in_reply_to_user_id_str")));
                    }

                }
                out3.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            /////////////////

            //collect data for pair similarity network

            String loc = "C:\\Users\\nourhan\\Desktop\\Final WebScience\\pairs\\new\\pairRelationshipFinal.csv";
            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(loc));
                for (Map.Entry<Long, TwitterUser> entry : hm.entrySet()) {
                    Long key = entry.getKey();
                    TwitterUser value = entry.getValue();
                    for (Map.Entry<Long, TwitterUser> entry2 : hm.entrySet()) {

                        TwitterUser value2 = entry2.getValue();
                        //calculate similarity
                        double sim = (double) getUserSimilarity(value, value2);
                        if (sim >
                            0.5) {
                            //threshold
                            String similarityReport =
                          value.getScreenName() + "," + value2.getScreenName() + "," + (double) sim + "\n";
                            write.write(similarityReport);
                        }

                    }

                    String textName = value.getName();
                    String screenName = value.getScreenName();

                    String replyName = value.getInReplyTo();
                    String replyID = value.getInReplyToID();
                    String textTweets = value.getTweets();
                    //user-to-user inreply to relationship per json file
                    String location =
                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tweets\\" + replyID +
                        " - in reply to " + replyName + " - From " + textName + "%" + number + "%Tweets.txt";

                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(location));
                        out.write(textTweets);
                        out.close();
                    } catch (IOException e) {
                    }


                }
                write.close();
            } catch (IOException e) {
            }

            //            for (Map.Entry<Long, TwitterUser> entry : hm.entrySet()) {
            //                Long key = entry.getKey();
            //                TwitterUser value = entry.getValue();
            //                String textName = value.getName();
            //                String screenName = value.getScreenName();
            //                String replyName = value.getInReplyTo();
            //                String replyID = value.getInReplyToID();
            //                ArrayList<String> textTags = value.getHashtags();
            //
            //                if (textTags.size() > 5) {
            //                    int[] sorting = new int[textTags.size()];
            //                    ArrayList<String> temp = new ArrayList<String>();
            //
            //                    for (int i = 0; i < textTags.size(); i++) {
            //
            //                        sorting[i] = tagFrequency(textTags.get(i), textTags);
            //                    }
            //
            //                    ReadJson mms = new ReadJson();
            //                    mms.sort(sorting);
            //
            //                    for (int i = 0; i < 5; i++) {
            //                        for (int j = 0; j < textTags.size(); j++) {
            //                            if (sorting[i] == tagFrequency(textTags.get(j), textTags)) {
            //                                temp.add((String) textTags.get(j));
            //                            }
            //                        }
            //                    }
            //
            //                    String location2 =
            //                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tags\\" + replyID + " - in reply to " +
            //                        replyName + " - From " + textName + " %" + number + "%Top5Tags.txt";
            //
            //                    try {
            //                        BufferedWriter out2 = new BufferedWriter(new FileWriter(location2));
            //
            //
            //                        for (int h = 0; h < 5; h++) {
            //                            String y = temp.get(h);
            //                            out2.write(y + "," + sorting[h]);
            //                            out2.newLine();
            //                        }
            //                        out2.close();
            //                    } catch (IOException e) {
            //                    }
            //
            //                } else if (!textTags.isEmpty()) {
            //
            //                    String location =
            //                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tags\\" + replyID + " - in reply to " +
            //                        replyName + " - From " + textName + "%" + number + "%Tags.txt";
            //
            //                    try {
            //                        BufferedWriter out = new BufferedWriter(new FileWriter(location));
            //                        for (int i = 0; i < textTags.size(); i++) {
            //                            out.write(textTags.get(i));
            //                            out.newLine();
            //                        }
            //                        System.out.println(textTags);
            //                        out.close();
            //                    } catch (IOException e) {
            //                    }
            //                } else if (textTags.isEmpty()) {
            //                    String location =
            //                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tags\\" + replyID + " - in reply to " +
            //                        replyName + " - From " + textName + "%" + number + "%Tags.txt";
            //
            //                    try {
            //                        BufferedWriter out = new BufferedWriter(new FileWriter(location));
            //                        if (textTags.size() > 5) {
            //                        }
            //                        out.write("Empty");
            //                        out.newLine();
            //
            //                        System.out.println(textTags);
            //                        out.close();
            //                    } catch (IOException e) {
            //                    }
            //
            //                }
            //            }


            //collect hashtags per user to be used in characterizing the user they replied to
            for (Map.Entry<String, ReplyUser> entry : rp.entrySet()) {
                String key = entry.getKey();
                ReplyUser value = entry.getValue();
                String screenName = value.getScreenName();
                ArrayList<String> textTags = value.getHT();
                // if user uses more than 5 hashtags we get the top 5 with regard to their frequecies
                if (textTags.size() > 5) {
                    int[] sorting = new int[textTags.size()];
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int i = 0; i < textTags.size(); i++) {
                        sorting[i] = tagFrequency(textTags.get(i), textTags);
                    }
                    ReadJson mms = new ReadJson();
                    mms.sort(sorting); //sort frequencies
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < textTags.size(); j++) {
                            if (sorting[i] == tagFrequency(textTags.get(j), textTags)) {
                                temp.add((String) textTags.get(j));
                            }
                        }
                    }
                    String location2 =
                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tags\\" + screenName + " %" + number +
                        "%Top5Tags.txt";
                    try {
                        BufferedWriter out2 = new BufferedWriter(new FileWriter(location2));
                        for (int h = 0; h < 5; h++) {
                            String y = temp.get(h);
                            out2.write(y + "," + sorting[h]);
                            out2.newLine();
                        }
                        out2.close();
                    } catch (IOException e) {
                    }

                } else if (!textTags.isEmpty()) {

                    String location =
                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tags\\" + screenName + "%" + number +
                        "%Tags.txt";

                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(location));
                        if (textTags.size() > 5) {
                        }
                        for (int i = 0; i < textTags.size(); i++) {
                            out.write(textTags.get(i));
                            out.newLine();
                        }
                        //                System.out.println(textTags);
                        out.close();
                    } catch (IOException e) {
                    }
                } else if (textTags.isEmpty()) {
                    String location =
                        "C:\\Users\\nourhan\\Desktop\\Final WebScience\\results\\Tags\\" + screenName + "%" + number +
                        "%Tags.txt";

                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(location));
                        if (textTags.size() > 5) {
                        }
                        out.write("Empty");
                        out.newLine();

                        //                       System.out.println(textTags);
                        out.close();
                    } catch (IOException e) {
                    }

                }
            }
            System.out.println("BYEEEEE");
        }
        System.out.println("Num of Users: " + hm.size());


    }

    public static int tagFrequency(String word, ArrayList<String> t) {

        int k = 0;

        for (String c : t) {
            if (c.compareToIgnoreCase(word) == 0) {
                k = k + 1;
            }
        }
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

    public static String addConnection(String name, String reply) {

        String temp = name + "," + reply;
        return temp;
    }

    public static double getUserSimilarity(TwitterUser a, TwitterUser b) {
        ArrayList<String> atemp = a.getHashtags();
        ArrayList<String> btemp = b.getHashtags();
        int incommon = 0;
        double similarity = 0.0;
        if (atemp.size() > 0 && btemp.size() > 0) {
            for (int i = 0; i < atemp.size(); i++) {
                ArrayList<String> checked = new ArrayList<String>();
                for (int j = 0; j < btemp.size(); j++) {
                    if (atemp.get(i).equalsIgnoreCase(btemp.get(j)) && !checked.contains(atemp.get(i))) {
                        incommon = incommon + 1;
                        checked.add(atemp.get(i));
                    } else {
                    }
                }
            }
            int c = 0;
            if (!atemp.isEmpty() && !btemp.isEmpty()) {
                for (int i = 0; i < atemp.size(); i++) {
                    ArrayList<String> checked = new ArrayList<String>();
                    for (int j = 0; j < btemp.size(); j++) {
                        if (atemp.contains(btemp.get(j)) && !checked.contains(btemp.get(j))) {
                            c = c + 1;
                            checked.add(btemp.get(j));
                        }
                    }
                }
            }


            int total = atemp.size() + btemp.size();
            similarity = (double) incommon / total;
            System.out.println("SIM: " + similarity);
        }
        return similarity;
    }


}


