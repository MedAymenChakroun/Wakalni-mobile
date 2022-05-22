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
import static com.sun.javafx.fxml.expression.Expression.add;

/**
 *
 * @author malek guemri
 */
public class ForgotPasswordCodeForm extends Form  {
    public ForgotPasswordCodeForm(Resources theme, User u) {
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

        TextField code = new TextField("", "Code", 20, TextField.EMAILADDR);
        code.getAllStyles().setMargin(LEFT, 0);
        Label codeIcon = new Label("", "TextField");
        codeIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(codeIcon, FontImage.MATERIAL_EMAIL, 3);

        Button CodeButton = new Button("Confirm Code");
        CodeButton.setUIID("CodeButton");
       
        CodeButton.addActionListener(e-> {
            if(SessionManager.getCode().equals(code.getText().toString())){
                new ForgotPasswordResetForm(theme, u).show();
            }else{
                Dialog.show("Failed", "Incorrect code given", "ok", null);
            }
            
        });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(code).
                add(BorderLayout.WEST, codeIcon),
                CodeButton
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

    }

}
