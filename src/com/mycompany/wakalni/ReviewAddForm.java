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
import com.mycompany.entities.Review;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceReview;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author malek guemri
 */
public class ReviewAddForm extends SideMenuBaseForm {

    public ReviewAddForm(Resources res, int idproduct) {

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

        Review r = new Review();
        TextField note = new TextField("", "Report Subject", 20, TextField.NUMERIC);
        TextArea comment = new TextArea("", 5, 5, TextField.USERNAME);
        comment.setUIID("Toolbar");
       
        Button addButton = new Button("ADD");
        addButton.addActionListener(e -> {
            if (Dialog.show("Succes", "Thank you for reviewing this product", "Ok", null)) {
                ServiceReview.getInstance().ajouterReview(new Review(Integer.valueOf(note.getText().toString()),comment.getText().toString(),SessionManager.getId(), idproduct));
                new ReviewForm(res).show();
            } else {
                new ReviewForm(res).show();
            }
        });
        note.getAllStyles().setMargin(LEFT, 0);
        comment.getAllStyles().setMargin(LEFT, 0);
        Label noteIcon = new Label("", "TextField");
        Label commentIcon = new Label("", "TextField");
        noteIcon.getAllStyles().setMargin(RIGHT, 0);
        commentIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(noteIcon, FontImage.MATERIAL_NOTE_ADD, 3);
        FontImage.setMaterialIcon(commentIcon, FontImage.MATERIAL_EDIT, 3);
        Container by = BoxLayout.encloseY(
                BorderLayout.center(note).
                add(BorderLayout.WEST, noteIcon),
                BorderLayout.center(comment).
                add(BorderLayout.WEST, commentIcon),
                addButton
        );
        add(by);

        setupSideMenu(res);
    }

}