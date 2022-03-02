// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sessions", indexes = { @Index(name = "sessions_id_IX", columnList = "id", unique = true) })
public class Sessions implements Serializable {

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name = "LOCK_FLAG")
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
    @Column(unique = true, nullable = false, length = 120)
    private String id;
    @Column(name = "user_id", precision = 10)
    private int userId;
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(nullable = false)
    private String payload;
    @Column(name = "last_activity", nullable = false, precision = 10)
    private int lastActivity;

    /** Default constructor. */
    public Sessions() {
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
     * Access method for userId.
     *
     * @return the current value of userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter method for userId.
     *
     * @param aUserId the new value for userId
     */
    public void setUserId(int aUserId) {
        userId = aUserId;
    }

    /**
     * Access method for ipAddress.
     *
     * @return the current value of ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Setter method for ipAddress.
     *
     * @param aIpAddress the new value for ipAddress
     */
    public void setIpAddress(String aIpAddress) {
        ipAddress = aIpAddress;
    }

    /**
     * Access method for userAgent.
     *
     * @return the current value of userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Setter method for userAgent.
     *
     * @param aUserAgent the new value for userAgent
     */
    public void setUserAgent(String aUserAgent) {
        userAgent = aUserAgent;
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
     * Access method for lastActivity.
     *
     * @return the current value of lastActivity
     */
    public int getLastActivity() {
        return lastActivity;
    }

    /**
     * Setter method for lastActivity.
     *
     * @param aLastActivity the new value for lastActivity
     */
    public void setLastActivity(int aLastActivity) {
        lastActivity = aLastActivity;
    }

}
