/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ProjetJava
 */
@Entity
@Table(name = "commentaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c"),
    @NamedQuery(name = "Commentaire.findByIdCommentaire", query = "SELECT c FROM Commentaire c WHERE c.idCommentaire = :idCommentaire")})
public class Commentaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_commentaire")
    private Integer idCommentaire;
    @JoinColumn(name = "FK_id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur fKidutilisateur;
    @JoinColumn(name = "FK_id_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private Document fKiddocument;
    @JoinColumn(name = "FK_id_utilisateur_signaleur", referencedColumnName = "id_utilisateur")
    @ManyToOne
    private Utilisateur fKidutilisateursignaleur;
    @JoinColumn(name = "FK_id_etat_commentaire", referencedColumnName = "id_etat_commentaire")
    @ManyToOne(optional = false)
    private EtatCommentaire fKidetatcommentaire;

    public Commentaire() {
    }

    public Commentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Integer getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Utilisateur getFKidutilisateur() {
        return fKidutilisateur;
    }

    public void setFKidutilisateur(Utilisateur fKidutilisateur) {
        this.fKidutilisateur = fKidutilisateur;
    }

    public Document getFKiddocument() {
        return fKiddocument;
    }

    public void setFKiddocument(Document fKiddocument) {
        this.fKiddocument = fKiddocument;
    }

    public Utilisateur getFKidutilisateursignaleur() {
        return fKidutilisateursignaleur;
    }

    public void setFKidutilisateursignaleur(Utilisateur fKidutilisateursignaleur) {
        this.fKidutilisateursignaleur = fKidutilisateursignaleur;
    }

    public EtatCommentaire getFKidetatcommentaire() {
        return fKidetatcommentaire;
    }

    public void setFKidetatcommentaire(EtatCommentaire fKidetatcommentaire) {
        this.fKidetatcommentaire = fKidetatcommentaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommentaire != null ? idCommentaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.idCommentaire == null && other.idCommentaire != null) || (this.idCommentaire != null && !this.idCommentaire.equals(other.idCommentaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Commentaire[ idCommentaire=" + idCommentaire + " ]";
    }
    
}
