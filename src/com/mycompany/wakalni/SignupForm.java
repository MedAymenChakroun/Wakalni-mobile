/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.mycompany.services.ServiceUser;
import java.util.Vector;

/**
 *
 * @author malek guemri
 */
public class SignupForm extends Form {
    public SignupForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("to WAKALNI", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
       Vector <String> vectorRoles;
       vectorRoles = new Vector();
       vectorRoles.add("Client");
       vectorRoles.add("Chef restau");
       vectorRoles.add("Livreur");
       ComboBox <String> roles = new ComboBox <> (vectorRoles);
        
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField firstname = new TextField("", "First Name", 20, TextField.USERNAME);
        TextField lastname = new TextField("", "Last Name", 20, TextField.USERNAME);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField cpassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField age = new TextField("", "Age", 20, TextField.NUMERIC);
        TextField phonenumber = new TextField("", "Phone Number", 20, TextField.PHONENUMBER);
        email.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        firstname.getAllStyles().setMargin(LEFT, 0);
        lastname.getAllStyles().setMargin(LEFT, 0);
        cpassword.getAllStyles().setMargin(LEFT, 0);
        age.getAllStyles().setMargin(LEFT, 0);
        phonenumber.getAllStyles().setMargin(LEFT, 0);
        roles.getAllStyles().setMargin(LEFT, 0);
        Label emailIcon = new Label("", "TextField");
        Label firstnameIcon = new Label("", "TextField");
        Label lastnameIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        Label cpasswordIcon = new Label("", "TextField");
        Label ageIcon = new Label("", "TextField");
        Label phonenumberIcon = new Label("", "TextField");
        Label rolesIcon = new Label("","TextField");
        emailIcon.getAllStyles().setMargin(RIGHT, 0);
        firstname.getAllStyles().setMargin(RIGHT, 0);
        lastname.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        cpasswordIcon.getAllStyles().setMargin(RIGHT, 0);
        ageIcon.getAllStyles().setMargin(RIGHT, 0);
        phonenumberIcon.getAllStyles().setMargin(RIGHT, 0);
        rolesIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_EMAIL, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(cpasswordIcon,FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(firstnameIcon,FontImage.MATERIAL_PERSON, 3);
        FontImage.setMaterialIcon(lastnameIcon,FontImage.MATERIAL_PERSON, 3);
        FontImage.setMaterialIcon(phonenumberIcon,FontImage.MATERIAL_PHONE, 3);
        FontImage.setMaterialIcon(ageIcon,FontImage.MATERIAL_PERSON, 3);
        FontImage.setMaterialIcon(rolesIcon,FontImage.MATERIAL_PERSON, 3);
        
        Button signupButton = new Button("SIGNUP");
        signupButton.setUIID("LoginButton");
        signupButton.addActionListener(e -> new LoginForm(theme).show());
        
        Button loginButton = new Button("ALREADY HAVE AN ACCOUNT ?");
        loginButton.setUIID("CreateNewAccountButton");
        loginButton.addActionListener(e -> new LoginForm(theme).show());
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon),
                BorderLayout.center(firstname).
                        add(BorderLayout.WEST, firstnameIcon),
                BorderLayout.center(lastname).
                        add(BorderLayout.WEST, lastnameIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                BorderLayout.center(cpassword).
                        add(BorderLayout.WEST, cpasswordIcon),
                BorderLayout.center(age).
                        add(BorderLayout.WEST, ageIcon),
                BorderLayout.center(phonenumber).
                        add(BorderLayout.WEST, phonenumberIcon),
                BorderLayout.center(roles).
                        add(BorderLayout.WEST, rolesIcon),
                signupButton,
                loginButton
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
        
        signupButton.addActionListener((e)->{
            ServiceUser.getInstance().signup(email.getText().toString(), firstname.getText().toString(), lastname.getText().toString(), password.getText().toString(), cpassword.getText().toString(), Integer.parseInt(age.getText()), Integer.parseInt(phonenumber.getText()), roles.getSelectedItem().toString(), theme);
            Dialog.show("Success","account is saved","OK",null);
            new LoginForm(theme).show();
        });
    }
    
}
