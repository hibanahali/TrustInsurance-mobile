/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Camilia
 */
public class User {
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private String photo_membre;
    private String genre;
    private int phone_number;
    private String country;
    private int age;
    protected String Stripe_id;

    public String getStripe_id() {
        return Stripe_id;
    }

    public void setStripe_id(String Stripe_id) {
        this.Stripe_id = Stripe_id;
    }

    public User(int id, String username, String username_canonical, String email, String email_canonical, boolean enabled, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String photo_membre, String genre, int phone_number, String country, int age, String Stripe_id) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.photo_membre = photo_membre;
        this.genre = genre;
        this.phone_number = phone_number;
        this.country = country;
        this.age = age;
        this.Stripe_id = Stripe_id;
    }

    public User(String pays, int ageS, int numero, String emaile,String photo_member) {
            this.country=pays;
            this.age=ageS;
            this.phone_number=numero;
            this.email=emaile;
            this.photo_membre=photo_member;
            
    }
    public User(String nom, String email,String stripe_id) {
            this.username=nom;
            this.email=email;
            this.Stripe_id=stripe_id;
            
    }

    public User(String username, String roles) {
        this.username = username;
        this.roles = roles;
    }

  

  

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", lastLogin=" + lastLogin + ", confirmationToken=" + confirmationToken + ", passwordRequestedAt=" + passwordRequestedAt + ", roles=" + roles + ", photo_membre=" + photo_membre + ", genre=" + genre + ", phone_number=" + phone_number + ", country=" + country + ", age=" + age + '}';
    }
    
    
    public User() {
    }
//     public User(Integer id) {
//        this.id = id;
//    }
      public User(int id, String username, String username_canonical, String email, String email_canonical, boolean enabled, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String photo_membre, String genre,int phone_number,String country,int age) {
        this.id= id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.photo_membre = photo_membre;
        this.genre = genre;
        this.phone_number=phone_number;
        this.country=country;
        this.age=age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public User(String username, String username_canonical, String email, String email_canonical, boolean enabled, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String photo_membre, String genre,int phone_number,String country,int age) {
    
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.photo_membre = photo_membre;
        this.genre = genre;
        this.phone_number=phone_number;
        this.country=country;
        this.age=age;
    }

    public User(String name) {
        this.username= name;                           
    }
    public User(Boolean enabled){
        this.enabled=enabled;
    };

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPhoto_membre() {
        return photo_membre;
    }

    public void setPhoto_membre(String photo_membre) {
        this.photo_membre = photo_membre;
    }

    


    
    
    
}
