/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Article;
import Service.ArticleService;
import com.codename1.capture.Capture;
import com.codename1.ui.Button;
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
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 *
 * @author elbrh
 */
public class EditArticle extends SideMenuAdminBaseForm{
    Form f;
    Resources theme;
    public EditArticle(Article article){
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Edit Article", BoxLayout.y());
        super.setupSideMenu(f);
        Container root = new Container(BoxLayout.y());
        TextField texte = new TextField();
        TextField desc = new TextField();
        texte.setText(article.getTitre());
        desc.setText(article.getDescription());
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, g -> {
            ArticleListAdmin h = new ArticleListAdmin();
            h.getF().showBack();
        });
        Validator v = new Validator();
        v.addConstraint(texte, new LengthConstraint(5)).
                addConstraint(desc, new LengthConstraint(6));
        Button add = new Button("Ajouter");
        v.addSubmitButtons(add);
        Container img = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button imgBtn = new Button("upload photo");
        imgBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_CAMERA, imgBtn.getAllStyles()));
        imgBtn.addActionListener(e -> {
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (filePath != null) {
                try {
                    img.removeAll();
                    article.setImage(filePath);
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
                if (v.isValid()) {
                    ArticleService es = new ArticleService();
                    article.setTitre(texte.getText());
                    article.setDescription(desc.getText());
                    es.EditArticle(article);
                    ArticleListAdmin h = new ArticleListAdmin();
                    h.getF().showBack();
                }
            }
        });
        Label nomL = new Label("titre :");
        Label descL = new Label("desc :");
        root.add(nomL);
        root.add(texte);
        root.add(createLineSeparator());
        root.add(descL);
        root.add(desc);
        root.add(createLineSeparator());
        root.add(imgBtn);
        root.add(img);
        root.add(createLineSeparator());
        root.add(add);
        f.add(root);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
