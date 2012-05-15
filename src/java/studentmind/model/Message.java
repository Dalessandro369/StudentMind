/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ProjetJava
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Message.findByObjetMessage", query = "SELECT m FROM Message m WHERE m.objetMessage = :objetMessage"),
    @NamedQuery(name = "Message.findByDateMessage", query = "SELECT m FROM Message m WHERE m.dateMessage = :dateMessage")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_message")
    private Integer idMessage;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "contenu_message")
    private String contenuMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "objet_message")
    private String objetMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_message")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMessage;
    @JoinColumn(name = "FK_id_utilisateur_des", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur fKidutilisateurdes;
    @JoinColumn(name = "FK_id_utilisateur_exp", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur fKidutilisateurexp;
    @JoinColumn(name = "FK_id_etat_message", referencedColumnName = "id_etat_message")
    @ManyToOne(optional = false)
    private EtatMessage fKidetatmessage;

    public Message() {
    }

    public Message(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Message(Integer idMessage, String contenuMessage, String objetMessage, Date dateMessage) {
        this.idMessage = idMessage;
        this.contenuMessage = contenuMessage;
        this.objetMessage = objetMessage;
        this.dateMessage = dateMessage;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }

    public String getObjetMessage() {
        return objetMessage;
    }

    public void setObjetMessage(String objetMessage) {
        this.objetMessage = objetMessage;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public Utilisateur getFKidutilisateurdes() {
        return fKidutilisateurdes;
    }

    public void setFKidutilisateurdes(Utilisateur fKidutilisateurdes) {
        this.fKidutilisateurdes = fKidutilisateurdes;
    }

    public Utilisateur getFKidutilisateurexp() {
        return fKidutilisateurexp;
    }

    public void setFKidutilisateurexp(Utilisateur fKidutilisateurexp) {
        this.fKidutilisateurexp = fKidutilisateurexp;
    }

    public EtatMessage getFKidetatmessage() {
        return fKidetatmessage;
    }

    public void setFKidetatmessage(EtatMessage fKidetatmessage) {
        this.fKidetatmessage = fKidetatmessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.Message[ idMessage=" + idMessage + " ]";
    }
    
}
