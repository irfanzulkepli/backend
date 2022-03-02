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

@Entity(name="websockets_statistics_entries")
public class WebsocketsStatisticsEntries implements Serializable {

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
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="app_id", nullable=false, length=191)
    private String appId;
    @Column(name="peak_connection_count", nullable=false, precision=10)
    private int peakConnectionCount;
    @Column(name="websocket_message_count", nullable=false, precision=10)
    private int websocketMessageCount;
    @Column(name="api_message_count", nullable=false, precision=10)
    private int apiMessageCount;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;

    /** Default constructor. */
    public WebsocketsStatisticsEntries() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for appId.
     *
     * @return the current value of appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Setter method for appId.
     *
     * @param aAppId the new value for appId
     */
    public void setAppId(String aAppId) {
        appId = aAppId;
    }

    /**
     * Access method for peakConnectionCount.
     *
     * @return the current value of peakConnectionCount
     */
    public int getPeakConnectionCount() {
        return peakConnectionCount;
    }

    /**
     * Setter method for peakConnectionCount.
     *
     * @param aPeakConnectionCount the new value for peakConnectionCount
     */
    public void setPeakConnectionCount(int aPeakConnectionCount) {
        peakConnectionCount = aPeakConnectionCount;
    }

    /**
     * Access method for websocketMessageCount.
     *
     * @return the current value of websocketMessageCount
     */
    public int getWebsocketMessageCount() {
        return websocketMessageCount;
    }

    /**
     * Setter method for websocketMessageCount.
     *
     * @param aWebsocketMessageCount the new value for websocketMessageCount
     */
    public void setWebsocketMessageCount(int aWebsocketMessageCount) {
        websocketMessageCount = aWebsocketMessageCount;
    }

    /**
     * Access method for apiMessageCount.
     *
     * @return the current value of apiMessageCount
     */
    public int getApiMessageCount() {
        return apiMessageCount;
    }

    /**
     * Setter method for apiMessageCount.
     *
     * @param aApiMessageCount the new value for apiMessageCount
     */
    public void setApiMessageCount(int aApiMessageCount) {
        apiMessageCount = aApiMessageCount;
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
     * Compares the key for this instance with another WebsocketsStatisticsEntries.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class WebsocketsStatisticsEntries and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof WebsocketsStatisticsEntries)) {
            return false;
        }
        WebsocketsStatisticsEntries that = (WebsocketsStatisticsEntries) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another WebsocketsStatisticsEntries.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WebsocketsStatisticsEntries)) return false;
        return this.equalKeys(other) && ((WebsocketsStatisticsEntries)other).equalKeys(this);
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
        i = getId();
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
        StringBuffer sb = new StringBuffer("[WebsocketsStatisticsEntries |");
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
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
