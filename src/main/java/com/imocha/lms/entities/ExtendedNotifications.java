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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name="extended_notifications")
public class ExtendedNotifications implements Serializable {

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
    @Column(name="contextable_type", nullable=false, length=191)
    private String contextableType;
    @Column(name="contextable_id", nullable=false, precision=20)
    private long contextableId;
    @Column(nullable=false, length=191)
    private String audience;
    private String message;
    @Column(name="readers_id", nullable=false, length=191)
    private String readersId;
    @Column(name="read_at", length=191)
    private String readAt;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne(optional=false)
    @JoinColumn(name="user_id", nullable=false)
    private Users users;

    /** Default constructor. */
    public ExtendedNotifications() {
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
     * Access method for audience.
     *
     * @return the current value of audience
     */
    public String getAudience() {
        return audience;
    }

    /**
     * Setter method for audience.
     *
     * @param aAudience the new value for audience
     */
    public void setAudience(String aAudience) {
        audience = aAudience;
    }

    /**
     * Access method for message.
     *
     * @return the current value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for message.
     *
     * @param aMessage the new value for message
     */
    public void setMessage(String aMessage) {
        message = aMessage;
    }

    /**
     * Access method for readersId.
     *
     * @return the current value of readersId
     */
    public String getReadersId() {
        return readersId;
    }

    /**
     * Setter method for readersId.
     *
     * @param aReadersId the new value for readersId
     */
    public void setReadersId(String aReadersId) {
        readersId = aReadersId;
    }

    /**
     * Access method for readAt.
     *
     * @return the current value of readAt
     */
    public String getReadAt() {
        return readAt;
    }

    /**
     * Setter method for readAt.
     *
     * @param aReadAt the new value for readAt
     */
    public void setReadAt(String aReadAt) {
        readAt = aReadAt;
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
     * Compares the key for this instance with another ExtendedNotifications.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ExtendedNotifications and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ExtendedNotifications)) {
            return false;
        }
        ExtendedNotifications that = (ExtendedNotifications) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ExtendedNotifications.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ExtendedNotifications)) return false;
        return this.equalKeys(other) && ((ExtendedNotifications)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[ExtendedNotifications |");
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
