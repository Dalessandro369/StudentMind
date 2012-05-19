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
@Table(name = "categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findAllAlpha", query = "SELECT c FROM Type c ORDER BY c.nomCategorie ASC"),
    @NamedQuery(name = "Categorie.findByIdCategorie", query = "SELECT c FROM Categorie c WHERE c.idCategorie = :idCategorie"),
    @NamedQuery(name = "Categorie.findByNomCategorie", query = "SELECT c FROM Categorie c WHERE c.nomCategorie = :nomCategorie"),
    @NamedQuery(name = "Categorie.findByDescriptionCategorie", query = "SELECT c FROM Categorie c WHERE c.descriptionCategorie = :descriptionCategorie")})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_categorie")
    private Integer idCategorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_categorie")
    private String nomCategorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description_categorie")
    private String descriptionCategorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidcategorie")
    private Collection<Document> documentCollection;

    public Categorie() {
    }

    public Categorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie(Integer idCategorie, String nomCategorie, String descriptionCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.descriptionCategorie = descriptionCategorie;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescriptionCategorie() {
        return descriptionCategorie;
    }

    public void setDescriptionCategorie(String descriptionCategorie) {
        this.descriptionCategorie = descriptionCategorie;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorie != null ? idCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idCategorie == null && other.idCategorie != null) || (this.idCategorie != null && !this.idCategorie.equals(other.idCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Categorie[ idCategorie=" + idCategorie + " ]";
    }
    
}
