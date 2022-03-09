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

@Entity(name="custom_field_values")
public class CustomFieldValues implements Serializable {

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
    private String value;
    @Column(name="contextable_type", nullable=false, length=191)
    private String contextableType;
    @Column(name="contextable_id", nullable=false, length=191)
    private String contextableId;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne(optional=false)
    @JoinColumn(name="custom_field_id", nullable=false)
    private CustomFields customFields;
    @ManyToOne
    @JoinColumn(name="updated_by")
    private Users users;

    /** Default constructor. */
    public CustomFieldValues() {
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
    public String getContextableId() {
        return contextableId;
    }

    /**
     * Setter method for contextableId.
     *
     * @param aContextableId the new value for contextableId
     */
    public void setContextableId(String aContextableId) {
        contextableId = aContextableId;
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
     * Access method for customFields.
     *
     * @return the current value of customFields
     */
    public CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Setter method for customFields.
     *
     * @param aCustomFields the new value for customFields
     */
    public void setCustomFields(CustomFields aCustomFields) {
        customFields = aCustomFields;
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
     * Compares the key for this instance with another CustomFieldValues.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CustomFieldValues and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CustomFieldValues)) {
            return false;
        }
        CustomFieldValues that = (CustomFieldValues) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CustomFieldValues.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CustomFieldValues)) return false;
        return this.equalKeys(other) && ((CustomFieldValues)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[CustomFieldValues |");
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
