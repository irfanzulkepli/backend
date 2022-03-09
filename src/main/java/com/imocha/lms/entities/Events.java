// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.imocha.lms.leads.entities.ContactTypes;

@Entity
@Table(name="events", indexes={@Index(name="eventsEventsContextableTypeContextableIdIndex", columnList="contextable_type,contextable_id")})
public class Events implements Serializable {

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
    @Column(name="contextable_type", nullable=false, length=191)
    private String contextableType;
    @Column(name="contextable_id", nullable=false, precision=20)
    private long contextableId;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="contact_type_id")
    private ContactTypes contactTypes;
    @ManyToOne
    @JoinColumn(name="created_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name="status_id")
    private Statuses statuses;

    /** Default constructor. */
    public Events() {
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
     * Access method for contextableType.
     *
     * @return the current value of contextableType
     */
    public String getContextableType() {
        return contextableType;
    }

    /**
     * Setter method for contextableType.
     *
     * @param aContextableType the new value for contextableType
     */
    public void setContextableType(String aContextableType) {
        contextableType = aContextableType;
    }

    /**
     * Access method for contextableId.
     *
     * @return the current value of contextableId
     */
    public long getContextableId() {
        return contextableId;
    }

    /**
     * Setter method for contextableId.
     *
     * @param aContextableId the new value for contextableId
     */
    public void setContextableId(long aContextableId) {
        contextableId = aContextableId;
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
     * Access method for contactTypes.
     *
     * @return the current value of contactTypes
     */
    public ContactTypes getContactTypes() {
        return contactTypes;
    }

    /**
     * Setter method for contactTypes.
     *
     * @param aContactTypes the new value for contactTypes
     */
    public void setContactTypes(ContactTypes aContactTypes) {
        contactTypes = aContactTypes;
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
     * Access method for statuses.
     *
     * @return the current value of statuses
     */
    public Statuses getStatuses() {
        return statuses;
    }

    /**
     * Setter method for statuses.
     *
     * @param aStatuses the new value for statuses
     */
    public void setStatuses(Statuses aStatuses) {
        statuses = aStatuses;
    }

    /**
     * Compares the key for this instance with another Events.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Events and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Events)) {
            return false;
        }
        Events that = (Events) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Events.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Events)) return false;
        return this.equalKeys(other) && ((Events)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Events |");
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
