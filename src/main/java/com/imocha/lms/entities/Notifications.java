// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name="notifications")
public class Notifications implements Serializable {

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
    @Column(unique=true, nullable=false, length=36)
    private String id;
    @Column(nullable=false, length=191)
    private String type;
    @Column(name="notifiable_type", nullable=false, length=160)
    private String notifiableType;
    @Column(name="notifiable_id", nullable=false, precision=20)
    private long notifiableId;
    @Column(nullable=false)
    private String data;
    @Column(name="read_at")
    private Date readAt;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;

    /** Default constructor. */
    public Notifications() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(String aId) {
        id = aId;
    }

    /**
     * Access method for type.
     *
     * @return the current value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for type.
     *
     * @param aType the new value for type
     */
    public void setType(String aType) {
        type = aType;
    }

    /**
     * Access method for notifiableType.
     *
     * @return the current value of notifiableType
     */
    public String getNotifiableType() {
        return notifiableType;
    }

    /**
     * Setter method for notifiableType.
     *
     * @param aNotifiableType the new value for notifiableType
     */
    public void setNotifiableType(String aNotifiableType) {
        notifiableType = aNotifiableType;
    }

    /**
     * Access method for notifiableId.
     *
     * @return the current value of notifiableId
     */
    public long getNotifiableId() {
        return notifiableId;
    }

    /**
     * Setter method for notifiableId.
     *
     * @param aNotifiableId the new value for notifiableId
     */
    public void setNotifiableId(long aNotifiableId) {
        notifiableId = aNotifiableId;
    }

    /**
     * Access method for data.
     *
     * @return the current value of data
     */
    public String getData() {
        return data;
    }

    /**
     * Setter method for data.
     *
     * @param aData the new value for data
     */
    public void setData(String aData) {
        data = aData;
    }

    /**
     * Access method for readAt.
     *
     * @return the current value of readAt
     */
    public Date getReadAt() {
        return readAt;
    }

    /**
     * Setter method for readAt.
     *
     * @param aReadAt the new value for readAt
     */
    public void setReadAt(Date aReadAt) {
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
     * Compares the key for this instance with another Notifications.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Notifications and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Notifications)) {
            return false;
        }
        Notifications that = (Notifications) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Notifications.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Notifications)) return false;
        return this.equalKeys(other) && ((Notifications)other).equalKeys(this);
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
        if (getId() == null) {
            i = 0;
        } else {
            i = getId().hashCode();
        }
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
        StringBuffer sb = new StringBuffer("[Notifications |");
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
        ret.put("id", getId());
        return ret;
    }

}
