/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import java.net.*;

/**
 *
 * @author maxime
 */
public class Client {
    
    
    private static Client instance;
    
    protected Client () {}
    
    public static Client getInstance () {
        
        if(instance==null) {
            instance = new Client();       
        }      
        return instance;
        
    }
    
    public String connexion (String URL) {
        
        String message = "";
        String retour=System.getProperty("line.separator"); 

        try {
            
            URL aURL = new URL(URL);
            
            URL = aURL.getHost();
                    
            Socket socketClient = new Socket(URL,80); 
            
            BufferedReader  in = new BufferedReader(new InputStreamReader(socketClient.getInputStream(), "utf-8"));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())));
            
            out.println("GET /index.html HTTP/1.0\n");
            out.flush();
            
            message += "Information : Client connecté sur le serveur " + socketClient.getRemoteSocketAddress() + retour;
  
            message += "GET /index.html HTTP/1.0" + retour; 

            while (in.readLine() != null) { 
                message += in.readLine()+retour; // Afficher le message envoyé par le serveur
            } 

            in.close();
            out.close();
            socketClient.close(); 
        }
        catch(IOException e) {
            message = "Whoops! ça marche Pas ! \n Message d'erreur : "+e.getMessage();
        }
        
        return message;
    }
    
}
