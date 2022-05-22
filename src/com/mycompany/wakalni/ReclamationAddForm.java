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
import com.codename1.ui.TextArea;
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
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class ReclamationAddForm extends SideMenuBaseForm {

    public ReclamationAddForm(Resources res/*, int idcommande*/) {

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

        add(new Label("Add a report Report", "TodayTitle"));

        Reclamation r = new Reclamation();
        TextField sujet = new TextField("", "Report Subject", 20, TextField.USERNAME);
        TextArea contenu = new TextArea("", 5, 5, TextField.USERNAME);
        contenu.setUIID("Toolbar");
       
        Button addButton = new Button("ADD");
        addButton.addActionListener(e -> {
            if (Dialog.show("Succes", "The report will be sent to be reviewed", "Ok", "Cancel")) {
                ServiceReclamation.getInstance().ajouterReclamation(new Reclamation(sujet.getText().toString(),contenu.getText().toString(),"en cours",120/*hethy hot f blasetha idcommande*/,SessionManager.getId()));
                new ReclamationForm(res).show();
            } else {
                new ReclamationForm(res).show();
            }
        });
        sujet.getAllStyles().setMargin(LEFT, 0);
        contenu.getAllStyles().setMargin(LEFT, 0);
        Label sujetIcon = new Label("", "TextField");
        Label contenuIcon = new Label("", "TextField");
        sujetIcon.getAllStyles().setMargin(RIGHT, 0);
        contenuIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(sujetIcon, FontImage.MATERIAL_SUBJECT, 3);
        FontImage.setMaterialIcon(contenuIcon, FontImage.MATERIAL_EDIT, 3);
        Container by = BoxLayout.encloseY(
                BorderLayout.center(sujet).
                add(BorderLayout.WEST, sujetIcon),
                BorderLayout.center(contenu).
                add(BorderLayout.WEST, contenuIcon),
                addButton
        );
        add(by);

        setupSideMenu(res);
    }

}
