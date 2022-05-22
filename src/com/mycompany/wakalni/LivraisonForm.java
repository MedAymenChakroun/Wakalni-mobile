/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Livraison;
import com.mycompany.entities.Review;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceLivraison;
import com.mycompany.services.ServiceReview;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class LivraisonForm extends SideMenuBaseForm {

    public LivraisonForm(Resources res) {

        super(BoxLayout.y());
        setUIID("Toolbar");
        User u = new User();
        u = ServiceUser.getInstance().getCurrent(SessionManager.getId());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("01.jpg");
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
                                new Label(u.getFirstname(), "Title"),
                                new Label(u.getEmail(), "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        tb.setTitleComponent(titleCmp);

        add(new Label("My deliveries", "TodayTitle"));
        ArrayList<Livraison> livraisons = new ArrayList<>();
        ArrayList<Livraison> livraisonuser = new ArrayList<>();
        livraisons = ServiceLivraison.getInstance().affichageLivraison();
        System.out.print("trueeeeee"+livraisons);

        for (Livraison r : livraisons) {
            if (r.getIdclient()== SessionManager.getId()) {
                livraisonuser.add(r);
                System.out.print("trueeeeee"+livraisonuser);
            }else{
                System.out.print("falseeeee"+livraisonuser);
            }
        }
        for (Livraison r : livraisonuser) {
            TextField commande = new TextField(String.valueOf("ID Commande : "+r.getIdcommande()), "", 20, TextField.EMAILADDR);
            TextField livreur = new TextField(String.valueOf("Livreur : "+r.getLivreur()), "", 20, TextField.USERNAME);
            TextField livreurtel = new TextField("Tel livreur : "+r.getLivreurtel(), "", 20, TextField.PHONENUMBER);
            commande.setEnabled(false);
            livreur.setEnabled(false);
            livreurtel.setEnabled(false);
            
            commande.getAllStyles().setMargin(LEFT, 0);
            livreur.getAllStyles().setMargin(LEFT, 0);
            livreurtel.getAllStyles().setMargin(LEFT, 0);
            Label commandeIcon = new Label("", "TextField");
            Label livreurIcon = new Label("", "TextField");
            Label livreurtelIcon = new Label("", "TextField");
            commandeIcon.getAllStyles().setMargin(RIGHT, 0);
            livreurIcon.getAllStyles().setMargin(RIGHT, 0);
            livreurtelIcon.getAllStyles().setMargin(RIGHT, 0);
            FontImage.setMaterialIcon(commandeIcon, FontImage.MATERIAL_SHOPPING_CART, 3);
            FontImage.setMaterialIcon(livreurIcon, FontImage.MATERIAL_PERSON, 3);
            FontImage.setMaterialIcon(livreurtelIcon, FontImage.MATERIAL_PHONE, 3);
            Container by = BoxLayout.encloseY(
                    BorderLayout.center(commande).
                    add(BorderLayout.WEST, commandeIcon),
                    BorderLayout.center(livreur).
                    add(BorderLayout.WEST, livreurIcon),
                    BorderLayout.center(livreurtel).
                    add(BorderLayout.WEST, livreurtelIcon)
            );
            add(by);
        }

        setupSideMenu(res);
    }

}


