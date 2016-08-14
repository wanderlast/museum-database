/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MuseumGUI;

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
@Table(name = "LLRP_MAINTENANCE", catalog = "", schema = "CS342")
@NamedQueries({
    @NamedQuery(name = "LlrpMaintenance.findAll", query = "SELECT l FROM LlrpMaintenance l"),
    @NamedQuery(name = "LlrpMaintenance.findByMaintainid", query = "SELECT l FROM LlrpMaintenance l WHERE l.maintainid = :maintainid"),
    @NamedQuery(name = "LlrpMaintenance.findByProcedures", query = "SELECT l FROM LlrpMaintenance l WHERE l.procedures = :procedures")})
public class LlrpMaintenance implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MAINTAINID")
    private Integer maintainid;
    @Basic(optional = false)
    @Column(name = "PROCEDURES")
    private String procedures;

    public LlrpMaintenance() {
    }

    public LlrpMaintenance(Integer maintainid) {
        this.maintainid = maintainid;
    }

    public LlrpMaintenance(Integer maintainid, String procedures) {
        this.maintainid = maintainid;
        this.procedures = procedures;
    }

    public Integer getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(Integer maintainid) {
        Integer oldMaintainid = this.maintainid;
        this.maintainid = maintainid;
        changeSupport.firePropertyChange("maintainid", oldMaintainid, maintainid);
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        String oldProcedures = this.procedures;
        this.procedures = procedures;
        changeSupport.firePropertyChange("procedures", oldProcedures, procedures);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maintainid != null ? maintainid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LlrpMaintenance)) {
            return false;
        }
        LlrpMaintenance other = (LlrpMaintenance) object;
        if ((this.maintainid == null && other.maintainid != null) || (this.maintainid != null && !this.maintainid.equals(other.maintainid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MuseumGUI.LlrpMaintenance[ maintainid=" + maintainid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
