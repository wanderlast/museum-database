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
@Table(name = "LLRP_COLLECTION", catalog = "", schema = "CS342")
@NamedQueries({
    @NamedQuery(name = "LlrpCollection.findAll", query = "SELECT l FROM LlrpCollection l"),
    @NamedQuery(name = "LlrpCollection.findByCollectid", query = "SELECT l FROM LlrpCollection l WHERE l.collectid = :collectid"),
    @NamedQuery(name = "LlrpCollection.findByName", query = "SELECT l FROM LlrpCollection l WHERE l.name = :name"),
    @NamedQuery(name = "LlrpCollection.findByDescription", query = "SELECT l FROM LlrpCollection l WHERE l.description = :description"),
    @NamedQuery(name = "LlrpCollection.findByYearoforigin", query = "SELECT l FROM LlrpCollection l WHERE l.yearoforigin = :yearoforigin"),
    @NamedQuery(name = "LlrpCollection.findByTypes", query = "SELECT l FROM LlrpCollection l WHERE l.types = :types"),
    @NamedQuery(name = "LlrpCollection.findByCreator", query = "SELECT l FROM LlrpCollection l WHERE l.creator = :creator"),
    @NamedQuery(name = "LlrpCollection.findByMaterial", query = "SELECT l FROM LlrpCollection l WHERE l.material = :material")})
public class LlrpCollection implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COLLECTID")
    private Integer collectid;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "YEAROFORIGIN")
    private short yearoforigin;
    @Basic(optional = false)
    @Column(name = "TYPES")
    private String types;
    @Column(name = "CREATOR")
    private String creator;
    @Basic(optional = false)
    @Column(name = "MATERIAL")
    private String material;

    public LlrpCollection() {
    }

    public LlrpCollection(Integer collectid) {
        this.collectid = collectid;
    }

    public LlrpCollection(Integer collectid, String name, String description, short yearoforigin, String types, String material) {
        this.collectid = collectid;
        this.name = name;
        this.description = description;
        this.yearoforigin = yearoforigin;
        this.types = types;
        this.material = material;
    }

    public Integer getCollectid() {
        return collectid;
    }

    public void setCollectid(Integer collectid) {
        Integer oldCollectid = this.collectid;
        this.collectid = collectid;
        changeSupport.firePropertyChange("collectid", oldCollectid, collectid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public short getYearoforigin() {
        return yearoforigin;
    }

    public void setYearoforigin(short yearoforigin) {
        short oldYearoforigin = this.yearoforigin;
        this.yearoforigin = yearoforigin;
        changeSupport.firePropertyChange("yearoforigin", oldYearoforigin, yearoforigin);
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        String oldTypes = this.types;
        this.types = types;
        changeSupport.firePropertyChange("types", oldTypes, types);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        String oldCreator = this.creator;
        this.creator = creator;
        changeSupport.firePropertyChange("creator", oldCreator, creator);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collectid != null ? collectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LlrpCollection)) {
            return false;
        }
        LlrpCollection other = (LlrpCollection) object;
        if ((this.collectid == null && other.collectid != null) || (this.collectid != null && !this.collectid.equals(other.collectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MuseumGUI.LlrpCollection[ collectid=" + collectid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
