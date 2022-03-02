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
import javax.persistence.Version;

@Entity(name="failed_jobs")
public class FailedJobs implements Serializable {

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
    @Column(nullable=false)
    private String connection;
    @Column(nullable=false)
    private String queue;
    @Column(nullable=false)
    private String payload;
    @Column(nullable=false)
    private String exception;
    @Column(name="failed_at", nullable=false)
    private Date failedAt;

    /** Default constructor. */
    public FailedJobs() {
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
     * Access method for connection.
     *
     * @return the current value of connection
     */
    public String getConnection() {
        return connection;
    }

    /**
     * Setter method for connection.
     *
     * @param aConnection the new value for connection
     */
    public void setConnection(String aConnection) {
        connection = aConnection;
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
     * Access method for exception.
     *
     * @return the current value of exception
     */
    public String getException() {
        return exception;
    }

    /**
     * Setter method for exception.
     *
     * @param aException the new value for exception
     */
    public void setException(String aException) {
        exception = aException;
    }

    /**
     * Access method for failedAt.
     *
     * @return the current value of failedAt
     */
    public Date getFailedAt() {
        return failedAt;
    }

    /**
     * Setter method for failedAt.
     *
     * @param aFailedAt the new value for failedAt
     */
    public void setFailedAt(Date aFailedAt) {
        failedAt = aFailedAt;
    }

    /**
     * Compares the key for this instance with another FailedJobs.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class FailedJobs and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof FailedJobs)) {
            return false;
        }
        FailedJobs that = (FailedJobs) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another FailedJobs.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FailedJobs)) return false;
        return this.equalKeys(other) && ((FailedJobs)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[FailedJobs |");
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
