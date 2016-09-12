/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnderConstruction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Defenestrate
 */
@Entity
@Table(name = "LLRP_CONTRIBUTOR", catalog = "", schema = "CS342")
@NamedQueries({
    @NamedQuery(name = "LlrpContributor.findAll", query = "SELECT l FROM LlrpContributor l"),
    @NamedQuery(name = "LlrpContributor.findByContributeid", query = "SELECT l FROM LlrpContributor l WHERE l.contributeid = :contributeid"),
    @NamedQuery(name = "LlrpContributor.findByName", query = "SELECT l FROM LlrpContributor l WHERE l.name = :name"),
    @NamedQuery(name = "LlrpContributor.findByStreet1", query = "SELECT l FROM LlrpContributor l WHERE l.street1 = :street1"),
    @NamedQuery(name = "LlrpContributor.findByStreet2", query = "SELECT l FROM LlrpContributor l WHERE l.street2 = :street2"),
    @NamedQuery(name = "LlrpContributor.findByCity", query = "SELECT l FROM LlrpContributor l WHERE l.city = :city"),
    @NamedQuery(name = "LlrpContributor.findByState", query = "SELECT l FROM LlrpContributor l WHERE l.state = :state"),
    @NamedQuery(name = "LlrpContributor.findByZip", query = "SELECT l FROM LlrpContributor l WHERE l.zip = :zip"),
    @NamedQuery(name = "LlrpContributor.findByEmail", query = "SELECT l FROM LlrpContributor l WHERE l.email = :email"),
    @NamedQuery(name = "LlrpContributor.findByOrganization", query = "SELECT l FROM LlrpContributor l WHERE l.organization = :organization")})
public class LlrpContributor implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CONTRIBUTEID")
    private Integer contributeid;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "STREET1")
    private String street1;
    @Column(name = "STREET2")
    private String street2;
    @Basic(optional = false)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @Column(name = "STATE")
    private String state;
    @Basic(optional = false)
    @Column(name = "ZIP")
    private String zip;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ORGANIZATION")
    private String organization;

    public LlrpContributor() {
    }

    public LlrpContributor(Integer contributeid) {
        this.contributeid = contributeid;
    }

    public LlrpContributor(Integer contributeid, String name, String street1, String city, String state, String zip) {
        this.contributeid = contributeid;
        this.name = name;
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Integer getContributeid() {
        return contributeid;
    }

    public void setContributeid(Integer contributeid) {
        Integer oldContributeid = this.contributeid;
        this.contributeid = contributeid;
        changeSupport.firePropertyChange("contributeid", oldContributeid, contributeid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        String oldStreet1 = this.street1;
        this.street1 = street1;
        changeSupport.firePropertyChange("street1", oldStreet1, street1);
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        String oldStreet2 = this.street2;
        this.street2 = street2;
        changeSupport.firePropertyChange("street2", oldStreet2, street2);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        String oldCity = this.city;
        this.city = city;
        changeSupport.firePropertyChange("city", oldCity, city);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        String oldState = this.state;
        this.state = state;
        changeSupport.firePropertyChange("state", oldState, state);
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        String oldZip = this.zip;
        this.zip = zip;
        changeSupport.firePropertyChange("zip", oldZip, zip);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        String oldOrganization = this.organization;
        this.organization = organization;
        changeSupport.firePropertyChange("organization", oldOrganization, organization);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contributeid != null ? contributeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LlrpContributor)) {
            return false;
        }
        LlrpContributor other = (LlrpContributor) object;
        if ((this.contributeid == null && other.contributeid != null) || (this.contributeid != null && !this.contributeid.equals(other.contributeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MuseumGUI.LlrpContributor[ contributeid=" + contributeid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
