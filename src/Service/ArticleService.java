/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Utils.UploadPhoto;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elbrh
 */
public class ArticleService {

    public void NewArticle(Article e) {
        String image = UploadPhoto.pictureUpload(e.getImage());
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/addArticle?image=" + image
                + "&text=" + e.getDescription() + "&titre=" + e.getTitre());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Article Ajouter", "Article Ajouter Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void EditArticle(Article e) {
        String image = e.getImage();
        if (image.startsWith("file")) {
            image = UploadPhoto.pictureUpload(e.getImage());
        }
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/editArticle/" + e.getId() + "?image=" + image
                + "&text=" + e.getDescription() + "&titre=" + e.getTitre());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Article Modifier", "Article Modifier Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void DeleteArticle(Article p) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/deleteArticle/" + p.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    Dialog.show("Article  supprimé", "Article supprimé", "OK", "Annuler");
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public ArrayList<Article> getAllArticles() {
        ArrayList<Article> listConseils = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/getAllArticles");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Article ev = new Article();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                        ev.setTitre(obj.get("titre").toString());
                        ev.setDescription(obj.get("text").toString());
                        ev.setImage(obj.get("image").toString());
                        ev.setNbr_like((int) Float.parseFloat(obj.get("likes").toString()));
                        listConseils.add(ev);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listConseils;
    }

    public ArrayList<Article> getAllArticlesByName(String name) {
        ArrayList<Article> listConseils = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/getAllArticlesByName?name=" + name);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Article ev = new Article();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                        ev.setTitre(obj.get("titre").toString());
                        ev.setDescription(obj.get("text").toString());
                        ev.setImage(obj.get("image").toString());
                        ev.setNbr_like((int) Float.parseFloat(obj.get("likes").toString()));
                        listConseils.add(ev);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listConseils;
    }

    public void LikeArticle(Article e) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/LikeArticle/" + e.getId());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    Dialog.show("Like", "on a compte votre Like Avec Succes", "OK", "Annuler");
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
