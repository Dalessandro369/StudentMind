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
@Table(name = "etat_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatMessage.findAll", query = "SELECT e FROM EtatMessage e"),
    @NamedQuery(name = "EtatMessage.findByIdEtatMessage", query = "SELECT e FROM EtatMessage e WHERE e.idEtatMessage = :idEtatMessage"),
    @NamedQuery(name = "EtatMessage.findByNomEtatMessage", query = "SELECT e FROM EtatMessage e WHERE e.nomEtatMessage = :nomEtatMessage")})
public class EtatMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_etat_message")
    private Integer idEtatMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nom_etat_message")
    private String nomEtatMessage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKidetatmessage")
    private Collection<Message> messageCollection;

    public EtatMessage() {
    }

    public EtatMessage(Integer idEtatMessage) {
        this.idEtatMessage = idEtatMessage;
    }

    public EtatMessage(Integer idEtatMessage, String nomEtatMessage) {
        this.idEtatMessage = idEtatMessage;
        this.nomEtatMessage = nomEtatMessage;
    }

    public Integer getIdEtatMessage() {
        return idEtatMessage;
    }

    public void setIdEtatMessage(Integer idEtatMessage) {
        this.idEtatMessage = idEtatMessage;
    }

    public String getNomEtatMessage() {
        return nomEtatMessage;
    }

    public void setNomEtatMessage(String nomEtatMessage) {
        this.nomEtatMessage = nomEtatMessage;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtatMessage != null ? idEtatMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatMessage)) {
            return false;
        }
        EtatMessage other = (EtatMessage) object;
        if ((this.idEtatMessage == null && other.idEtatMessage != null) || (this.idEtatMessage != null && !this.idEtatMessage.equals(other.idEtatMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentmind.model.EtatMessage[ idEtatMessage=" + idEtatMessage + " ]";
    }
    
}
