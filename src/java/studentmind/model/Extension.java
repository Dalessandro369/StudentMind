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
@Table(name = "extension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extension.findAll", query = "SELECT e FROM Extension e"),
    @NamedQuery(name = "Extension.findByIdExtension", query = "SELECT e FROM Extension e WHERE e.idExtension = :idExtension"),
    @NamedQuery(name = "Extension.findByNomExtension", query = "SELECT e FROM Extension e WHERE e.nomExtension = :nomExtension"),
    @NamedQuery(name = "Extension.findByDescriptionExtension", query = "SELECT e FROM Extension e WHERE e.descriptionExtension = :descriptionExtension")})
public class Extension implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_extension")
    private Integer idExtension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_extension")
    private String nomExtension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description_extension")
    private String descriptionExtension;
    @JoinColumn(name = "FK_id_famille", referencedColumnName = "id_famille")

    @ManyToOne(optional = false)
    private Famille fKidfamille;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidextension")
    private Collection<Document> documentCollection;

    public Extension() {
    }

    public Extension(Integer idExtension) {
        this.idExtension = idExtension;
    }

    public Extension(Integer idExtension, String nomExtension, String descriptionExtension) {
        this.idExtension = idExtension;
        this.nomExtension = nomExtension;
        this.descriptionExtension = descriptionExtension;
    }

    public Integer getIdExtension() {
        return idExtension;
    }

    public void setIdExtension(Integer idExtension) {
        this.idExtension = idExtension;
    }

    public String getNomExtension() {
        return nomExtension;
    }

    public void setNomExtension(String nomExtension) {
        this.nomExtension = nomExtension;
    }

    public String getDescriptionExtension() {
        return descriptionExtension;
    }

    public void setDescriptionExtension(String descriptionExtension) {
        this.descriptionExtension = descriptionExtension;
    }

    public Famille getFKidfamille() {
        return fKidfamille;
    }

    public void setFKidfamille(Famille fKidfamille) {
        this.fKidfamille = fKidfamille;
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
        hash += (idExtension != null ? idExtension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Extension)) {
            return false;
        }
        Extension other = (Extension) object;
        if ((this.idExtension == null && other.idExtension != null) || (this.idExtension != null && !this.idExtension.equals(other.idExtension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Extension[ idExtension=" + idExtension + " ]";
    }
    
}
