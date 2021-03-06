/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ProjetJava
 */
public abstract class ServicesLocator {

    private ServicesLocator() {
    }

    public static PaysFacade getPaysFacade() {
        try {
            Context context = new InitialContext();
            return (PaysFacade) context.lookup("java:global/StudentMind/PaysFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static MessageFacade getMessageFacade() {
        try {
            Context context = new InitialContext();
            return (MessageFacade) context.lookup("java:global/StudentMind/MessageFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static EtatMessageFacade getEtatMessageFacade() {
        try {
            Context context = new InitialContext();
            return (EtatMessageFacade) context.lookup("java:global/StudentMind/EtatMessageFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }   

    public static NoteFacade getNoteFacade() {
        try {
            Context context = new InitialContext();
            return (NoteFacade) context.lookup("java:global/StudentMind/NoteFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static TelechargementFacade getTelechargementFacade() {
        try {
            Context context = new InitialContext();
            return (TelechargementFacade) context.lookup("java:global/StudentMind/TelechargementFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ExtensionFacade getExtensionFacade() {
        try {
            Context context = new InitialContext();
            return (ExtensionFacade) context.lookup("java:global/StudentMind/ExtensionFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static CategorieFacade getCategorieFacade() {
        try {
            Context context = new InitialContext();
            return (CategorieFacade) context.lookup("java:global/StudentMind/CategorieFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static CommentaireFacade getCommentaireFacade() {
        try {
            Context context = new InitialContext();
            return (CommentaireFacade) context.lookup("java:global/StudentMind/CommentaireFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static DocumentFacade getDocumentFacade() {
        try {
            Context context = new InitialContext();
            return (DocumentFacade) context.lookup("java:global/StudentMind/DocumentFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static TypeFacade getTypeFacade() {
        try {
            Context context = new InitialContext();
            return (TypeFacade) context.lookup("java:global/StudentMind/TypeFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static UtilisateurFacade getUtilisateurFacade() {
        try {
            Context context = new InitialContext();
            return (UtilisateurFacade) context.lookup("java:global/StudentMind/UtilisateurFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ImageFacade getImageFacade() {
        try {
            Context context = new InitialContext();
            return (ImageFacade) context.lookup("java:global/StudentMind/ImageFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static EtatUtilisateurFacade getEtatUtiFacade() {
        try {
            Context context = new InitialContext();
            return (EtatUtilisateurFacade) context.lookup("java:global/StudentMind/EtatUtilisateurFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static RangFacade getRangFacade() {
        try {
            Context context = new InitialContext();
            return (RangFacade) context.lookup("java:global/StudentMind/RangFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static FamilleFacade getFamilleFacade() {
        try {
            Context context = new InitialContext();
            return (FamilleFacade) context.lookup("java:global/StudentMind/FamilleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ServicesLocator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}