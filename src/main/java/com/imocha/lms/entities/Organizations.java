// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.imocha.lms.users.entities.Users;

@Entity(name="organizations")
public class Organizations implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name="LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=20)
    private long id;
    @Column(nullable=false, length=191)
    private String name;
    private String address;
    @Column(name="contact_type_id", nullable=false, precision=20)
    private long contactTypeId;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    private String area;
    @Column(length=191)
    private String state;
    @Column(length=191)
    private String city;
    @Column(name="zip_code", length=191)
    private String zipCode;
    @ManyToOne
    @JoinColumn(name="country_id")
    private Countries countries;
    @ManyToOne
    @JoinColumn(name="created_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Users users2;
    @OneToMany(mappedBy="organizations")
    private Set<PersonOrganization> personOrganization;

    /** Default constructor. */
    public Organizations() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(long aId) {
        id = aId;
    }

    /**
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for contactTypeId.
     *
     * @return the current value of contactTypeId
     */
    public long getContactTypeId() {
        return contactTypeId;
    }

    /**
     * Setter method for contactTypeId.
     *
     * @param aContactTypeId the new value for contactTypeId
     */
    public void setContactTypeId(long aContactTypeId) {
        contactTypeId = aContactTypeId;
    }

    /**
     * Access method for createdAt.
     *
     * @return the current value of createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter method for createdAt.
     *
     * @param aCreatedAt the new value for createdAt
     */
    public void setCreatedAt(Date aCreatedAt) {
        createdAt = aCreatedAt;
    }

    /**
     * Access method for updatedAt.
     *
     * @return the current value of updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Setter method for updatedAt.
     *
     * @param aUpdatedAt the new value for updatedAt
     */
    public void setUpdatedAt(Date aUpdatedAt) {
        updatedAt = aUpdatedAt;
    }

    /**
     * Access method for area.
     *
     * @return the current value of area
     */
    public String getArea() {
        return area;
    }

    /**
     * Setter method for area.
     *
     * @param aArea the new value for area
     */
    public void setArea(String aArea) {
        area = aArea;
    }

    /**
     * Access method for state.
     *
     * @return the current value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for state.
     *
     * @param aState the new value for state
     */
    public void setState(String aState) {
        state = aState;
    }

    /**
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for zipCode.
     *
     * @return the current value of zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Setter method for zipCode.
     *
     * @param aZipCode the new value for zipCode
     */
    public void setZipCode(String aZipCode) {
        zipCode = aZipCode;
    }

    /**
     * Access method for countries.
     *
     * @return the current value of countries
     */
    public Countries getCountries() {
        return countries;
    }

    /**
     * Setter method for countries.
     *
     * @param aCountries the new value for countries
     */
    public void setCountries(Countries aCountries) {
        countries = aCountries;
    }

    /**
     * Access method for users.
     *
     * @return the current value of users
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Setter method for users.
     *
     * @param aUsers the new value for users
     */
    public void setUsers(Users aUsers) {
        users = aUsers;
    }

    /**
     * Access method for users2.
     *
     * @return the current value of users2
     */
    public Users getUsers2() {
        return users2;
    }

    /**
     * Setter method for users2.
     *
     * @param aUsers2 the new value for users2
     */
    public void setUsers2(Users aUsers2) {
        users2 = aUsers2;
    }

    /**
     * Access method for personOrganization.
     *
     * @return the current value of personOrganization
     */
    public Set<PersonOrganization> getPersonOrganization() {
        return personOrganization;
    }

    /**
     * Setter method for personOrganization.
     *
     * @param aPersonOrganization the new value for personOrganization
     */
    public void setPersonOrganization(Set<PersonOrganization> aPersonOrganization) {
        personOrganization = aPersonOrganization;
    }

    /**
     * Compares the key for this instance with another Organizations.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Organizations and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Organizations)) {
            return false;
        }
        Organizations that = (Organizations) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Organizations.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Organizations)) return false;
        return this.equalKeys(other) && ((Organizations)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = (int)(getId() ^ (getId()>>>32));
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Organizations |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Long.valueOf(getId()));
        return ret;
    }

}
