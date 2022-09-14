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
import com.mycompany.entities.Panier;
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
public class ServicePanier {
    
    
    public boolean resultOK = true;

    public static ServicePanier instance = null;
    
    public static NetworkManager instances ;

    private ConnectionRequest req;
    
     public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    public ServicePanier() {
        req = new ConnectionRequest();
    }
    
    public void ajouterPanier(Panier cm) {

        ConnectionRequest req1 = new ConnectionRequest() ;
        String url = statics.BASE_URL + "/mobile/addpanier?produitid=" + cm.getProduitid()+ "&clientid=" + cm.getClientid()+"&quantite="+
                cm.getQuantite()+"&prixprod=0";

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

    }

    public ArrayList<Panier> affichagePanier() {
        
        ConnectionRequest req1 = new ConnectionRequest() ;
        ArrayList<Panier> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displaypanier";
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
                        Panier c = new Panier();
                        LinkedHashMap<String, Object> panier = (LinkedHashMap<String, Object>) obj.get("produitid");
                        String productnom = panier.get("nom").toString();
                        float productprix = Float.valueOf(panier.get("prix").toString());

                

                        float quantite = Float.parseFloat(obj.get("quantite").toString());
                        float panierid = Float.parseFloat(obj.get("panierid").toString());
                      //  String datecreation = obj.get("question").toString();
                      //  String datearivee = obj.get("reponse").toString();                     
                      //  c.setId((int) commandeid);
                        c.setPanierid((int) panierid);
                      //        c.setDatecreation(datecreation);
                     //   c.setDateexpedition(dateexpedition);
                    //    c.setDatearivee(datearivee);
                    
                       c.setQuantite((int) quantite);
                       c.setNom(productnom);
                       c.setPrix(productprix);
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
     public void sendMail(User u ,Resources res) {
        try {

            Properties props = new Properties();
          props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.mydomain.com");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar

            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("Commande <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, u.getEmail());
            msg.setSubject("Votre commande a eté crée !");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            SessionManager.setCode(ServiceUser.getInstance().Random6Digits());
            
            String txt = "Welcome to WAKALNI , votre commande a ete crée avec succés";

            msg.setText(txt);
            

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");

            st.connect("smtp.gmail.com", 587, "ahmed.rahal@esprit.tn", "213JMT3199");

            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : " + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

}}
