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
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateur.findByVille", query = "SELECT u FROM Utilisateur u WHERE u.ville = :ville"),
    @NamedQuery(name = "Utilisateur.findByEmail", query = "SELECT u FROM Utilisateur u WHERE u.email = :email"),
    @NamedQuery(name = "Utilisateur.findByPassword", query = "SELECT u FROM Utilisateur u WHERE u.password = :password"),
    @NamedQuery(name = "Utilisateur.findByDateNaissance", query = "SELECT u FROM Utilisateur u WHERE u.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Utilisateur.findBySexe", query = "SELECT u FROM Utilisateur u WHERE u.sexe = :sexe"),
    @NamedQuery(name = "Utilisateur.findByNbrSignal", query = "SELECT u FROM Utilisateur u WHERE u.nbrSignal = :nbrSignal"),
    @NamedQuery(name = "Utilisateur.findByPoints", query = "SELECT u FROM Utilisateur u WHERE u.points = :points"),
    @NamedQuery(name = "Utilisateur.findBySiteWeb", query = "SELECT u FROM Utilisateur u WHERE u.siteWeb = :siteWeb"),
    @NamedQuery(name = "Utilisateur.findByEcole", query = "SELECT u FROM Utilisateur u WHERE u.ecole = :ecole")})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ville")
    private String ville;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "date_naissance")
    private String dateNaissance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexe")
    private String sexe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbr_signal")
    private int nbrSignal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "points")
    private int points;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "site_web")
    private String siteWeb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ecole")
    private String ecole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidutilisateurdes")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidutilisateurexp")
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidutilisateur")
    private Collection<Document> documentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utlisateurIdUtilisateur")
    private Collection<Telechargement> telechargementCollection;
    @JoinColumn(name = "FK_id_rang", referencedColumnName = "id_rang")
    @ManyToOne(optional = false)
    private Rang fKidrang;
    @JoinColumn(name = "FK_id_pays", referencedColumnName = "id_pays")
    @ManyToOne(optional = false)
    private Pays fKidpays;
    @JoinColumn(name = "FK_id_Image", referencedColumnName = "id_image")
    @ManyToOne(optional = false)
    private Image fKidImage;
    @JoinColumn(name = "FK_id_etat_utlisateur", referencedColumnName = "id_etat_utilisateur")
    @ManyToOne(optional = false)
    private EtatUtilisateur fKidetatutlisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidutilisateur")
    private Collection<Commentaire> commentaireCollection;
    @OneToMany(mappedBy = "fKidutilisateursignaleur")
    private Collection<Commentaire> commentaireCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
    private Collection<Note> noteCollection;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(Integer idUtilisateur, String nom, String prenom, String ville, String email, String password, String dateNaissance, String sexe, int nbrSignal, int points, String siteWeb, String ecole) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.nbrSignal = nbrSignal;
        this.points = points;
        this.siteWeb = siteWeb;
        this.ecole = ecole;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getNbrSignal() {
        return nbrSignal;
    }

    public void setNbrSignal(int nbrSignal) {
        this.nbrSignal = nbrSignal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @XmlTransient
    public Collection<Telechargement> getTelechargementCollection() {
        return telechargementCollection;
    }

    public void setTelechargementCollection(Collection<Telechargement> telechargementCollection) {
        this.telechargementCollection = telechargementCollection;
    }

    public Rang getFKidrang() {
        return fKidrang;
    }

    public void setFKidrang(Rang fKidrang) {
        this.fKidrang = fKidrang;
    }

    public Pays getFKidpays() {
        return fKidpays;
    }

    public void setFKidpays(Pays fKidpays) {
        this.fKidpays = fKidpays;
    }

    public Image getFKidImage() {
        return fKidImage;
    }

    public void setFKidImage(Image fKidImage) {
        this.fKidImage = fKidImage;
    }

    public EtatUtilisateur getFKidetatutlisateur() {
        return fKidetatutlisateur;
    }

    public void setFKidetatutlisateur(EtatUtilisateur fKidetatutlisateur) {
        this.fKidetatutlisateur = fKidetatutlisateur;
    }

    @XmlTransient
    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
    }

    @XmlTransient
    public Collection<Commentaire> getCommentaireCollection1() {
        return commentaireCollection1;
    }

    public void setCommentaireCollection1(Collection<Commentaire> commentaireCollection1) {
        this.commentaireCollection1 = commentaireCollection1;
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
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
    }
    
}
