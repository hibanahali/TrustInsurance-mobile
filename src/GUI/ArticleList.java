/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Article;
import Service.ArticleService;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author elbrh
 */
public class ArticleList extends SideMenuBaseForm {

    Form f;
    Resources theme;

    public ArticleList() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Article List", BoxLayout.y());
        ArticleService rs = new ArticleService();
        ArrayList<Article> p = rs.getAllArticles();
        ListModel<String> autoP = new DefaultListModel<>();
        ListModel<URLImage> pictures = new DefaultListModel<>();
        final int size = Display.getInstance().convertToPixels(7);
        final EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(size, size, 0xffcccccc), true);
        for (Article pr : p) {
            autoP.addItem(pr.getTitre());
            URLImage urli = URLImage.createToStorage(placeholder,
                    "http://localhost/PIJFinal/web/uploads/" + pr.getImage(),
                    "http://localhost/PIJFinal/web/uploads/" + pr.getImage());
            pictures.addItem(urli);
        }
        Container Filter = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        AutoCompleteTextField ac = new AutoCompleteTextField(autoP);
        ac.setCompletionRenderer(new ListCellRenderer() {
            private final Label focus = new Label();
            private final Label line1 = new Label();
            private final Label icon = new Label();
            private final Container selection = BorderLayout.center(
                    BoxLayout.encloseY(line1)).add(BorderLayout.EAST, icon);

            @Override
            public Component getListCellRendererComponent(com.codename1.ui.List list, Object value, int index, boolean isSelected) {
                for (int iter = 0; iter < autoP.getSize(); iter++) {
                    if (autoP.getItemAt(iter).equals(value)) {
                        line1.setText(autoP.getItemAt(iter));
                        System.out.println(autoP.getItemAt(iter));
                        icon.setIcon(pictures.getItemAt(iter));
                        break;
                    }
                }
                return selection;
            }

            @Override
            public Component getListFocusComponent(com.codename1.ui.List list) {
                return focus;
            }
        });
        Filter.add(ac);
        System.out.println(ac.getText());

        Container ctnlistProduct;
        System.err.println(p.size());
        super.setupSideMenu(f);
        ctnlistProduct = fillContainer(p);
        f.add(Filter);
        f.add(ctnlistProduct);
        ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!"".equals(ac.getText())) {
                    ArticleList pl = new ArticleList(ac.getText());
                    pl.getF().show();
                } else {
                    ArticleList pl = new ArticleList();
                    pl.getF().show();
                }

            }
        });

        super.setupSideMenu(f);
    }

    public Container fillContainer(ArrayList<Article> p) {
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        if (p.size() > 0) {
            for (Article pr : p) {
                Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label label = new Label();
                System.out.println(pr.getImage());
                int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                Image placeholder = Image.createImage(deviceWidth, deviceWidth); 
                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                label.setIcon(URLImage.createToStorage(encImage,
                        "Large_" + "http://localhost/PIJFinal/web/uploads/" + pr.getImage()
                        + "", "http://localhost/PIJFinal/web/uploads/" + pr.getImage()
                        + "", URLImage.RESIZE_SCALE_TO_FILL));
                c.add(label);
                Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                cnt.getAllStyles().setPaddingLeft(2);
                cnt.add(pr.getTitre());
                c.add(cnt);
                Button show = new Button("Details");
                show.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO_OUTLINE, show.getUnselectedStyle()));
                show.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                         showArticle sa = new showArticle(pr);
                        sa.getF().show();
                    }
                });
                Container cnt1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                cnt1.add(show);
                Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                cc.add(c);
                cc.add(cnt1);
                cc.getAllStyles().setBorder(Border.createGrooveBorder(2));
                ctnlistProduct.add(cc);
            }
        } else {
            Label vide = new Label("No Article Available");
            ctnlistProduct.add(vide);
        }
        return ctnlistProduct;

    }

    public ArticleList(String name) {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Article List", BoxLayout.y());
        ArticleService rs = new ArticleService();
        ArrayList<Article> p = rs.getAllArticles();
        ListModel<String> autoP = new DefaultListModel<>();
        ListModel<URLImage> pictures = new DefaultListModel<>();
        final int size = Display.getInstance().convertToPixels(7);
        final EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(size, size, 0xffcccccc), true);
        for (Article pr : p) {
            autoP.addItem(pr.getTitre());
            URLImage urli = URLImage.createToStorage(placeholder,
                    "http://localhost/PIJFinal/web/uploads/" + pr.getImage(),
                    "http://localhost/PIJFinal/web/uploads/" + pr.getImage());
            pictures.addItem(urli);
        }
        Container Filter = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        AutoCompleteTextField ac = new AutoCompleteTextField(autoP);
        ac.setCompletionRenderer(new ListCellRenderer() {
            private final Label focus = new Label();
            private final Label line1 = new Label();
            private final Label icon = new Label();
            private final Container selection = BorderLayout.center(
                    BoxLayout.encloseY(line1)).add(BorderLayout.EAST, icon);

            @Override
            public Component getListCellRendererComponent(com.codename1.ui.List list, Object value, int index, boolean isSelected) {
                for (int iter = 0; iter < autoP.getSize(); iter++) {
                    if (autoP.getItemAt(iter).equals(value)) {
                        line1.setText(autoP.getItemAt(iter));
                        System.out.println(autoP.getItemAt(iter));
                        icon.setIcon(pictures.getItemAt(iter));
                        break;
                    }

                }
                return selection;
            }

            @Override
            public Component getListFocusComponent(com.codename1.ui.List list) {
                return focus;
            }
        });
        Filter.add(ac);
        System.out.println(ac.getText());

        Container ctnlistProduct;
        System.err.println(p.size());
        super.setupSideMenu(f);
        ctnlistProduct = fillContainer(p);
        f.add(Filter);
        f.add(ctnlistProduct);
        ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!"".equals(ac.getText())) {
                    ArticleList pl = new ArticleList(ac.getText());
                    pl.getF().show();
                } else {
                    ArticleList pl = new ArticleList();
                    pl.getF().show();
                }

            }
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
