/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MuseumGUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Defenestrate
 */
@Entity
@Table(name = "LLRP_WORKER", catalog = "", schema = "CS342")
@NamedQueries({
    @NamedQuery(name = "LlrpWorker.findAll", query = "SELECT l FROM LlrpWorker l"),
    @NamedQuery(name = "LlrpWorker.findBySsn", query = "SELECT l FROM LlrpWorker l WHERE l.ssn = :ssn"),
    @NamedQuery(name = "LlrpWorker.findByFname", query = "SELECT l FROM LlrpWorker l WHERE l.fname = :fname"),
    @NamedQuery(name = "LlrpWorker.findByMname", query = "SELECT l FROM LlrpWorker l WHERE l.mname = :mname"),
    @NamedQuery(name = "LlrpWorker.findByLname", query = "SELECT l FROM LlrpWorker l WHERE l.lname = :lname"),
    @NamedQuery(name = "LlrpWorker.findByStreet1", query = "SELECT l FROM LlrpWorker l WHERE l.street1 = :street1"),
    @NamedQuery(name = "LlrpWorker.findByStreet2", query = "SELECT l FROM LlrpWorker l WHERE l.street2 = :street2"),
    @NamedQuery(name = "LlrpWorker.findByCity", query = "SELECT l FROM LlrpWorker l WHERE l.city = :city"),
    @NamedQuery(name = "LlrpWorker.findByZip", query = "SELECT l FROM LlrpWorker l WHERE l.zip = :zip"),
    @NamedQuery(name = "LlrpWorker.findByAge", query = "SELECT l FROM LlrpWorker l WHERE l.age = :age"),
    @NamedQuery(name = "LlrpWorker.findByGender", query = "SELECT l FROM LlrpWorker l WHERE l.gender = :gender"),
    @NamedQuery(name = "LlrpWorker.findByStartdate", query = "SELECT l FROM LlrpWorker l WHERE l.startdate = :startdate")})
public class LlrpWorker implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SSN")
    private Integer ssn;
    @Basic(optional = false)
    @Column(name = "FNAME")
    private String fname;
    @Column(name = "MNAME")
    private String mname;
    @Basic(optional = false)
    @Column(name = "LNAME")
    private String lname;
    @Basic(optional = false)
    @Column(name = "STREET1")
    private String street1;
    @Column(name = "STREET2")
    private String street2;
    @Basic(optional = false)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @Column(name = "ZIP")
    private int zip;
    @Basic(optional = false)
    @Column(name = "AGE")
    private short age;
    @Basic(optional = false)
    @Column(name = "GENDER")
    private Character gender;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;

    public LlrpWorker() {
    }

    public LlrpWorker(Integer ssn) {
        this.ssn = ssn;
    }

    public LlrpWorker(Integer ssn, String fname, String lname, String street1, String city, int zip, short age, Character gender, Date startdate) {
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.street1 = street1;
        this.city = city;
        this.zip = zip;
        this.age = age;
        this.gender = gender;
        this.startdate = startdate;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        Integer oldSsn = this.ssn;
        this.ssn = ssn;
        changeSupport.firePropertyChange("ssn", oldSsn, ssn);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        String oldFname = this.fname;
        this.fname = fname;
        changeSupport.firePropertyChange("fname", oldFname, fname);
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        String oldMname = this.mname;
        this.mname = mname;
        changeSupport.firePropertyChange("mname", oldMname, mname);
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        String oldLname = this.lname;
        this.lname = lname;
        changeSupport.firePropertyChange("lname", oldLname, lname);
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

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        int oldZip = this.zip;
        this.zip = zip;
        changeSupport.firePropertyChange("zip", oldZip, zip);
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        short oldAge = this.age;
        this.age = age;
        changeSupport.firePropertyChange("age", oldAge, age);
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        Character oldGender = this.gender;
        this.gender = gender;
        changeSupport.firePropertyChange("gender", oldGender, gender);
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        Date oldStartdate = this.startdate;
        this.startdate = startdate;
        changeSupport.firePropertyChange("startdate", oldStartdate, startdate);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ssn != null ? ssn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LlrpWorker)) {
            return false;
        }
        LlrpWorker other = (LlrpWorker) object;
        if ((this.ssn == null && other.ssn != null) || (this.ssn != null && !this.ssn.equals(other.ssn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MuseumGUI.LlrpWorker[ ssn=" + ssn + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
