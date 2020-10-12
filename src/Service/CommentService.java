/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Entity.Comment;
import Utils.ConnectedUser;
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
public class CommentService {
    
    public ArrayList<Comment> getAllCommentsById(int id) {
        ArrayList<Comment> listConseils = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/getCommentsByArticle/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Comment ev = new Comment();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                        Map<String, Object> user =(Map<String, Object>) obj.get("utilisateur");
                        Map<String, Object> article =(Map<String, Object>) obj.get("article");
                        ev.getUser().setUsername(user.get("username").toString());
                        float idUser = Float.parseFloat(user.get("id").toString());
                        ev.getUser().setId((int)idUser);
                        ev.setComment(obj.get("commentaire").toString());
                        listConseils.add(ev);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listConseils;
    }
        public void NewComment(Comment e) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/addcomment?user=" + ConnectedUser.getUser().getId()
                + "&article=" + e.getArticle().getId() + "&comment=" + e.getComment());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Comment Ajouter", "Comment Ajouter Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
        public void editComment(Comment e) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/editComment/"+e.getId()+"?comment=" + e.getComment());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Comment Modifier", "Comment Modifier Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        
        public void delteComment(Comment e) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/deleteComment/"+e.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Comment Supprimer", "Comment supprimer Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
