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

@Entity(name="role_permission")
@IdClass(RolePermission.RolePermissionId.class)
public class RolePermission implements Serializable {

    /**
     * IdClass for primary key when using JPA annotations
     */
    public class RolePermissionId implements Serializable {
        Permissions permissions;
        Roles roles;
    }

    /** Primary key. */
    protected static final String PK = "RolePermissionPrimary";

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

    private String meta;
    @ManyToOne(optional=false)
    @Id
    @JoinColumn(name="permission_id", nullable=false)
    private Permissions permissions;
    @ManyToOne(optional=false)
    @Id
    @JoinColumn(name="role_id", nullable=false)
    private Roles roles;

    /** Default constructor. */
    public RolePermission() {
        super();
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
     * Access method for permissions.
     *
     * @return the current value of permissions
     */
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * Setter method for permissions.
     *
     * @param aPermissions the new value for permissions
     */
    public void setPermissions(Permissions aPermissions) {
        permissions = aPermissions;
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

    /** Temporary value holder for group key fragment permissionsId */
    private transient long tempPermissionsId;

    /**
     * Gets the key fragment id for member permissions.
     * If this.permissions is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setPermissionsId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Permissions
     */
    public long getPermissionsId() {
        return (getPermissions() == null ? tempPermissionsId : getPermissions().getId());
    }

    /**
     * Sets the key fragment id from member permissions.
     * If this.permissions is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getPermissionsId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Permissions
     */
    public void setPermissionsId(long aId) {
        if (getPermissions() == null) {
            tempPermissionsId = aId;
        } else {
            getPermissions().setId(aId);
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
     * Compares the key for this instance with another RolePermission.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class RolePermission and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RolePermission)) {
            return false;
        }
        RolePermission that = (RolePermission) other;
        if (this.getPermissionsId() != that.getPermissionsId()) {
            return false;
        }
        if (this.getRolesId() != that.getRolesId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another RolePermission.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RolePermission)) return false;
        return this.equalKeys(other) && ((RolePermission)other).equalKeys(this);
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
        i = (int)(getPermissionsId() ^ (getPermissionsId()>>>32));
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
        StringBuffer sb = new StringBuffer("[RolePermission |");
        sb.append(" permissionsId=").append(getPermissionsId());
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
        ret.put("permissionsId", Long.valueOf(getPermissionsId()));
        ret.put("rolesId", Long.valueOf(getRolesId()));
        return ret;
    }

}
