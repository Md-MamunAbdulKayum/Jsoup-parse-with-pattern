/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jsoupparseallemailadr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Mamun
 */
public class JsoupParseAllStringsWithPattern {

    public static void main(String[] args) {
        Document doc;
        try {
            doc = Jsoup.connect("https://cits.uwex.uwc.edu/email-address-examples").get(); // change with your desire web page from where you want to get email address
            String str = doc.body().text();
            int numOfWord = countWords(str);
            System.out.println("Words: " + numOfWord);
            List<String> getEmail = extractEmail(str);
            System.out.println("Email: " + getEmail.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> extractEmail(String str) {
        
        String [] allWords = findAllWords(str);
        //System.out.println("Found words: "+Arrays.toString(allWords));
        List <String> emailAdrs = new ArrayList<String>();
        for(String word : allWords){
//            System.out.println("Words: " + word);
         Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(word);
         if(m.matches()){
         emailAdrs.add(word);
         }
        }
        return emailAdrs;
    }

    public static int countWords(String str) {
        int count = 1;
        for (int i = 0; i <= str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                count++;
            }
        }
        return count;
    }

    public static String[] findAllWords(String str) {
        String[] words = str.split("\\s+");
        return words;
    }

}
