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
@Table(name = "etat_utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatUtilisateur.findAll", query = "SELECT e FROM EtatUtilisateur e"),
    @NamedQuery(name = "EtatUtilisateur.findByIdEtatUtilisateur", query = "SELECT e FROM EtatUtilisateur e WHERE e.idEtatUtilisateur = :idEtatUtilisateur"),
    @NamedQuery(name = "EtatUtilisateur.findByNomEtatUtilisateur", query = "SELECT e FROM EtatUtilisateur e WHERE e.nomEtatUtilisateur = :nomEtatUtilisateur")})
public class EtatUtilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_etat_utilisateur")
    private Integer idEtatUtilisateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nom_etat_utilisateur")
    private String nomEtatUtilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidetatutlisateur")
    private Collection<Utilisateur> utilisateurCollection;

    public EtatUtilisateur() {
    }

    public EtatUtilisateur(Integer idEtatUtilisateur) {
        this.idEtatUtilisateur = idEtatUtilisateur;
    }

    public EtatUtilisateur(Integer idEtatUtilisateur, String nomEtatUtilisateur) {
        this.idEtatUtilisateur = idEtatUtilisateur;
        this.nomEtatUtilisateur = nomEtatUtilisateur;
    }

    public Integer getIdEtatUtilisateur() {
        return idEtatUtilisateur;
    }

    public void setIdEtatUtilisateur(Integer idEtatUtilisateur) {
        this.idEtatUtilisateur = idEtatUtilisateur;
    }

    public String getNomEtatUtilisateur() {
        return nomEtatUtilisateur;
    }

    public void setNomEtatUtilisateur(String nomEtatUtilisateur) {
        this.nomEtatUtilisateur = nomEtatUtilisateur;
    }

    @XmlTransient
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtatUtilisateur != null ? idEtatUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatUtilisateur)) {
            return false;
        }
        EtatUtilisateur other = (EtatUtilisateur) object;
        if ((this.idEtatUtilisateur == null && other.idEtatUtilisateur != null) || (this.idEtatUtilisateur != null && !this.idEtatUtilisateur.equals(other.idEtatUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.EtatUtilisateur[ idEtatUtilisateur=" + idEtatUtilisateur + " ]";
    }
    
}
