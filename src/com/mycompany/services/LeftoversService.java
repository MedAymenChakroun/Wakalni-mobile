/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author dell
 */

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Leftovers;
import com.mycompany.statics.statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class LeftoversService {
    
    
    public ArrayList<Leftovers> leftovers;
    public Leftovers leftover;

    public static LeftoversService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private LeftoversService() {
        req = new ConnectionRequest();
    }

    public static LeftoversService getInstance() {
        if (instance == null) {
            instance = new LeftoversService();
        }
        return instance;
    }

    public ArrayList<Leftovers> parseLeftover(String jsonText) {
        try {
            leftovers = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> LeftoverListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) LeftoverListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                
                Leftovers l = new Leftovers();
     
                l.setLeftoverid((int) Float.parseFloat(obj.get("leftoverid").toString()));
                l.setSujet(obj.get("sujet").toString());
                l.setType(obj.get("type").toString());
                l.setQuantite((int)Float.parseFloat(obj.get("quantite").toString()));

              // l.setDateexpiration(obj.get("Dateexpiration").toString());
             
                leftovers.add(l);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return leftovers;
    }
    
    public ArrayList<Leftovers> getAllLeftovers() {
        String url = statics.BASE_URL + "/api/leftovers";//url requte w get w ba3ed post 
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                leftovers = parseLeftover(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return leftovers;
    }
    
    
    
    public void addLeftover(String sujet, String type, String quantite ) {
        String url = statics.BASE_URL + "/api/leftover/add";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.addArgument("sujet", sujet);
        req.addArgument("type", type);
        req.addArgument("quantite", quantite);
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void updateLeftover(Integer leftoverid,String sujet, String type, String quantite) {
        String url = statics.BASE_URL + "/api/leftover/update";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        //req.addArgument("username", MyApplication.loggedUser.getUsername());
        req.addArgument("leftoverid", leftoverid.toString());
         req.addArgument("sujet", sujet);
        req.addArgument("type", type);
        req.addArgument("quantite", quantite);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void deleteLeftover(Integer leftoverid) {
        String url = statics.BASE_URL + "/api/leftover/delete";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        //req.addArgument("username", MyApplication.loggedUser.getUsername());
       
        req.addArgument("leftoverid", leftoverid.toString());
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}
