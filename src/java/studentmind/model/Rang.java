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
@Table(name = "rang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rang.findAll", query = "SELECT r FROM Rang r"),
    @NamedQuery(name = "Rang.findByIdRang", query = "SELECT r FROM Rang r WHERE r.idRang = :idRang"),
    @NamedQuery(name = "Rang.findByNomRang", query = "SELECT r FROM Rang r WHERE r.nomRang = :nomRang")})
public class Rang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rang")
    private Integer idRang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nom_rang")
    private String nomRang;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidrang")
    private Collection<Utilisateur> utilisateurCollection;

    public Rang() {
    }

    public Rang(Integer idRang) {
        this.idRang = idRang;
    }

    public Rang(Integer idRang, String nomRang) {
        this.idRang = idRang;
        this.nomRang = nomRang;
    }

    public Integer getIdRang() {
        return idRang;
    }

    public void setIdRang(Integer idRang) {
        this.idRang = idRang;
    }

    public String getNomRang() {
        return nomRang;
    }

    public void setNomRang(String nomRang) {
        this.nomRang = nomRang;
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
        hash += (idRang != null ? idRang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rang)) {
            return false;
        }
        Rang other = (Rang) object;
        if ((this.idRang == null && other.idRang != null) || (this.idRang != null && !this.idRang.equals(other.idRang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Rang[ idRang=" + idRang + " ]";
    }
    
}
