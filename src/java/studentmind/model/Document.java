/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findDocumentUne", query = "SELECT d FROM Document d JOIN d.fKidetatdocument etat WHERE etat.idEtatDocument = 2 ORDER BY d.idDocument DESC"),     
    @NamedQuery(name = "Document.findByIdDocument", query = "SELECT d FROM Document d WHERE d.idDocument = :idDocument"),
    @NamedQuery(name = "Document.findByTitreDocument", query = "SELECT d FROM Document d WHERE d.titreDocument = :titreDocument"),
    @NamedQuery(name = "Document.findByDescriptionDocument", query = "SELECT d FROM Document d WHERE d.descriptionDocument = :descriptionDocument"),
    @NamedQuery(name = "Document.findByNomFichier", query = "SELECT d FROM Document d WHERE d.nomFichier = :nomFichier"),
    @NamedQuery(name = "Document.findByTaille", query = "SELECT d FROM Document d WHERE d.taille = :taille"),
    @NamedQuery(name = "Document.findByDate", query = "SELECT d FROM Document d WHERE d.date = :date")})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_document")
    private Integer idDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "titre_document")
    private String titreDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description_document")
    private String descriptionDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nomFichier")
    private String nomFichier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taille")
    private float taille;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "FK_id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private Type fKidtype;
    @JoinColumn(name = "FK_id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur fKidutilisateur;
    @JoinColumn(name = "FK_id_extension", referencedColumnName = "id_extension")
    @ManyToOne(optional = false)
    private Extension fKidextension;
    @JoinColumn(name = "FK_id_etat_document", referencedColumnName = "id_etat_document")
    @ManyToOne(optional = false)
    private EtatDocument fKidetatdocument;
    @JoinColumn(name = "FK_id_categorie", referencedColumnName = "id_categorie")
    @ManyToOne(optional = false)
    private Categorie fKidcategorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentIdDocument")
    private Collection<Telechargement> telechargementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKiddocument")
    private Collection<Commentaire> commentaireCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Collection<Note> noteCollection;

    public Document() {
    }

    public Document(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public Document(Integer idDocument, String titreDocument, String descriptionDocument, String nomFichier, float taille, Date date) {
        this.idDocument = idDocument;
        this.titreDocument = titreDocument;
        this.descriptionDocument = descriptionDocument;
        this.nomFichier = nomFichier;
        this.taille = taille;
        this.date = date;
    }

    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public String getTitreDocument() {
        return titreDocument;
    }

    public void setTitreDocument(String titreDocument) {
        this.titreDocument = titreDocument;
    }

    public String getDescriptionDocument() {
        return descriptionDocument;
    }

    public void setDescriptionDocument(String descriptionDocument) {
        this.descriptionDocument = descriptionDocument;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getFKidtype() {
        return fKidtype;
    }

    public void setFKidtype(Type fKidtype) {
        this.fKidtype = fKidtype;
    }

    public Utilisateur getFKidutilisateur() {
        return fKidutilisateur;
    }

    public void setFKidutilisateur(Utilisateur fKidutilisateur) {
        this.fKidutilisateur = fKidutilisateur;
    }

    public Extension getFKidextension() {
        return fKidextension;
    }

    public void setFKidextension(Extension fKidextension) {
        this.fKidextension = fKidextension;
    }

    public EtatDocument getFKidetatdocument() {
        return fKidetatdocument;
    }

    public void setFKidetatdocument(EtatDocument fKidetatdocument) {
        this.fKidetatdocument = fKidetatdocument;
    }

    public Categorie getFKidcategorie() {
        return fKidcategorie;
    }

    public void setFKidcategorie(Categorie fKidcategorie) {
        this.fKidcategorie = fKidcategorie;
    }

    @XmlTransient
    public Collection<Telechargement> getTelechargementCollection() {
        return telechargementCollection;
    }

    public void setTelechargementCollection(Collection<Telechargement> telechargementCollection) {
        this.telechargementCollection = telechargementCollection;
    }

    @XmlTransient
    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocument != null ? idDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.idDocument == null && other.idDocument != null) || (this.idDocument != null && !this.idDocument.equals(other.idDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Document[ idDocument=" + idDocument + " ]";
    }
    
}
