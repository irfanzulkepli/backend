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
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="revisions", indexes={@Index(name="revisionsRevisionsRevisionableIdRevisionableTypeIndex", columnList="revisionable_id,revisionable_type")})
public class Revisions implements Serializable {

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
    @Column(name="revisionable_type", nullable=false, length=191)
    private String revisionableType;
    @Column(name="revisionable_id", nullable=false, precision=20)
    private long revisionableId;
    @Column(name="user_id", precision=20)
    private long userId;
    @Column(nullable=false, length=191)
    private String key;
    @Column(name="old_value")
    private String oldValue;
    @Column(name="new_value")
    private String newValue;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;

    /** Default constructor. */
    public Revisions() {
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
     * Access method for revisionableType.
     *
     * @return the current value of revisionableType
     */
    public String getRevisionableType() {
        return revisionableType;
    }

    /**
     * Setter method for revisionableType.
     *
     * @param aRevisionableType the new value for revisionableType
     */
    public void setRevisionableType(String aRevisionableType) {
        revisionableType = aRevisionableType;
    }

    /**
     * Access method for revisionableId.
     *
     * @return the current value of revisionableId
     */
    public long getRevisionableId() {
        return revisionableId;
    }

    /**
     * Setter method for revisionableId.
     *
     * @param aRevisionableId the new value for revisionableId
     */
    public void setRevisionableId(long aRevisionableId) {
        revisionableId = aRevisionableId;
    }

    /**
     * Access method for userId.
     *
     * @return the current value of userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Setter method for userId.
     *
     * @param aUserId the new value for userId
     */
    public void setUserId(long aUserId) {
        userId = aUserId;
    }

    /**
     * Access method for key.
     *
     * @return the current value of key
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter method for key.
     *
     * @param aKey the new value for key
     */
    public void setKey(String aKey) {
        key = aKey;
    }

    /**
     * Access method for oldValue.
     *
     * @return the current value of oldValue
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * Setter method for oldValue.
     *
     * @param aOldValue the new value for oldValue
     */
    public void setOldValue(String aOldValue) {
        oldValue = aOldValue;
    }

    /**
     * Access method for newValue.
     *
     * @return the current value of newValue
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * Setter method for newValue.
     *
     * @param aNewValue the new value for newValue
     */
    public void setNewValue(String aNewValue) {
        newValue = aNewValue;
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
     * Compares the key for this instance with another Revisions.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Revisions and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Revisions)) {
            return false;
        }
        Revisions that = (Revisions) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Revisions.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Revisions)) return false;
        return this.equalKeys(other) && ((Revisions)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Revisions |");
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
