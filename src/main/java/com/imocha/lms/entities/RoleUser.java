// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.imocha.lms.users.entities.Users;

@Entity(name="role_user")
@IdClass(RoleUser.RoleUserId.class)
public class RoleUser implements Serializable {

    /**
     * IdClass for primary key when using JPA annotations
     */
    public class RoleUserId implements Serializable {
        Users users;
        Roles roles;
    }

    /** Primary key. */
    protected static final String PK = "RoleUserPrimary";

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

    @ManyToOne(optional=false)
    @Id
    @JoinColumn(name="role_id", nullable=false)
    private Roles roles;
    @ManyToOne(optional=false)
    @Id
    @JoinColumn(name="user_id", nullable=false)
    private Users users;

    /** Default constructor. */
    public RoleUser() {
        super();
    }

    /**
     * Access method for roles.
     *
     * @return the current value of roles
     */
    public Roles getRoles() {
        return roles;
    }

    /**
     * Setter method for roles.
     *
     * @param aRoles the new value for roles
     */
    public void setRoles(Roles aRoles) {
        roles = aRoles;
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

    /** Temporary value holder for group key fragment usersId */
    private transient long tempUsersId;

    /**
     * Gets the key fragment id for member users.
     * If this.users is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setUsersId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Users
     */
    public long getUsersId() {
        return (getUsers() == null ? tempUsersId : getUsers().getId());
    }

    /**
     * Sets the key fragment id from member users.
     * If this.users is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getUsersId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Users
     */
    public void setUsersId(long aId) {
        if (getUsers() == null) {
            tempUsersId = aId;
        } else {
            getUsers().setId(aId);
        }
    }

    /** Temporary value holder for group key fragment rolesId */
    private transient long tempRolesId;

    /**
     * Gets the key fragment id for member roles.
     * If this.roles is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setRolesId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Roles
     */
    public long getRolesId() {
        return (getRoles() == null ? tempRolesId : getRoles().getId());
    }

    /**
     * Sets the key fragment id from member roles.
     * If this.roles is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getRolesId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Roles
     */
    public void setRolesId(long aId) {
        if (getRoles() == null) {
            tempRolesId = aId;
        } else {
            getRoles().setId(aId);
        }
    }

    /**
     * Compares the key for this instance with another RoleUser.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class RoleUser and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RoleUser)) {
            return false;
        }
        RoleUser that = (RoleUser) other;
        if (this.getUsersId() != that.getUsersId()) {
            return false;
        }
        if (this.getRolesId() != that.getRolesId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another RoleUser.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RoleUser)) return false;
        return this.equalKeys(other) && ((RoleUser)other).equalKeys(this);
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
        i = (int)(getUsersId() ^ (getUsersId()>>>32));
        result = 37*result + i;
        i = (int)(getRolesId() ^ (getRolesId()>>>32));
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
        StringBuffer sb = new StringBuffer("[RoleUser |");
        sb.append(" usersId=").append(getUsersId());
        sb.append(" rolesId=").append(getRolesId());
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
        ret.put("usersId", Long.valueOf(getUsersId()));
        ret.put("rolesId", Long.valueOf(getRolesId()));
        return ret;
    }

}
