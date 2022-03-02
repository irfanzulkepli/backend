// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="custom_fields")
public class CustomFields implements Serializable {

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
    @Column(nullable=false, length=191)
    private String context;
    private String meta;
    @Column(name="in_list", nullable=false, length=3)
    private boolean inList;
    @Column(name="is_for_admin", nullable=false, length=3)
    private boolean isForAdmin;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne(optional=false)
    @JoinColumn(name="created_by", nullable=false)
    private Users users;
    @ManyToOne(optional=false)
    @JoinColumn(name="custom_field_type_id", nullable=false)
    private CustomFieldTypes customFieldTypes;
    @OneToMany(mappedBy="customFields")
    private Set<CustomFieldValues> customFieldValues;

    /** Default constructor. */
    public CustomFields() {
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
     * Access method for meta.
     *
     * @return the current value of meta
     */
    public String getMeta() {
        return meta;
    }

    /**
     * Setter method for meta.
     *
     * @param aMeta the new value for meta
     */
    public void setMeta(String aMeta) {
        meta = aMeta;
    }

    /**
     * Access method for inList.
     *
     * @return true if and only if inList is currently true
     */
    public boolean isInList() {
        return inList;
    }

    /**
     * Setter method for inList.
     *
     * @param aInList the new value for inList
     */
    public void setInList(boolean aInList) {
        inList = aInList;
    }

    /**
     * Access method for isForAdmin.
     *
     * @return true if and only if isForAdmin is currently true
     */
    public boolean isIsForAdmin() {
        return isForAdmin;
    }

    /**
     * Setter method for isForAdmin.
     *
     * @param aIsForAdmin the new value for isForAdmin
     */
    public void setIsForAdmin(boolean aIsForAdmin) {
        isForAdmin = aIsForAdmin;
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
     * Access method for customFieldTypes.
     *
     * @return the current value of customFieldTypes
     */
    public CustomFieldTypes getCustomFieldTypes() {
        return customFieldTypes;
    }

    /**
     * Setter method for customFieldTypes.
     *
     * @param aCustomFieldTypes the new value for customFieldTypes
     */
    public void setCustomFieldTypes(CustomFieldTypes aCustomFieldTypes) {
        customFieldTypes = aCustomFieldTypes;
    }

    /**
     * Access method for customFieldValues.
     *
     * @return the current value of customFieldValues
     */
    public Set<CustomFieldValues> getCustomFieldValues() {
        return customFieldValues;
    }

    /**
     * Setter method for customFieldValues.
     *
     * @param aCustomFieldValues the new value for customFieldValues
     */
    public void setCustomFieldValues(Set<CustomFieldValues> aCustomFieldValues) {
        customFieldValues = aCustomFieldValues;
    }

    /**
     * Compares the key for this instance with another CustomFields.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CustomFields and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CustomFields)) {
            return false;
        }
        CustomFields that = (CustomFields) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CustomFields.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CustomFields)) return false;
        return this.equalKeys(other) && ((CustomFields)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[CustomFields |");
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
