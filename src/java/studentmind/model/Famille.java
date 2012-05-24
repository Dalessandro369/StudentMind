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
@Table(name = "famille")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Famille.findAll", query = "SELECT f FROM Famille f"),
    @NamedQuery(name = "Famille.findAllAlpha", query = "SELECT f FROM Famille f ORDER BY f.nomFamille ASC"),    
    @NamedQuery(name = "Famille.findByIdFamille", query = "SELECT f FROM Famille f WHERE f.idFamille = :idFamille"),
    @NamedQuery(name = "Famille.findByNomFamille", query = "SELECT f FROM Famille f WHERE f.nomFamille = :nomFamille"),
    @NamedQuery(name = "Famille.findByDescriptionFamille", query = "SELECT f FROM Famille f WHERE f.descriptionFamille = :descriptionFamille")})
public class Famille implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_famille")
    private Integer idFamille;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_famille")
    private String nomFamille;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description_famille")
    private String descriptionFamille;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidfamille")
    private Collection<Extension> extensionCollection;

    public Famille() {
    }

    public Famille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public Famille(Integer idFamille, String nomFamille, String descriptionFamille) {
        this.idFamille = idFamille;
        this.nomFamille = nomFamille;
        this.descriptionFamille = descriptionFamille;
    }

    public Integer getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public String getDescriptionFamille() {
        return descriptionFamille;
    }

    public void setDescriptionFamille(String descriptionFamille) {
        this.descriptionFamille = descriptionFamille;
    }

    @XmlTransient
    public Collection<Extension> getExtensionCollection() {
        return extensionCollection;
    }

    public void setExtensionCollection(Collection<Extension> extensionCollection) {
        this.extensionCollection = extensionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamille != null ? idFamille.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Famille)) {
            return false;
        }
        Famille other = (Famille) object;
        if ((this.idFamille == null && other.idFamille != null) || (this.idFamille != null && !this.idFamille.equals(other.idFamille))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Famille[ idFamille=" + idFamille + " ]";
    }
    
}
