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

import com.imocha.lms.users.entities.Users;

@Entity(name="roles")
public class Roles implements Serializable {

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
    private String name;
    @Column(name="is_admin", nullable=false, length=3)
    private boolean isAdmin;
    @Column(name="is_default", nullable=false, length=3)
    private boolean isDefault;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @OneToMany(mappedBy="roles")
    private Set<RolePermission> rolePermission;
    @ManyToOne
    @JoinColumn(name="created_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name="type_id")
    private Types types;
    @OneToMany(mappedBy="roles")
    private Set<RoleUser> roleUser;

    /** Default constructor. */
    public Roles() {
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
     * Access method for isAdmin.
     *
     * @return true if and only if isAdmin is currently true
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * Setter method for isAdmin.
     *
     * @param aIsAdmin the new value for isAdmin
     */
    public void setIsAdmin(boolean aIsAdmin) {
        isAdmin = aIsAdmin;
    }

    /**
     * Access method for isDefault.
     *
     * @return true if and only if isDefault is currently true
     */
    public boolean isIsDefault() {
        return isDefault;
    }

    /**
     * Setter method for isDefault.
     *
     * @param aIsDefault the new value for isDefault
     */
    public void setIsDefault(boolean aIsDefault) {
        isDefault = aIsDefault;
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
     * Access method for rolePermission.
     *
     * @return the current value of rolePermission
     */
    public Set<RolePermission> getRolePermission() {
        return rolePermission;
    }

    /**
     * Setter method for rolePermission.
     *
     * @param aRolePermission the new value for rolePermission
     */
    public void setRolePermission(Set<RolePermission> aRolePermission) {
        rolePermission = aRolePermission;
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
     * Access method for types.
     *
     * @return the current value of types
     */
    public Types getTypes() {
        return types;
    }

    /**
     * Setter method for types.
     *
     * @param aTypes the new value for types
     */
    public void setTypes(Types aTypes) {
        types = aTypes;
    }

    /**
     * Access method for roleUser.
     *
     * @return the current value of roleUser
     */
    public Set<RoleUser> getRoleUser() {
        return roleUser;
    }

    /**
     * Setter method for roleUser.
     *
     * @param aRoleUser the new value for roleUser
     */
    public void setRoleUser(Set<RoleUser> aRoleUser) {
        roleUser = aRoleUser;
    }

    /**
     * Compares the key for this instance with another Roles.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Roles and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Roles)) {
            return false;
        }
        Roles that = (Roles) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Roles.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Roles)) return false;
        return this.equalKeys(other) && ((Roles)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Roles |");
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
