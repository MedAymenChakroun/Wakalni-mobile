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
import com.mycompany.entities.User;
import com.mycompany.wakalni.SessionManager;
import com.mycompany.statics.statics;
import com.sun.mail.smtp.SMTPTransport;
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
public class ServiceCommande {
    
    
    public boolean resultOK = true;

    public static ServiceCommande instance = null;
    
    public static NetworkManager instances ;

    private ConnectionRequest req;
    
     public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public ServiceCommande() {
        req = new ConnectionRequest();
    }
    
    public void ajouterCommande(Commande cm) {

        ConnectionRequest req1 = new ConnectionRequest() ;
        String url = statics.BASE_URL + "/mobile/addcommande?datecreation=2022-05-03 00:00:00" + "&dateexpedition=2023-05-03 00:00:00" +"+&datearivee=2027-05-03 00:00:00"+
               "&panierid="+cm.getPanierid()+"&clientid="+cm.getClientid();

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

    }

    public ArrayList<Commande> affichageCommande() {
        
        ConnectionRequest req1 = new ConnectionRequest() ;
        ArrayList<Commande> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displayCommande";
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
                        Commande c = new Commande();
                        LinkedHashMap<String, Object> panier = (LinkedHashMap<String, Object>) obj.get("panierid");
                        String productnom = panier.get("quantite").toString();
                        String productprix = panier.get("prixprod").toString();
                        LinkedHashMap<String, Object> client = (LinkedHashMap<String, Object>) obj.get("clientid");
                        String nomclient = client.get("firstname").toString();

                        float commandeid = Float.parseFloat(obj.get("commandeid").toString());
                     //   float panierid = Float.parseFloat(obj.get("panierid").toString());
                     //   float clientid = Float.parseFloat(obj.get("clientid").toString());
                      //  String datecreation = obj.get("question").toString();
                      //  String dateexpedition = obj.get("reponse").toString();
                      //  String datearivee = obj.get("reponse").toString();                     
                      //  c.setId((int) commandeid);
                      //  c.setPanierid((int) panierid);
                      //        c.setDatecreation(datecreation);
                     //   c.setDateexpedition(dateexpedition);
                    //    c.setDatearivee(datearivee);
                       c.setQuantite((Integer.parseInt(productnom)));
                       c.setPrixproduit((Float.parseFloat(productprix)));
                      c.setDatecreation(nomclient);                
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
