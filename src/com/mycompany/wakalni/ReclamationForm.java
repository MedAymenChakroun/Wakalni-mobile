/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.io.Storage;
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
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceUser;
import com.sun.mail.smtp.SMTPTransport;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author malek guemri
 */
public class ReclamationForm extends SideMenuBaseForm {

    public ReclamationForm(Resources res) {

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

        add(new Label("Reports", "TodayTitle"));
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        ArrayList<Reclamation> reclamationuser = new ArrayList<>();
        reclamations = ServiceReclamation.getInstance().affichageReclamation();

        for (Reclamation r : reclamations) {
            if (r.getClientid() == SessionManager.getId()) {
                reclamationuser.add(r);
            }
        }
        for (Reclamation r : reclamationuser) {
            TextField email = new TextField(r.getClient(), "", 20, TextField.EMAILADDR);
            TextField sujet = new TextField(r.getSujet(), "", 20, TextField.USERNAME);
            TextField contenu = new TextField(r.getContenu(), "", 20, TextField.USERNAME);
            TextField etat = new TextField(r.getEtat(), "", 20, TextField.NUMERIC);
            TextField commande = new TextField(r.getCommande(), "", 20, TextField.PHONENUMBER);
            email.setEnabled(false);
            sujet.setEnabled(false);
            contenu.setEnabled(false);
            etat.setEnabled(false);
            commande.setEnabled(false);
            Button delButton = new Button("Delete");
            delButton.addActionListener(e -> {
                if (Dialog.show("Warning", "The report will be permanently deleted, Are you sure ?", "Ok", "Cancel")) {
                    ServiceReclamation.getInstance().deleteReclamation(r.getId());
                    new ReclamationForm(res).show();
                } else {
                    new ReclamationForm(res).show();
                }
            });
            email.getAllStyles().setMargin(LEFT, 0);
            sujet.getAllStyles().setMargin(LEFT, 0);
            contenu.getAllStyles().setMargin(LEFT, 0);
            etat.getAllStyles().setMargin(LEFT, 0);
            commande.getAllStyles().setMargin(LEFT, 0);
            Label emailIcon = new Label("", "TextField");
            Label sujetIcon = new Label("", "TextField");
            Label contenuIcon = new Label("", "TextField");
            Label etatIcon = new Label("", "TextField");
            Label commandeIcon = new Label("", "TextField");
            emailIcon.getAllStyles().setMargin(RIGHT, 0);
            sujetIcon.getAllStyles().setMargin(RIGHT, 0);
            contenuIcon.getAllStyles().setMargin(RIGHT, 0);
            etatIcon.getAllStyles().setMargin(RIGHT, 0);
            commandeIcon.getAllStyles().setMargin(RIGHT, 0);
            FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_EMAIL, 3);
            FontImage.setMaterialIcon(sujetIcon, FontImage.MATERIAL_SUBJECT, 3);
            FontImage.setMaterialIcon(contenuIcon, FontImage.MATERIAL_EDIT, 3);
            FontImage.setMaterialIcon(etatIcon, FontImage.MATERIAL_VERIFIED, 3);
            FontImage.setMaterialIcon(commandeIcon, FontImage.MATERIAL_SHOPPING_CART, 3);
            Container by = BoxLayout.encloseY(
                    BorderLayout.center(sujet).
                    add(BorderLayout.WEST, sujetIcon),
                    BorderLayout.center(contenu).
                    add(BorderLayout.WEST, contenuIcon),
                    BorderLayout.center(commande).
                    add(BorderLayout.WEST, commandeIcon),
                    BorderLayout.center(etat).
                    add(BorderLayout.WEST, etatIcon),
                    BorderLayout.center(email).
                    add(BorderLayout.WEST, emailIcon),
                    delButton
            );
            add(by);
        }

        setupSideMenu(res);
    }

    
}
