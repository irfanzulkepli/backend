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

import com.imocha.lms.users.entities.Users;

@Entity(name="templates")
public class Templates implements Serializable {

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
    private String subject;
    @Column(name="default_content")
    private String defaultContent;
    @Column(name="custom_content")
    private String customContent;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="created_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name="updated_by")
    private Users users2;

    /** Default constructor. */
    public Templates() {
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
     * Access method for subject.
     *
     * @return the current value of subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter method for subject.
     *
     * @param aSubject the new value for subject
     */
    public void setSubject(String aSubject) {
        subject = aSubject;
    }

    /**
     * Access method for defaultContent.
     *
     * @return the current value of defaultContent
     */
    public String getDefaultContent() {
        return defaultContent;
    }

    /**
     * Setter method for defaultContent.
     *
     * @param aDefaultContent the new value for defaultContent
     */
    public void setDefaultContent(String aDefaultContent) {
        defaultContent = aDefaultContent;
    }

    /**
     * Access method for customContent.
     *
     * @return the current value of customContent
     */
    public String getCustomContent() {
        return customContent;
    }

    /**
     * Setter method for customContent.
     *
     * @param aCustomContent the new value for customContent
     */
    public void setCustomContent(String aCustomContent) {
        customContent = aCustomContent;
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
     * Access method for users2.
     *
     * @return the current value of users2
     */
    public Users getUsers2() {
        return users2;
    }

    /**
     * Setter method for users2.
     *
     * @param aUsers2 the new value for users2
     */
    public void setUsers2(Users aUsers2) {
        users2 = aUsers2;
    }

    /**
     * Compares the key for this instance with another Templates.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Templates and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Templates)) {
            return false;
        }
        Templates that = (Templates) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Templates.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Templates)) return false;
        return this.equalKeys(other) && ((Templates)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Templates |");
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
