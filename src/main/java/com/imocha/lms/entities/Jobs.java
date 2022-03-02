// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name="jobs")
public class Jobs implements Serializable {

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
    @Column(nullable=false, length=160)
    private String queue;
    @Column(nullable=false)
    private String payload;
    @Column(nullable=false, precision=3)
    private short attempts;
    @Column(name="reserved_at", precision=10)
    private int reservedAt;
    @Column(name="available_at", nullable=false, precision=10)
    private int availableAt;
    @Column(name="created_at", nullable=false, precision=10)
    private int createdAt;

    /** Default constructor. */
    public Jobs() {
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
     * Access method for queue.
     *
     * @return the current value of queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * Setter method for queue.
     *
     * @param aQueue the new value for queue
     */
    public void setQueue(String aQueue) {
        queue = aQueue;
    }

    /**
     * Access method for payload.
     *
     * @return the current value of payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Setter method for payload.
     *
     * @param aPayload the new value for payload
     */
    public void setPayload(String aPayload) {
        payload = aPayload;
    }

    /**
     * Access method for attempts.
     *
     * @return the current value of attempts
     */
    public short getAttempts() {
        return attempts;
    }

    /**
     * Setter method for attempts.
     *
     * @param aAttempts the new value for attempts
     */
    public void setAttempts(short aAttempts) {
        attempts = aAttempts;
    }

    /**
     * Access method for reservedAt.
     *
     * @return the current value of reservedAt
     */
    public int getReservedAt() {
        return reservedAt;
    }

    /**
     * Setter method for reservedAt.
     *
     * @param aReservedAt the new value for reservedAt
     */
    public void setReservedAt(int aReservedAt) {
        reservedAt = aReservedAt;
    }

    /**
     * Access method for availableAt.
     *
     * @return the current value of availableAt
     */
    public int getAvailableAt() {
        return availableAt;
    }

    /**
     * Setter method for availableAt.
     *
     * @param aAvailableAt the new value for availableAt
     */
    public void setAvailableAt(int aAvailableAt) {
        availableAt = aAvailableAt;
    }

    /**
     * Access method for createdAt.
     *
     * @return the current value of createdAt
     */
    public int getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter method for createdAt.
     *
     * @param aCreatedAt the new value for createdAt
     */
    public void setCreatedAt(int aCreatedAt) {
        createdAt = aCreatedAt;
    }

    /**
     * Compares the key for this instance with another Jobs.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Jobs and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Jobs)) {
            return false;
        }
        Jobs that = (Jobs) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Jobs.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Jobs)) return false;
        return this.equalKeys(other) && ((Jobs)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Jobs |");
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
