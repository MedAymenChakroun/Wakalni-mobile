/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Panier;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Review;
import com.mycompany.services.ServicePanier;
import com.mycompany.services.ServiceProduit;
import com.mycompany.services.ServiceReview;

import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class ProductCatalogue extends SideMenuBaseForm {
    public ProductCatalogue(Resources res) {
        
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
                                new Label("", "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        tb.setTitleComponent(titleCmp);

        add(new Label("Produit", "TodayTitle"));

       ArrayList<Produit> produit = new ArrayList<>();
       produit = ServiceProduit.getInstance().affichageProduit();
       Container by = null;
       for(Produit q : produit){
          
           Label nom = new Label(String.valueOf(q.getNom()));
           Label type = new Label(String.valueOf(q.getType()));
           Label firstname = new Label(String.valueOf(q.getFirstname()));
           Label prix = new Label(String.valueOf(q.getPrix()));
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
            Button validate = new Button("validate");
            Button addreview = new Button("ADD REVIEW");
            addreview.addActionListener(e->{
                new ReviewAddForm(res,q.getProduitid()).show();
            });
           validate.addActionListener(e->{
               //Panier pm= new Panier(q.getProduitid(),q.getCrid(),1);
               ServicePanier.getInstance().ajouterPanier(new Panier(q.getProduitid(),SessionManager.getId(),1));
           });
       
            by = BoxLayout.encloseXCenter(
                BoxLayout.encloseYCenter(
                BorderLayout.center(nom).
                add(BorderLayout.WEST, questionIcon),
                BorderLayout.center(type).
                add(BorderLayout.WEST, promoIcon),
                BorderLayout.center(firstname).
                add(BorderLayout.WEST, reponseIcon),
                BorderLayout.center(prix).
                add(BorderLayout.WEST, dureeIcon),
                validate,
                addreview
                
        ));
      
        add(by);
        
       }
       setupSideMenu(res);
    }

  
}
