package utils;

import java.util.Random;

public class PayLoadUtils {

    public static String getPetPayload(String categoryName,String petName,String status){
        Random random=new Random();
        int number=random.nextInt(500);
       String petPayload="{\n" +
               "  \"id\": "+number+",\n" +
               "  \"category\": {\n" +
               "    \"id\": 123,\n" +
               "    \"name\": \""+categoryName+"\"\n" +
               "  },\n" +
               "  \"name\": \""+petName+"\",\n" +
               "  \"photoUrls\": [\n" +
               "    \"www.amazon.com/bird.png\"\n" +
               "  ],\n" +
               "  \"tags\": [\n" +
               "    {\n" +
               "      \"id\": 3,\n" +
               "      \"name\": \"unknown\"\n" +
               "    }\n" +
               "  ],\n" +
               "  \"status\": \""+status+"\"\n" +
               "}";
       return petPayload;
    }
}
