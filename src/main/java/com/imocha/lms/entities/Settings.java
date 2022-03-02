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
@Table(name="settings", indexes={@Index(name="settingsSettingableIndex", columnList="settingable_type,settingable_id")})
public class Settings implements Serializable {

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
    private String value;
    @Column(name="settingable_type", length=160)
    private String settingableType;
    @Column(name="settingable_id", precision=20)
    private long settingableId;
    @Column(length=191)
    private String context;
    @Column(nullable=false, length=3)
    private boolean autoload;
    @Column(name="public", nullable=false, length=3)
    private boolean public_;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;

    /** Default constructor. */
    public Settings() {
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
     * Access method for settingableType.
     *
     * @return the current value of settingableType
     */
    public String getSettingableType() {
        return settingableType;
    }

    /**
     * Setter method for settingableType.
     *
     * @param aSettingableType the new value for settingableType
     */
    public void setSettingableType(String aSettingableType) {
        settingableType = aSettingableType;
    }

    /**
     * Access method for settingableId.
     *
     * @return the current value of settingableId
     */
    public long getSettingableId() {
        return settingableId;
    }

    /**
     * Setter method for settingableId.
     *
     * @param aSettingableId the new value for settingableId
     */
    public void setSettingableId(long aSettingableId) {
        settingableId = aSettingableId;
    }

    /**
     * Access method for context.
     *
     * @return the current value of context
     */
    public String getContext() {
        return context;
    }

    /**
     * Setter method for context.
     *
     * @param aContext the new value for context
     */
    public void setContext(String aContext) {
        context = aContext;
    }

    /**
     * Access method for autoload.
     *
     * @return true if and only if autoload is currently true
     */
    public boolean isAutoload() {
        return autoload;
    }

    /**
     * Setter method for autoload.
     *
     * @param aAutoload the new value for autoload
     */
    public void setAutoload(boolean aAutoload) {
        autoload = aAutoload;
    }

    /**
     * Access method for public_.
     *
     * @return true if and only if public_ is currently true
     */
    public boolean isPublic_() {
        return public_;
    }

    /**
     * Setter method for public_.
     *
     * @param aPublic_ the new value for public_
     */
    public void setPublic_(boolean aPublic_) {
        public_ = aPublic_;
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
     * Compares the key for this instance with another Settings.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Settings and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Settings)) {
            return false;
        }
        Settings that = (Settings) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Settings.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Settings)) return false;
        return this.equalKeys(other) && ((Settings)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Settings |");
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
