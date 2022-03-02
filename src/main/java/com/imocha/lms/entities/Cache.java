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
@Table(name = "cache", indexes = { @Index(name = "cache_key_IX", columnList = "key", unique = true) })
public class Cache implements Serializable {

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
    private String key;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false, precision = 10)
    private int expiration;

    /** Default constructor. */
    public Cache() {
        super();
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
     * Access method for value.
     *
     * @return the current value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for value.
     *
     * @param aValue the new value for value
     */
    public void setValue(String aValue) {
        value = aValue;
    }

    /**
     * Access method for expiration.
     *
     * @return the current value of expiration
     */
    public int getExpiration() {
        return expiration;
    }

    /**
     * Setter method for expiration.
     *
     * @param aExpiration the new value for expiration
     */
    public void setExpiration(int aExpiration) {
        expiration = aExpiration;
    }

}
