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
@Table(name = "etat_document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatDocument.findAll", query = "SELECT e FROM EtatDocument e"),
    @NamedQuery(name = "EtatDocument.findByIdEtatDocument", query = "SELECT e FROM EtatDocument e WHERE e.idEtatDocument = :idEtatDocument"),
    @NamedQuery(name = "EtatDocument.findByNomEtatDocument", query = "SELECT e FROM EtatDocument e WHERE e.nomEtatDocument = :nomEtatDocument")})
public class EtatDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_etat_document")
    private Integer idEtatDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nom_etat_document")
    private String nomEtatDocument;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidetatdocument")
    private Collection<Document> documentCollection;

    public EtatDocument() {
    }

    public EtatDocument(Integer idEtatDocument) {
        this.idEtatDocument = idEtatDocument;
    }

    public EtatDocument(Integer idEtatDocument, String nomEtatDocument) {
        this.idEtatDocument = idEtatDocument;
        this.nomEtatDocument = nomEtatDocument;
    }

    public Integer getIdEtatDocument() {
        return idEtatDocument;
    }

    public void setIdEtatDocument(Integer idEtatDocument) {
        this.idEtatDocument = idEtatDocument;
    }

    public String getNomEtatDocument() {
        return nomEtatDocument;
    }

    public void setNomEtatDocument(String nomEtatDocument) {
        this.nomEtatDocument = nomEtatDocument;
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
        hash += (idEtatDocument != null ? idEtatDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatDocument)) {
            return false;
        }
        EtatDocument other = (EtatDocument) object;
        if ((this.idEtatDocument == null && other.idEtatDocument != null) || (this.idEtatDocument != null && !this.idEtatDocument.equals(other.idEtatDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.EtatDocument[ idEtatDocument=" + idEtatDocument + " ]";
    }
    
}
