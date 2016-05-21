package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJson {
    public static void main(String[] args) {
        
        JSONObject obj = new JSONObject();
                obj.put("name", "mkyong.com");
                obj.put("age", new Integer(100));

                JSONArray list = new JSONArray();
                list.add("msg 1");
                list.add("msg 2");
                list.add("msg 3");

                obj.put("messages", list);

                try {

                        FileWriter file = new FileWriter("C:\\Users\\nourhan\\Desktop\\test.json");
                        file.write(obj.toJSONString());
                        file.flush();
                        file.close();

                } catch (IOException e) {
                        e.printStackTrace();
                }

                System.out.print(obj);
                
        try {

                FileReader testingJason = new FileReader("C:\\Users\\nourhan\\Desktop\\Twitter\\tweets.json.7");

            BufferedReader reading = new BufferedReader(testingJason);
            
            String aLine;
            int lines=0;
            
            while((aLine = reading.readLine()) !=null){
                lines++;
                System.out.println(aLine);
            }
            
            
            String[] jsonData = new String[lines];
            
            for(int i=0; i<lines; i++){
                jsonData[i] = reading.readLine();
            }
            
            reading.close();
            
            testingJason.close();

        } catch (IOException e) {
                e.printStackTrace();
        }
        

        
//                System.out.println(jsonData);


        
        
    }
}
