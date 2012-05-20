/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import studentmind.controller.InscriptionServlet;

/**
 *
 * @author ProjetJava
 */
public class HashMD5 {
     
    public static String encode(String el){
        
        /* Envoi de l'email de confirmation email pour cloturer l'inscription 
            * On converti la chaîne de caractère email en tableau de bytes pour l'utiliser par la suite et on initialise un autre tableau qui contiendra le hash MD5  
            */

        byte[] uniqueKey = el.getBytes();
        byte[] hash      = null;

        /* Calcul du hash */
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* On reforme une chaîne de caractères représentant le hash */
        StringBuilder md5email = new StringBuilder();
        for (int i = 0; i < hash.length; i++)
        {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1){
                md5email.append('0');
                md5email.append(hex.charAt(hex.length() - 1));
            }
            else md5email.append(hex.substring(hex.length() - 2));
        }

        return md5email.toString();
        
    }
    
}
