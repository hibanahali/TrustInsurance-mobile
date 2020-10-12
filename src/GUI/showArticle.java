/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Article;
import Entity.Comment;
import Service.ArticleService;
import Service.CommentService;
import Service.EvenementService;
import Utils.ConnectedUser;
import Utils.SendSMS;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.util.List;

/**
 *
 * @author elbrh
 */
public class showArticle {

    Form f;
    Resources theme;

    public showArticle(Article evenement) {
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
        SpanLabel nom = new SpanLabel("Nom : " + evenement.getTitre());
        SpanLabel desc = new SpanLabel("Description : " + evenement.getDescription());
        SpanLabel nbp = new SpanLabel("Nombre Nombre De Like : " + evenement.getNbr_like());
        c.add(nom);
        c.add(imgvb);
        c.add(desc);
        c.add(nbp);
        f = new Form(evenement.getTitre(), new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, g
                -> {
                    if (ConnectedUser.getUser().getRoles().indexOf("ROLE_CLIENT") > -1) {
                        ArticleList h = new ArticleList();
                        h.getF().show();
                    } else {
                        ArticleListAdmin h = new ArticleListAdmin();
                        h.getF().showBack();
                    }
                });

        c.getAllStyles().setBorder(Border.createLineBorder(3));

        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.add(cc);
        cc.getAllStyles().setBorder(Border.createGrooveBorder(2));
        Button participer = new Button("Like");
        participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArticleService as = new ArticleService();
                as.LikeArticle(evenement);
                showArticle sa = new showArticle(evenement);
                sa.getF().show();
            }
        });
        f.add(c);
        CommentService cs = new CommentService();
        List<Comment> ls = cs.getAllCommentsById(evenement.getId());
        f.add(participer);
        f.add(createLineSeparator());
        for (Comment com : ls) {
            SpanLabel comment = new SpanLabel(com.getUser().getUsername() + " : " + com.getComment());
            f.add(comment);
            if (ConnectedUser.getUser().getId() == com.getUser().getId()) {
                Button btnedit = new Button("edit");
                btnedit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog d = new Dialog("Edit Comment");
                        TextField txt = new TextField();
                        txt.setText(com.getComment() + "");
                        d.add(txt);
                        d.dispose();
                        Button ok = new Button("          Ok           ");
                        Button close = new Button("         Close          ");
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                com.setComment(txt.getText());
                                cs.editComment(com);
                                showArticle sa = new showArticle(evenement);
                                sa.getF().show();
                            }
                        });
                        close.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                d.dispose();
                            }
                        });
                        d.add(ok);
                        d.add(close);
                        d.showDialog();
                    }
                });
                Button btndelete = new Button("delete");
                btndelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        cs.delteComment(com);
                        System.out.println("******************************************************************");
                        showArticle sa = new showArticle(evenement);
                        sa.getF().show();
                    }
                });
                f.add(btnedit);
                f.add(btndelete);
            }
            f.add(createLineSeparator());
        }
        Container form = new Container(BoxLayout.y());
        TextField comtxt = new TextField();
        Validator v = new Validator();
        v.addConstraint(comtxt, new LengthConstraint(5));
        Button btnaddComment = new Button("Ajouter Commentaire");
        v.addSubmitButtons(btnaddComment);
        btnaddComment.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (v.isValid()) {
                    CommentService cs = new CommentService();
                    Comment c = new Comment();
                    c.setArticle(evenement);
                    c.setComment(comtxt.getText());
                    cs.NewComment(c);
                    SendSMS.sendSms();
                    showArticle sa = new showArticle(evenement);
                    sa.getF().show();
                }

            }
        });
        form.add(comtxt);
        form.add(btnaddComment);
        f.add(form);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

}
