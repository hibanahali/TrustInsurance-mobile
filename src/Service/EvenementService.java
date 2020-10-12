/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Event;
import Utils.UploadPhoto;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elbrh
 */
public class EvenementService {

    public void NewEvent(Event e) {
        String image = UploadPhoto.pictureUpload(e.getImage());
        System.out.println("image Name : " + image);
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/addEvent?image=" + image
                + "&startDate=" + e.getDate_debut() + "&desc=" + e.getDescription() + "&titre=" + e.getNom() + "&dueDate=" + e.getDate_fin());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Event Ajouter", "Event Ajouter Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void EditEvent(Event e) {
        String image = e.getImage();
        if (image.startsWith("file")) {
            image = UploadPhoto.pictureUpload(e.getImage());
        }
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/editEvent/" + e.getId() + "?image=" + image
                + "&startDate=" + e.getDate_debut() + "&desc=" + e.getDescription() + "&titre=" + e.getNom() + "&dueDate=" + e.getDate_fin());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (service.get("id") != null) {
                        Dialog.show("Event Modifier", "Event Modifier Avec Succes", "OK", "Annuler");
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void ParticipeEvent(Event e) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/ParticipeEvent/" + e.getId());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    Dialog.show("Participé", "on a compte votre Participation Avec Succes", "OK", "Annuler");
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void DeleteEvent(Event p) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/deleteEvent/" + p.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> service = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    Dialog.show("Event supprimé", "Event supprimé Avec Succes", "OK", "Annuler");

                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> listConseils = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/getAllEvents");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Event ev = new Event();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                        ev.setImage(obj.get("image").toString());
                        ev.setNom(obj.get("titre").toString());
                        ev.setDescription(obj.get("description").toString());
                        ev.setNb_participant((int) Float.parseFloat(obj.get("participants").toString()));
                        //Start Date
                        Map<String, Object> d = (Map<String, Object>) obj.get("StartDate");
                        double td = (double) d.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd/MM/yyyy").format(new Date(xd));
                        try {
                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatd);
                            ev.setDate_debut(date);
                        } catch (ParseException ex) {

                        }
                        //dateFin
                        Map<String, Object> df = (Map<String, Object>) obj.get("DueDate");
                        double tdf = (double) df.get("timestamp");
                        long xdf = (long) (tdf * 1000L);
                        String formatdf = new SimpleDateFormat("dd/MM/yyyy").format(new Date(xdf));
                        try {
                            Date datef = new SimpleDateFormat("dd/MM/yyy").parse(formatdf);
                            ev.setDate_fin(datef);
                        } catch (ParseException ex) {

                        }
                        listConseils.add(ev);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listConseils;
    }

    public ArrayList<Event> getAllEventsByName(String name) {
        ArrayList<Event> listConseils = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIJFinal/web/app_dev.php/mobile/getAllEventsByName?name=" + name);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Event ev = new Event();
                        float id = Float.parseFloat(obj.get("p_id").toString());
                        ev.setId((int) id);
                        ev.setImage(obj.get("p_image").toString());
                        ev.setNom(obj.get("p_titre").toString());
                        ev.setDescription(obj.get("p_description").toString());
                        ev.setNb_participant((int) Float.parseFloat(obj.get("p_participants").toString()));
                        //Start Date
                        Map<String, Object> d = (Map<String, Object>) obj.get("p_StartDate");
                        double td = (double) d.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd/MM/yyyy").format(new Date(xd));
                        try {
                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatd);
                            ev.setDate_debut(date);
                        } catch (ParseException ex) {

                        }
                        //dateFin
                        Map<String, Object> df = (Map<String, Object>) obj.get("p_DueDate");
                        double tdf = (double) df.get("timestamp");
                        long xdf = (long) (tdf * 1000L);
                        String formatdf = new SimpleDateFormat("dd/MM/yyyy").format(new Date(xdf));
                        try {
                            Date datef = new SimpleDateFormat("dd/MM/yyy").parse(formatdf);
                            ev.setDate_fin(datef);
                        } catch (ParseException ex) {

                        }
                        listConseils.add(ev);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listConseils;
    }
}
