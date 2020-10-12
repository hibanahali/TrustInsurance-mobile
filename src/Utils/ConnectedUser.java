/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entity.User;

/**
 *
 * @author elbrh
 */
public class ConnectedUser {
    static User u ;
    
    public static void setUser(User u){
        ConnectedUser.u = u ;
    }
    
    public static User getUser(){
        return u ;
    }
    
}
