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
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.Review;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceReview;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class ReviewForm extends SideMenuBaseForm {

    public ReviewForm(Resources res) {

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

        add(new Label("My Reviews", "TodayTitle"));
        ArrayList<Review> reviews = new ArrayList<>();
        ArrayList<Review> reviewuser = new ArrayList<>();
        reviews = ServiceReview.getInstance().affichageReview();

        for (Review r : reviews) {
            if (r.getUserid() == SessionManager.getId()) {
                reviewuser.add(r);
            }
        }
        for (Review r : reviewuser) {
            TextField email = new TextField(r.getUser(), "", 20, TextField.EMAILADDR);
            TextField note = new TextField(String.valueOf(r.getNote())+"/20", "", 20, TextField.USERNAME);
            TextField comment = new TextField(r.getComment(), "", 20, TextField.USERNAME);
            TextField product = new TextField(r.getProduct(), "", 20, TextField.PHONENUMBER);
            email.setEnabled(false);
            note.setEnabled(false);
            comment.setEnabled(false);
            product.setEnabled(false);
            Button delButton = new Button("Delete");
            delButton.addActionListener(e -> {
                if (Dialog.show("Warning", "The review will be permanently deleted, Are you sure ?", "Ok", "Cancel")) {
                    ServiceReview.getInstance().deleteReview(r.getId());
                    new ReclamationForm(res).show();
                    } else {
                    new ReclamationForm(res).show();
                }
            });
            email.getAllStyles().setMargin(LEFT, 0);
            note.getAllStyles().setMargin(LEFT, 0);
            comment.getAllStyles().setMargin(LEFT, 0);
            product.getAllStyles().setMargin(LEFT, 0);
            Label emailIcon = new Label("", "TextField");
            Label noteIcon = new Label("", "TextField");
            Label commentIcon = new Label("", "TextField");
            Label productIcon = new Label("", "TextField");
            emailIcon.getAllStyles().setMargin(RIGHT, 0);
            noteIcon.getAllStyles().setMargin(RIGHT, 0);
            commentIcon.getAllStyles().setMargin(RIGHT, 0);
            productIcon.getAllStyles().setMargin(RIGHT, 0);
            FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_EMAIL, 3);
            FontImage.setMaterialIcon(noteIcon, FontImage.MATERIAL_SUBJECT, 3);
            FontImage.setMaterialIcon(commentIcon, FontImage.MATERIAL_EDIT, 3);
            FontImage.setMaterialIcon(productIcon, FontImage.MATERIAL_SHOPPING_CART, 3);
            Container by = BoxLayout.encloseY(
                    BorderLayout.center(product).
                    add(BorderLayout.WEST, productIcon),
                    BorderLayout.center(comment).
                    add(BorderLayout.WEST, commentIcon),
                    BorderLayout.center(note).
                    add(BorderLayout.WEST, noteIcon),
                    BorderLayout.center(email).
                    add(BorderLayout.WEST, emailIcon),
                    delButton
            );
            add(by);
        }

        setupSideMenu(res);
    }

}

