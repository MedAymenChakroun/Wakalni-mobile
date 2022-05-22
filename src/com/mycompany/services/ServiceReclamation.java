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
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.User;
import com.mycompany.statics.statics;
import com.mycompany.wakalni.SessionManager;
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
public class ServiceReclamation {
    
    public boolean resultOK = true;

    public static ServiceReclamation instance = null;

    public static NetworkManager instances;

    private ConnectionRequest req;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public void ajouterReclamation(Reclamation rec) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/addreclamation?sujet=" + rec.getSujet()+ "&contenu=" + rec.getContenu()+ "&etat=" + rec.getEtat()+ "&commandeid=" + rec.getCommandeid()+ "&clientid=" + rec.getClientid();

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

    }

    public ArrayList<Reclamation> affichageReclamation() {

        ConnectionRequest req1 = new ConnectionRequest();
        ArrayList<Reclamation> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displayreclamation";
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
                        Reclamation c = new Reclamation();

                        float id = Float.parseFloat(obj.get("reclamationid").toString());
                        String sujet = obj.get("sujet").toString();
                        String contenu = obj.get("contenu").toString();
                        String etat = obj.get("etat").toString();
                        LinkedHashMap<String, Object> commande = (LinkedHashMap<String, Object>) obj.get("commandeid");
                        String commandeid = commande.get("commandeid").toString();
                        LinkedHashMap<String, Object> client = (LinkedHashMap<String, Object>) obj.get("clientid");
                        String email = client.get("email").toString();
                        float idclient = Float.valueOf(client.get("id").toString());

                        c.setId((int) id);
                        c.setSujet(sujet);
                        c.setContenu(contenu);
                        c.setEtat(etat);
                        c.setCommande(commandeid);
                        c.setClient(email);
                        c.setClientid((int)idclient);
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

    public boolean deleteReclamation(int id) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/deletereclamation?id=" + id;

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

    public boolean modifierSingle(Reclamation rec) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/updatereclamation?id=" + rec.getId() + "&sujet=" + rec.getSujet()+ "&contenu=" + rec.getContenu()+ "&etat=" + rec.getEtat()+ "&commandeid=" + rec.getCommande()+ "&clientid=" + rec.getClient();

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
