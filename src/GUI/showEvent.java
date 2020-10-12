/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Event;
import Service.EvenementService;
import Utils.ConnectedUser;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;

/**
 *
 * @author elbrh
 */
public class showEvent {

    Form f;
    Resources theme;

    public showEvent(Event evenement) {
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.setWidth(Display.getInstance().getDisplayWidth());
        EvenementService se = new EvenementService();

        int deviceWidth = Display.getInstance().getDisplayWidth() / 2;
        Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        URLImage imgsv = URLImage.createToStorage(encImage,
                "http://localhost/PIJFinal/web/uploads/" + evenement.getImage(),
                "http://localhost/PIJFinal/web/uploads/" + evenement.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer imgvb = new ImageViewer(imgsv);
        SpanLabel nom = new SpanLabel("Nom : " + evenement.getNom());
        SpanLabel desc = new SpanLabel("Description : " + evenement.getDescription());
        SpanLabel dateDebut = new SpanLabel("Date Debut : " + evenement.getDate_debut());
        SpanLabel dateFin = new SpanLabel("Date Fin : " + evenement.getDate_fin());
        SpanLabel nbp = new SpanLabel("Nombre Participant : " + evenement.getNb_participant());
        c.add(nom);
        c.add(imgvb);
        c.add(desc);
        c.add(dateDebut);
        c.add(dateFin);
        c.add(nbp);
        f = new Form(evenement.getNom(), new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, g
                -> {
                    if (ConnectedUser.getUser().getRoles().indexOf("ROLE_CLIENT") > -1) {
                        EventList h = new EventList();
                        h.getF().show();
                    } else {
                        EventListAdmin h = new EventListAdmin();
                        h.getF().showBack();
                    }
                });

        c.getAllStyles().setBorder(Border.createLineBorder(3));

        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.add(cc);
        cc.getAllStyles().setBorder(Border.createGrooveBorder(2));
        Button participer = new Button("Participer");
        participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EvenementService e = new EvenementService();
                e.ParticipeEvent(evenement);
                showEvent se = new showEvent(evenement);
                se.getF().show();
            }
        });
        f.add(c);
        f.add(participer);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
