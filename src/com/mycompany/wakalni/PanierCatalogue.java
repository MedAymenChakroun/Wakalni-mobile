/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.sendSMS;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Panier;
import com.mycompany.entities.Produit;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServiceUser;

import com.mycompany.services.ServicePanier;
import com.mycompany.services.ServiceProduit;


import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class PanierCatalogue extends SideMenuBaseForm {
    public PanierCatalogue(Resources res) {
        
        
        super(BoxLayout.y());
        setUIID("LoginForm");
         
       
        //u = ServiceUser.getInstance().getCurrent(SessionManager.getId());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("", "Title"),
                                new Label("Panier", "TodayTitle"),
                                new Label("", "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        tb.setTitleComponent(titleCmp);

 

       ArrayList<Panier> panier = new ArrayList<>();
       panier = ServicePanier.getInstance().affichagePanier();
       Container by = null;
       for(Panier q : panier){
          
                    Label nom = new Label(String.valueOf(q.getQuantite()));
           Label type = new Label(String.valueOf(q.getNom()));
           Label firstname = new Label(String.valueOf(q.getPrix()));

           Label dureeIcon = new Label("", "TextField");
           Label questionIcon = new Label("","TextField");
           Label reponseIcon = new Label("","TextField");
           Label promoIcon = new Label("","TextField");
           TextField rep = new TextField("","Answer");
           dureeIcon.getAllStyles().setMargin(RIGHT, 0);
           questionIcon.getAllStyles().setMargin(RIGHT, 0);
           promoIcon.getAllStyles().setMargin(RIGHT, 0);
           reponseIcon.getAllStyles().setMargin(RIGHT, 0);
           FontImage.setMaterialIcon(dureeIcon, FontImage.MATERIAL_TIMER, 3);
           FontImage.setMaterialIcon(questionIcon, FontImage.MATERIAL_QUESTION_ANSWER, 3);
           FontImage.setMaterialIcon(reponseIcon, FontImage.MATERIAL_VERIFIED, 3);
           FontImage.setMaterialIcon(promoIcon, FontImage.MATERIAL_MONETIZATION_ON, 3);
            Button validate = new Button("Commander");
           validate.addActionListener(e->{
               //Panier pm= new Panier(q.getProduitid(),q.getCrid(),1);
               ServiceCommande.getInstance().ajouterCommande(new Commande(q.getPanierid(),SessionManager.getId()));   
            User X = new User();
             X = ServiceUser.getInstance().getCurrent(SessionManager.getId());
            String aa= X.getEmail().toString();
             System.out.println("xxxxx"+aa);
             ServicePanier.send();
           
           // ServiceUser.getInstance().sendMail(X, res);

           });
       
            by = BoxLayout.encloseXCenter(
                BoxLayout.encloseXCenter(
                BorderLayout.center(nom).
                add(BorderLayout.WEST, questionIcon),
                BorderLayout.center(type).
                add(BorderLayout.WEST, promoIcon),
                BorderLayout.center(firstname).
                add(BorderLayout.WEST, reponseIcon),
                validate
                
        ));
      
        add(by);
       }        setupSideMenu(res);
       
    }

}
