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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author malek guemri
 */
public class ForgotPasswordResetForm extends Form {

    public ForgotPasswordResetForm(Resources theme, User u) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("to WAKALNI", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());

        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField cpassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        Button editButton = new Button("Save");
        editButton.setUIID("LoginButton");
        editButton.addActionListener(e -> {
            if (password.getText().equals(cpassword.getText())) {
                ServiceUser.getInstance().editPassword(password.getText().toString(), u.getID());
                new LoginForm(theme).show();
            } else {
                Dialog.show("Failed", "Password must match", "Ok", null);
            }
        });
        password.getAllStyles().setMargin(LEFT, 0);
        cpassword.getAllStyles().setMargin(LEFT, 0);
        Label passwordIcon = new Label("", "TextField");
        Label cpasswordIcon = new Label("", "TextField");

        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        cpasswordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK, 3);
        FontImage.setMaterialIcon(cpasswordIcon, FontImage.MATERIAL_LOCK, 3);
        Container by = BoxLayout.encloseY(
                welcome,
                BorderLayout.center(password).
                add(BorderLayout.WEST, passwordIcon),
                BorderLayout.center(cpassword).
                add(BorderLayout.WEST, cpasswordIcon),
                editButton
        );
        add(BorderLayout.CENTER, by);
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

    }

}
