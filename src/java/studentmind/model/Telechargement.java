/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ProjetJava
 */
@Entity
@Table(name = "telechargement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telechargement.findAll", query = "SELECT t FROM Telechargement t"),
    @NamedQuery(name = "Telechargement.findByIdTelechargement", query = "SELECT t FROM Telechargement t WHERE t.idTelechargement = :idTelechargement"),
    @NamedQuery(name = "Telechargement.findByDateTelechargement", query = "SELECT t FROM Telechargement t WHERE t.dateTelechargement = :dateTelechargement")})
public class Telechargement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_telechargement")
    private Integer idTelechargement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_telechargement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTelechargement;
    @JoinColumn(name = "utlisateur_id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur utlisateurIdUtilisateur;
    @JoinColumn(name = "document_id_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private Document documentIdDocument;

    public Telechargement() {
    }

    public Telechargement(Integer idTelechargement) {
        this.idTelechargement = idTelechargement;
    }

    public Telechargement(Integer idTelechargement, Date dateTelechargement) {
        this.idTelechargement = idTelechargement;
        this.dateTelechargement = dateTelechargement;
    }

    public Integer getIdTelechargement() {
        return idTelechargement;
    }

    public void setIdTelechargement(Integer idTelechargement) {
        this.idTelechargement = idTelechargement;
    }

    public Date getDateTelechargement() {
        return dateTelechargement;
    }

    public void setDateTelechargement(Date dateTelechargement) {
        this.dateTelechargement = dateTelechargement;
    }

    public Utilisateur getUtlisateurIdUtilisateur() {
        return utlisateurIdUtilisateur;
    }

    public void setUtlisateurIdUtilisateur(Utilisateur utlisateurIdUtilisateur) {
        this.utlisateurIdUtilisateur = utlisateurIdUtilisateur;
    }

    public Document getDocumentIdDocument() {
        return documentIdDocument;
    }

    public void setDocumentIdDocument(Document documentIdDocument) {
        this.documentIdDocument = documentIdDocument;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelechargement != null ? idTelechargement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telechargement)) {
            return false;
        }
        Telechargement other = (Telechargement) object;
        if ((this.idTelechargement == null && other.idTelechargement != null) || (this.idTelechargement != null && !this.idTelechargement.equals(other.idTelechargement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Telechargement[ idTelechargement=" + idTelechargement + " ]";
    }
    
}
