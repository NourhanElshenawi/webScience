package client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class test {
    public static void main(String[] args) {

        


        String loc = "C:\\Users\\nourhan\\Desktop\\Final WebScience\\pairs\\k.csv";
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(loc));

            
            for (int i = 0; i < 10; i++) {
               String k = "bbb \n";
                write.write(k);

            }
            
            
            write.close();

        } catch (IOException e) {
        }

    }
}
