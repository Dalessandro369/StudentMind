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
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findAllAlpha", query = "SELECT t FROM Type t ORDER BY t.nomType ASC"),
    @NamedQuery(name = "Type.findByIdType", query = "SELECT t FROM Type t WHERE t.idType = :idType"),
    @NamedQuery(name = "Type.findByNomType", query = "SELECT t FROM Type t WHERE t.nomType = :nomType"),
    @NamedQuery(name = "Type.findByDescriptionType", query = "SELECT t FROM Type t WHERE t.descriptionType = :descriptionType")})
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_type")
    private Integer idType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_type")
    private String nomType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description_type")
    private String descriptionType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidtype")
    private Collection<Document> documentCollection;

    public Type() {
    }

    public Type(Integer idType) {
        this.idType = idType;
    }

    public Type(Integer idType, String nomType, String descriptionType) {
        this.idType = idType;
        this.nomType = nomType;
        this.descriptionType = descriptionType;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
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
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Type[ idType=" + idType + " ]";
    }
    
}
