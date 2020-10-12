/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author elbrh
 */
public class SideMenuAdminBaseForm {
    
    public void setupSideMenu(Form f) {
      f.getToolbar().addMaterialCommandToSideMenu(" Events", FontImage.MATERIAL_COMMENT, e -> new EventListAdmin().getF().show());
        f.getToolbar().addMaterialCommandToSideMenu(" Articles", FontImage.MATERIAL_COMMENT, e -> new ArticleListAdmin().getF().show());
     

    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
