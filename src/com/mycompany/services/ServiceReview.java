/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Review;
import com.mycompany.statics.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author malek guemri
 */
public class ServiceReview {
    
    public boolean resultOK = true;

    public static ServiceReview instance = null;

    public static NetworkManager instances;

    private ConnectionRequest req;

    public static ServiceReview getInstance() {
        if (instance == null) {
            instance = new ServiceReview();
        }
        return instance;
    }

    public ServiceReview() {
        req = new ConnectionRequest();
    }

    public void ajouterReview(Review rev) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/addreview?note=" + rev.getNote()+ "&comment=" + rev.getComment()+ "&clientid=" + rev.getUserid()+ "&productid=" + rev.getProductid();

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

    }

    public ArrayList<Review> affichageReview() {

        ConnectionRequest req1 = new ConnectionRequest();
        ArrayList<Review> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displayreview";
        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser jsonp;
                    jsonp = new JSONParser();
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req1.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Review c = new Review();

                        float id = Float.parseFloat(obj.get("reviewid").toString());
                        float note = Float.valueOf(obj.get("note").toString());
                        String comment = obj.get("commentaire").toString();
                        LinkedHashMap<String, Object> produit = (LinkedHashMap<String, Object>) obj.get("produitid");
                        String productnom = produit.get("nom").toString();
                        float productid = Float.valueOf(produit.get("produitid").toString());
                        LinkedHashMap<String, Object> client = (LinkedHashMap<String, Object>) obj.get("utilisateurid");
                        String email = client.get("email").toString();
                        float idclient = Float.valueOf(client.get("id").toString());

                        c.setId((int) id);
                        c.setNote((int)note);
                        c.setComment(comment);
                        c.setProduct(productnom);
                        c.setProductid((int)productid);
                        c.setUser(email);
                        c.setUserid((int)idclient);
                        result.add(c);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req1);
        return result;
    }

    public boolean deleteReview(int id) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/deletereview?id=" + id;

        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req1.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);
        return resultOK;

    }

    public boolean modifierReview(Review rev) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/updatereview?id=" + rev.getId() + "&sujet=" + rev.getNote()+ "&comment=" + rev.getComment()+ "&clientid=" + rev.getUserid()+ "&productid=" + rev.getProductid();

        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req1.getResponseCode() == 200;
                req1.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

        System.out.println(resultOK);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;
    }
}

