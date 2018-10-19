package midterm2018;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String question1and2 = "GET /add?a=3&b=4 HTTP/1.1\n"
            + "Host: localhost:1298\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\n"
            + "Accept: image/webp,image/apng,image/*,*/*;q=0.8\n"
            + "Referer: http://localhost:1298/\n"
            + "Accept-Encoding: gzip, deflate, br\n"
            + "Accept-Language: en-US,en;q=0.9,es;q=0.8\n";

        String question3 = "{\n"
                + "    “task” : “inc”,\n"
                + "    “num” : 3\n"
                + "}\n";

        String question4and5 = "To opt out, you’ll need to enter the Settings menu by clicking the three vertical dots, all the way in the upper right corner of the browser. From there, you’ll need to enter the Advanced settings at the very bottom and find the “Allow Chrome sign in” toggle, then turn it to off. Doing so lets you sign into Google services like Gmail and Maps without signing into the Chrome browser itself.";


        System.out.println(question1and2);
        System.out.println(question3);
        System.out.println(question4and5);

        // print each answer at the end

        // Question 1
        // Return the Host
        //extract what the host is, no hard coding
        System.out.println("\n"+question1and2.substring(question1and2.indexOf("Host: ")+6,
                question1and2.indexOf("\n",question1and2.indexOf("Host:"))));

        // Question 2
        // return sum of a and b
        //extract a&b from the Get request in the question1and2, just parse the string
        int a = Integer.parseInt(question1and2.substring(question1and2.indexOf("a=")+2,question1and2.indexOf("a=")+3));
        int b = Integer.parseInt(question1and2.substring(question1and2.indexOf("b=")+2,question1and2.indexOf("b=")+3));
        System.out.println("\nSum of a + b = " + (a+b));

        // Question 3
        // convert to java object, increment num, convert back to json and return
        // will need to convert this to JSON, increment the num value and then convert it back to a string

        class ClassForJsonImport{
            String task;
            int num;
            ClassForJsonImport(){}
        }

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject Json = parser.parse(question3).getAsJsonObject();
        ClassForJsonImport importedTask = gson.fromJson(Json, ClassForJsonImport.class);
        int number = Integer.parseInt(importedTask.num)+1;
        importedTask.num = String.valueOf(number);
        String result = gson.toJson(importedTask);
        System.out.println(result);


        // Question 4
        // return unique words
        //create a set in question4and5 string, to count the unique words

        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(question4and5.toLowerCase());
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        while (m.find())
        {
            String word = m.group();

            // If this is first occurrence of word
            if(!hm.containsKey(word))
                hm.put(word, 1);
            else
                // increment counter of word
                hm.put(word, hm.get(word) + 1);
        }

        System.out.println("The unique words are:\n" +hm.keySet());


        // Question 5
        // return 2nd most common word
        // create a map with a key and integer for a count

            Object highestValueKey = null;
            Object secondHighestKey = null;
            int temp;
            int highestValue = 0;
            int secondHighest = 0;
            Iterator it = hm.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                temp = (Integer) pair.getValue();
                if(temp > highestValue){
                       highestValue = temp;
                       secondHighestKey = highestValueKey;
                       highestValueKey = pair.getKey();
                }
                else if(temp > secondHighest){
                    secondHighest = temp;
                    secondHighestKey = pair.getKey();
                }
            }
        System.out.println("The second most common word is: " + secondHighestKey);

    }
}
