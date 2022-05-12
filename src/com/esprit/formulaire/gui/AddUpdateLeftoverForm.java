/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.gui;


import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.formulaire.entities.Leftovers;
import com.esprit.formulaire.services.LeftoversService;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class AddUpdateLeftoverForm extends Form {
    
private Leftovers leftover;

    public AddUpdateLeftoverForm(Form previous, boolean add, Leftovers l) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            this.setTitle("Leftovers");

            Container sujetContainer = new Container(BoxLayout.x());
            Container typeContainer = new Container(BoxLayout.x());
            Container quantiteContainer = new Container(BoxLayout.x());
            
            
           
            TextField sujetTF = new TextField();
            sujetTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            TextField typeTF = new TextField();
            typeTF.getAllStyles().setAlignment(TextArea.CENTER);
            TextField quantiteTF = new TextField();
            quantiteTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            Button submitBtn = new Button("");
           
            sujetContainer.addAll(new Label("Sujet: "),sujetTF);
            typeContainer.addAll(new Label("type: "),typeTF);
            quantiteContainer.addAll(new Label("Quantite: "),quantiteTF);
            
            
            
            if(add)
            {
            submitBtn.setText("Ajouter");
            
            submitBtn.addActionListener(e->{
                if(sujetTF.getText()==""||typeTF.getText()==""||quantiteTF.getText()=="")
                {
                    sujetTF.getAllStyles().setBgColor(0xFF0000);
                    sujetTF.repaint();
                    
                    return;
                }
            LeftoversService.getInstance().addLeftover(sujetTF.getText(),typeTF.getText(),quantiteTF.getText());
            new LeftoversListForm().show();
            });
                
            }
            else
            {
            submitBtn.setText("Modifier");
            
            sujetTF.setText(l.getSujet());
            typeTF.setText(l.getType());
            quantiteTF.setText(String.valueOf(l.getQuantite()));
         

            submitBtn.addActionListener(e->{
                if(sujetTF.getText()==""||typeTF.getText()==""||quantiteTF.getText()=="")
                {
                    return;
                }
                
            LeftoversService.getInstance().updateLeftover(l.getLeftoverid(),sujetTF.getText(),typeTF.getText(),quantiteTF.getText());
            new LeftoversListForm().show();
            });
            }

            this.addAll(sujetContainer, typeContainer,quantiteContainer, submitBtn);
            this.setLayout(BoxLayout.y());

    }

}