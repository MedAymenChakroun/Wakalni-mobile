/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.formulaire.entities.Leftovers;
import com.esprit.formulaire.entities.Organisation;
import com.esprit.formulaire.services.LeftoversService;
import com.esprit.formulaire.services.OrganisationService;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class AddUpdateOrganisationForm extends Form {
    
        
private Organisation organisation;

    public AddUpdateOrganisationForm(Form previous, boolean add, Organisation o) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            this.setTitle("Organisation");

            Container nomContainer = new Container(BoxLayout.x());
            Container emailContainer = new Container(BoxLayout.x());
            Container adresseContainer = new Container(BoxLayout.x());
            Container numeroContainer = new Container(BoxLayout.x());

            TextField nomTF = new TextField();
            nomTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            TextField emailTF = new TextField();
            emailTF.getAllStyles().setAlignment(TextArea.CENTER); 

            TextField adresseTF = new TextField();
            adresseTF.getAllStyles().setAlignment(TextArea.CENTER);

            TextField numeroTF = new TextField();
            numeroTF.getAllStyles().setAlignment(TextArea.CENTER);
            
           
            Button submitBtn = new Button("");
           
            nomContainer.addAll(new Label("Nom: "),nomTF);
            emailContainer.addAll(new Label("Email: "),emailTF);
            adresseContainer.addAll(new Label("Adresse: "),adresseTF);
            numeroContainer.addAll(new Label("Numéro: "),numeroTF);

            
            if(add)
            {
            submitBtn.setText("Ajouter");
           
            ComboBox<String> leftoversCB = new ComboBox<>();
            ArrayList<Leftovers> leftovers = LeftoversService.getInstance().getAllLeftovers();
            for (Leftovers leftover : leftovers) {
                leftoversCB.addItem(Integer.toString(leftover.getLeftoverid()));
            }
            
            Container comboContainer  = new Container(BoxLayout.y());
            comboContainer.addAll(new Label("Leftovers"),leftoversCB);
                    
            submitBtn.addActionListener(e->{
              
                if(nomTF.getText()==""||emailTF.getText()==""||adresseTF.getText()==""||numeroTF.getText()=="")
                {
                    nomTF.getAllStyles().setBgColor(0xFF0000);
                    nomTF.repaint();
                    emailTF.getAllStyles().setBgColor(0xFF0000);
                    emailTF.repaint();
                    adresseTF.getAllStyles().setBgColor(0xFF0000);
                    adresseTF.repaint();
                    numeroTF.getAllStyles().setBgColor(0xFF0000);
                    numeroTF.repaint();
                    return;
                }                
            OrganisationService.getInstance().addOrganisation(nomTF.getText(),emailTF.getText(),adresseTF.getText(),numeroTF.getText(),leftoversCB.getSelectedItem());
            new OrganisationListForm().show();
            });
            
                                    this.addAll(nomContainer, emailContainer,adresseContainer,numeroContainer,comboContainer, submitBtn);

                
            }
            else
            {
            submitBtn.setText("Modifier");
            

            nomTF.setText(o.getNom());
            emailTF.setText(o.getEmail());
            adresseTF.setText(o.getAdresse());
            numeroTF.setText(String.valueOf(o.getNumero()));

            submitBtn.addActionListener(e->{
                
            if(nomTF.getText()==""||emailTF.getText()==""||adresseTF.getText()==""||numeroTF.getText()=="")
                {
                    nomTF.getAllStyles().setBgColor(0xFF0000);
                    nomTF.repaint();
                    emailTF.getAllStyles().setBgColor(0xFF0000);
                    emailTF.repaint();
                    adresseTF.getAllStyles().setBgColor(0xFF0000);
                    adresseTF.repaint();
                    numeroTF.getAllStyles().setBgColor(0xFF0000);
                    numeroTF.repaint();
                    return;
                }                  
            OrganisationService.getInstance().updateOrganisation(o.getOrganisationid(),nomTF.getText(),emailTF.getText(),adresseTF.getText(),numeroTF.getText());
            new OrganisationListForm().show();
            });
            
                        this.addAll(nomContainer, emailContainer,adresseContainer,numeroContainer, submitBtn);

            
            }
            
            this.setLayout(BoxLayout.y());

    }
    
}
