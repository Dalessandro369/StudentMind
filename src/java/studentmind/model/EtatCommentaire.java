/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ProjetJava
 */
@Entity
@Table(name = "etat_commentaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatCommentaire.findAll", query = "SELECT e FROM EtatCommentaire e"),
    @NamedQuery(name = "EtatCommentaire.findByIdEtatCommentaire", query = "SELECT e FROM EtatCommentaire e WHERE e.idEtatCommentaire = :idEtatCommentaire"),
    @NamedQuery(name = "EtatCommentaire.findByNomEtatCommentaire", query = "SELECT e FROM EtatCommentaire e WHERE e.nomEtatCommentaire = :nomEtatCommentaire")})
public class EtatCommentaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_etat_commentaire")
    private Integer idEtatCommentaire;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nom_etat_commentaire")
    private String nomEtatCommentaire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidetatcommentaire")
    private Collection<Commentaire> commentaireCollection;

    public EtatCommentaire() {
    }

    public EtatCommentaire(Integer idEtatCommentaire) {
        this.idEtatCommentaire = idEtatCommentaire;
    }

    public EtatCommentaire(Integer idEtatCommentaire, String nomEtatCommentaire) {
        this.idEtatCommentaire = idEtatCommentaire;
        this.nomEtatCommentaire = nomEtatCommentaire;
    }

    public Integer getIdEtatCommentaire() {
        return idEtatCommentaire;
    }

    public void setIdEtatCommentaire(Integer idEtatCommentaire) {
        this.idEtatCommentaire = idEtatCommentaire;
    }

    public String getNomEtatCommentaire() {
        return nomEtatCommentaire;
    }

    public void setNomEtatCommentaire(String nomEtatCommentaire) {
        this.nomEtatCommentaire = nomEtatCommentaire;
    }

    @XmlTransient
    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtatCommentaire != null ? idEtatCommentaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatCommentaire)) {
            return false;
        }
        EtatCommentaire other = (EtatCommentaire) object;
        if ((this.idEtatCommentaire == null && other.idEtatCommentaire != null) || (this.idEtatCommentaire != null && !this.idEtatCommentaire.equals(other.idEtatCommentaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.EtatCommentaire[ idEtatCommentaire=" + idEtatCommentaire + " ]";
    }
    
}
