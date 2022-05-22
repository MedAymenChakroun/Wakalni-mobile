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
import com.mycompany.entities.Organisation;
import com.mycompany.statics.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class OrganisationService {
    
    
 public ArrayList<Organisation> Organisation;
    public Organisation organisation;

    public static OrganisationService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private OrganisationService() {
        req = new ConnectionRequest();
    }

    public static OrganisationService getInstance() {
        if (instance == null) {
            instance = new OrganisationService();
        }
        return instance;
    }

    public ArrayList<Organisation> parseOrganisations(String jsonText) {
        try {
            Organisation = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> organisationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) organisationListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                
                Organisation o = new Organisation();
     
                o.setOrganisationid((int) Float.parseFloat(obj.get("organisationid").toString()));
                o.setNom(obj.get("nom").toString());
                o.setEmail(obj.get("email").toString());
                o.setAdresse(obj.get("adresse").toString());
                o.setNumero((int)Float.parseFloat(obj.get("numero").toString()));
               // l.setDateexpiration(obj.get("Dateexpiration").toString());
             
                Organisation.add(o);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return Organisation;
    }
    
    public ArrayList<Organisation> getAllOrganisations() {
        String url = statics.BASE_URL + "/api/organisations";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Organisation = parseOrganisations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Organisation;
    }
    
    
    
    public void addOrganisation(String nom, String email, String adresse, String numero, String leftoverid ) {
        String url = statics.BASE_URL + "/api/organisation/add";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.addArgument("nom", nom);
        req.addArgument("email",email);
        req.addArgument("adresse", adresse);
        req.addArgument("numero", numero);
        req.addArgument("leftoverid", leftoverid);

        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void updateOrganisation(Integer Organisationid ,String nom, String email, String adresse, String numero) {
        String url = statics.BASE_URL + "/api/organisation/update";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        //req.addArgument("username", MyApplication.loggedUser.getUsername());
        req.addArgument("id", Organisationid.toString());
        req.addArgument("nom", nom);
        req.addArgument("email",email);
        req.addArgument("adresse", adresse);
        req.addArgument("numero", numero);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void deleteOrganisation(Integer organisationid) {
        String url = statics.BASE_URL + "/api/organisation/delete";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        //req.addArgument("username", MyApplication.loggedUser.getUsername());
       
        req.addArgument("organisationid", organisationid.toString());
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}

