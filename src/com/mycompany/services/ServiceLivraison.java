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
import com.mycompany.entities.Livraison;
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
public class ServiceLivraison {
    
    public boolean resultOK = true;

    public static ServiceLivraison instance = null;

    public static NetworkManager instances;

    private ConnectionRequest req;

    public static ServiceLivraison getInstance() {
        if (instance == null) {
            instance = new ServiceLivraison();
        }
        return instance;
    }

    public ServiceLivraison() {
        req = new ConnectionRequest();
    }
    
     public ArrayList<Livraison> affichageLivraison() {

        ConnectionRequest req1 = new ConnectionRequest();
        ArrayList<Livraison> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displaylivraison";
        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser jsonp;
                    jsonp = new JSONParser();
                    Map<String, Object> mapLivraison = jsonp.parseJSON(new CharArrayReader(new String(req1.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapLivraison.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Livraison c = new Livraison();

                        float id = Float.parseFloat(obj.get("id").toString());
                        LinkedHashMap<String, Object> livreur = (LinkedHashMap<String, Object>) obj.get("idlivreur");
                        float idlivreur = Float.valueOf(livreur.get("id").toString());
                        String livreurnom = livreur.get("firstname").toString();
                        String livreurtel = livreur.get("phonenumber").toString();
                        LinkedHashMap<String, Object> commande = (LinkedHashMap<String, Object>) obj.get("idcommande");
                        float idcommande = Float.valueOf(commande.get("commandeid").toString());
                        LinkedHashMap<String, Object> clientid = (LinkedHashMap<String, Object>) commande.get("clientid");
                        float idclient = Float.valueOf(clientid.get("id").toString());

                        c.setId((int) id);
                        c.setIdclient((int)idclient);
                        c.setIdcommande((int)idcommande);
                        c.setIdlivreur((int)idlivreur);
                        c.setLivreur(livreurnom);
                        c.setLivreurtel(livreurtel);
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
    
}
