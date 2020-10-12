/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author acer hiba
 */
public class Article {
    
    private int id;
    private String titre;
    private String description;
    private String Image;
    private int nbr_like;

    public Article(int id, String titre, String description, int nbr_like) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nbr_like = nbr_like;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Article(int id, String titre, String description, String Image, int nbr_like) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.Image = Image;
        this.nbr_like = nbr_like;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_like() {
        return nbr_like;
    }

    public void setNbr_like(int nbr_like) {
        this.nbr_like = nbr_like;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", nbr_like=" + nbr_like + '}';
    }
    
    
}
