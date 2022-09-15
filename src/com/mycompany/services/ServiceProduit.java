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
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Produit;
import com.mycompany.entities.User;
import com.mycompany.wakalni.SessionManager;
import com.mycompany.statics.statics;
import com.sun.mail.smtp.SMTPTransport;
import com.twilio.Twilio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author malek guemri
 */
public class ServiceProduit {
    
    
    public boolean resultOK = true;

    public static ServiceProduit instance = null;
    
    public static NetworkManager instances ;

    private ConnectionRequest req;
    
     public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public ServiceProduit() {
        req = new ConnectionRequest();
    }   
   /* public void ajouterProduit(Produit cm) {
        ConnectionRequest req1 = new ConnectionRequest() ;
        String url = statics.BASE_URL + "/mobile/addcommande?clientid=" + cm.getDatecreation()+ "&dateexpedition=" + cm.getDateexpedition()+"+&datearivee="+
                cm.getDatearivee()+"&panierid="+cm.getPanierid()+"&clientid="+cm.getClientid();

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);
    }*/
    public ArrayList<Produit> affichageProduit() {
        
        ConnectionRequest req1 = new ConnectionRequest() ;
        ArrayList<Produit> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displayproduit";
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
                        Produit c = new Produit();
                        
                        LinkedHashMap<String, Object> client = (LinkedHashMap<String, Object>) obj.get("crid");
                        String nomclient = client.get("firstname").toString();
                        

                     //   float panierid = Float.parseFloat(obj.get("panierid").toString());
                     //   float clientid = Float.parseFloat(obj.get("clientid").toString());
                      //  String datecreation = obj.get("question").toString();
                      //  String dateexpedition = obj.get("reponse").toString();
                       String nom = obj.get("nom").toString(); 
                       String type = obj.get("type").toString();  
                       float produiti = Float.parseFloat(obj.get("produitid").toString());
                        c.setProduitid((int) produiti);
                      //        c.setDatecreation(datecreation);
                     //   c.setDateexpedition(dateexpedition);
                    //    c.setDatearivee(datearivee);
                                   
                       c.setFirstname(nomclient);
                       c.setNom(nom);
                       c.setType(type);
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
