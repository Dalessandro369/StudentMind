/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ProjetJava
 */
@Embeddable
public class NotePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "utlisateur_id_utilisateur")
    private int utlisateurIdUtilisateur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "document_id_document")
    private int documentIdDocument;

    public NotePK() {
    }

    public NotePK(int utlisateurIdUtilisateur, int documentIdDocument) {
        this.utlisateurIdUtilisateur = utlisateurIdUtilisateur;
        this.documentIdDocument = documentIdDocument;
    }

    public int getUtlisateurIdUtilisateur() {
        return utlisateurIdUtilisateur;
    }

    public void setUtlisateurIdUtilisateur(int utlisateurIdUtilisateur) {
        this.utlisateurIdUtilisateur = utlisateurIdUtilisateur;
    }

    public int getDocumentIdDocument() {
        return documentIdDocument;
    }

    public void setDocumentIdDocument(int documentIdDocument) {
        this.documentIdDocument = documentIdDocument;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) utlisateurIdUtilisateur;
        hash += (int) documentIdDocument;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotePK)) {
            return false;
        }
        NotePK other = (NotePK) object;
        if (this.utlisateurIdUtilisateur != other.utlisateurIdUtilisateur) {
            return false;
        }
        if (this.documentIdDocument != other.documentIdDocument) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.NotePK[ utlisateurIdUtilisateur=" + utlisateurIdUtilisateur + ", documentIdDocument=" + documentIdDocument + " ]";
    }
    
}
