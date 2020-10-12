/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;


/**
 *
 * @author marwen
 */
public class Event {
    private int id;
    private Date date_debut;
    private Date date_fin;
    private String description;
    private int nb_participant ;
    private int capacite;
    private String lieu;
    private String nom;
    private String Image;
    private int user_id;
    private int prix;   

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Event(int id, Date date_debut, Date date_fin, String description, int nb_participant, int capacite, String lieu, String nom, String Image, int user_id, int prix) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.nb_participant = nb_participant;
        this.capacite = capacite;
        this.lieu = lieu;
        this.nom = nom;
        this.Image = Image;
        this.user_id = user_id;
        this.prix = prix;
    }
   
    public int getId() {
        return id;
    }

    public Event(int id, Date date_debut, Date date_fin, String description, int nb_participant, int capacite, String lieu, String nom, String Image, int user_id) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.nb_participant = nb_participant;
        this.capacite = capacite;
        this.lieu = lieu;
        this.nom = nom;
        this.Image = Image;
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public Event() {
    }
    

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}

