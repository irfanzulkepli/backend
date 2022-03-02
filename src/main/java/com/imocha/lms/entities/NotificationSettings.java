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

@Entity(name="notification_settings")
public class NotificationSettings implements Serializable {

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
    @Column(name="notify_by", nullable=false, length=191)
    private String notifyBy;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @OneToMany(mappedBy="notificationSettings")
    private Set<NotificationAudiences> notificationAudiences;
    @ManyToOne(optional=false)
    @JoinColumn(name="notification_event_id", nullable=false)
    private NotificationEvents notificationEvents;
    @ManyToOne
    @JoinColumn(name="updated_by")
    private Users users;

    /** Default constructor. */
    public NotificationSettings() {
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
     * Access method for notifyBy.
     *
     * @return the current value of notifyBy
     */
    public String getNotifyBy() {
        return notifyBy;
    }

    /**
     * Setter method for notifyBy.
     *
     * @param aNotifyBy the new value for notifyBy
     */
    public void setNotifyBy(String aNotifyBy) {
        notifyBy = aNotifyBy;
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
     * Access method for notificationAudiences.
     *
     * @return the current value of notificationAudiences
     */
    public Set<NotificationAudiences> getNotificationAudiences() {
        return notificationAudiences;
    }

    /**
     * Setter method for notificationAudiences.
     *
     * @param aNotificationAudiences the new value for notificationAudiences
     */
    public void setNotificationAudiences(Set<NotificationAudiences> aNotificationAudiences) {
        notificationAudiences = aNotificationAudiences;
    }

    /**
     * Access method for notificationEvents.
     *
     * @return the current value of notificationEvents
     */
    public NotificationEvents getNotificationEvents() {
        return notificationEvents;
    }

    /**
     * Setter method for notificationEvents.
     *
     * @param aNotificationEvents the new value for notificationEvents
     */
    public void setNotificationEvents(NotificationEvents aNotificationEvents) {
        notificationEvents = aNotificationEvents;
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
     * Compares the key for this instance with another NotificationSettings.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class NotificationSettings and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof NotificationSettings)) {
            return false;
        }
        NotificationSettings that = (NotificationSettings) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another NotificationSettings.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof NotificationSettings)) return false;
        return this.equalKeys(other) && ((NotificationSettings)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[NotificationSettings |");
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
