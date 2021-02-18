/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import interfaz.All;
import interfaz.Posts;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Miguel
 */
public class Jsonwork {
    
    
    public static ArrayList getUsersFromApi(String urlJson){
        ArrayList<String> usersData = new ArrayList<>();
        String urlApi=urlJson;
        String[] nameUserTemp = new String[1];
        
        try{
            URL url = new  URL(urlApi);
            InputStream is = url.openStream();
            JsonReader jsReader=Json.createReader(new InputStreamReader(is, "UTF-8"));
            JsonArray results= jsReader.readArray();
            Iterator<?> iterator = results.iterator();
            while(iterator.hasNext()){
                JsonObject jsonObject = (JsonObject) iterator.next();
                nameUserTemp[0] = jsonObject.get("id").toString()+"- "+jsonObject.get("name").toString();
                usersData.add(new String(jsonObject.get("id").toString()+","+jsonObject.get("name").toString()+
                        ","+jsonObject.get("username").toString()+","+jsonObject.get("email").toString()));
            }
            return usersData;
        }catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
    
    
    public static void getPublicationsAnsersUser(String userID){
        
        String urlApiPosts = "https://jsonplaceholder.typicode.com/users/"+userID+"/posts";
        try {
            URL url = new URL(urlApiPosts);
            InputStream is = url.openStream();
            JsonReader jsReader = Json.createReader(new InputStreamReader(is, "UTF-8"));
            JsonArray results = jsReader.readArray();
            Iterator<?> iterator = results.iterator();
            while (iterator.hasNext()) {
                JsonObject jsonObject = (JsonObject) iterator.next();
                String publicationID = jsonObject.get("id").toString();
                Posts.jTextArea1.append("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                Posts.jTextArea1.append(jsonObject.get("title").toString()+"\n\n");
                Posts.jTextArea1.append(jsonObject.get("body").toString()+"\n");
                Posts.jTextArea1.append("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                
                String urlApiAnswer = "https://jsonplaceholder.typicode.com/post/"+publicationID+"/comments";
                URL urlAnswer = new URL(urlApiAnswer);
                InputStream isAnswer = urlAnswer.openStream();
                JsonReader jsReaderAnswer = Json.createReader(new InputStreamReader(isAnswer, "UTF-8"));
                JsonArray resultsAnswer = jsReaderAnswer.readArray();
                Iterator<?> iteratorAnswer = resultsAnswer.iterator();
                while (iteratorAnswer.hasNext()) {
                    JsonObject jsonObjectAnser = (JsonObject) iteratorAnswer.next();
                    Posts.jTextArea1.append("                    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    Posts.jTextArea1.append("                             name: "+jsonObjectAnser.get("name").toString()+"\n");
                    Posts.jTextArea1.append("                             email: "+jsonObjectAnser.get("email").toString()+"\n");
                    Posts.jTextArea1.append("                             menssage: "+jsonObjectAnser.get("body").toString().replace("\n", " ")+"\n\n");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    
    public static void getUserActivFromApi(String userID) {
        String urlApi = "https://jsonplaceholder.typicode.com/users/"+userID+"/todos";
        
        try {
            URL url = new URL(urlApi);
            InputStream is = url.openStream();
            JsonReader jsReader = Json.createReader(new InputStreamReader(is, "UTF-8"));
            JsonArray results = jsReader.readArray();
            System.out.println("size " + results.size());
            for(int i = results.size()-1 ; i>=0; i--){
                JsonObject jsonObject = (JsonObject) results.getJsonObject(i);
                All.jTextArea1.append("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                All.jTextArea1.append("___________ Description: " + jsonObject.get("title").toString()+"\n");
                All.jTextArea1.append("___________ Finished: " + jsonObject.get("completed").toString()+"\n\n\n");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
}
