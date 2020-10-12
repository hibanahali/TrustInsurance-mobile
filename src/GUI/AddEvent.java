/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Event;
import Service.EvenementService;
import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 *
 * @author elbrh
 */
public class AddEvent   {

    Form f;
    Resources theme;

    public AddEvent() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Add Event", BoxLayout.y());
        Container root = new Container(BoxLayout.y());
        TextField texte = new TextField();
        TextField desc = new TextField();
        Picker startDate = new Picker();
        Picker dueDate = new Picker();
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, g -> {
            EventList h = new EventList();
            h.getF().showBack();
        });
        Validator v = new Validator();
        v.addConstraint(texte, new LengthConstraint(5)).
                addConstraint(desc, new LengthConstraint(6));
        Button add = new Button("Ajouter");
        v.addSubmitButtons(add);
        Container img = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button imgBtn = new Button("upload photo");
        Event evenement = new Event();
        imgBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_CAMERA, imgBtn.getAllStyles()));
        imgBtn.addActionListener(e -> {
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (filePath != null) {
                try {
                    img.removeAll();
                    evenement.setImage(filePath);
                    Label label = new Label();
                    Image placeholder;
                    placeholder = Image.createImage(filePath);
                    label.setIcon(placeholder);
                    img.add(label);
                    img.revalidate();
                } catch (IOException ex) {
                }

            }
        });

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (v.isValid() && evenement.getImage() != null) {
                    EvenementService es = new EvenementService();
                    evenement.setDate_debut(startDate.getDate());
                    evenement.setDate_fin(dueDate.getDate());
                    evenement.setNom(texte.getText());
                    evenement.setDescription(desc.getText());
                    es.NewEvent(evenement);
                    EventListAdmin h = new EventListAdmin();
                    h.getF().showBack();
                }
            }
        });
        Label nomL = new Label("Contenu :");
        Label descL = new Label("Type :");
        Label startDateL = new Label("start Date :");
        Label dueDateL = new Label("Due Date :");
        root.add(nomL);
        root.add(texte);
        root.add(createLineSeparator());
        root.add(descL);
        root.add(desc);
        root.add(createLineSeparator());
        root.add(startDateL);
        root.add(startDate);
        root.add(createLineSeparator());
        root.add(dueDateL);
        root.add(dueDate);
        root.add(createLineSeparator());
        root.add(imgBtn);
        root.add(img);
        root.add(createLineSeparator());
        root.add(add);
        f.add(root);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
}
