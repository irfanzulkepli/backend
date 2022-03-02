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

import com.imocha.lms.deals.entities.Deals;

@Entity(name="proposals")
public class Proposals implements Serializable {

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
    private String subject;
    @Column(nullable=false)
    private String content;
    @Column(name="sent_at")
    private Date sentAt;
    @Column(name="accepted_at")
    private Date acceptedAt;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @Column(name="expired_at")
    private Date expiredAt;
    @ManyToOne
    @JoinColumn(name="created_by")
    private Users users2;
    @ManyToOne
    @JoinColumn(name="deal_id")
    private Deals deals;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name="status_id")
    private Statuses statuses;

    /** Default constructor. */
    public Proposals() {
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
     * Access method for content.
     *
     * @return the current value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for content.
     *
     * @param aContent the new value for content
     */
    public void setContent(String aContent) {
        content = aContent;
    }

    /**
     * Access method for sentAt.
     *
     * @return the current value of sentAt
     */
    public Date getSentAt() {
        return sentAt;
    }

    /**
     * Setter method for sentAt.
     *
     * @param aSentAt the new value for sentAt
     */
    public void setSentAt(Date aSentAt) {
        sentAt = aSentAt;
    }

    /**
     * Access method for acceptedAt.
     *
     * @return the current value of acceptedAt
     */
    public Date getAcceptedAt() {
        return acceptedAt;
    }

    /**
     * Setter method for acceptedAt.
     *
     * @param aAcceptedAt the new value for acceptedAt
     */
    public void setAcceptedAt(Date aAcceptedAt) {
        acceptedAt = aAcceptedAt;
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
     * Access method for expiredAt.
     *
     * @return the current value of expiredAt
     */
    public Date getExpiredAt() {
        return expiredAt;
    }

    /**
     * Setter method for expiredAt.
     *
     * @param aExpiredAt the new value for expiredAt
     */
    public void setExpiredAt(Date aExpiredAt) {
        expiredAt = aExpiredAt;
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
     * Access method for deals.
     *
     * @return the current value of deals
     */
    public Deals getDeals() {
        return deals;
    }

    /**
     * Setter method for deals.
     *
     * @param aDeals the new value for deals
     */
    public void setDeals(Deals aDeals) {
        deals = aDeals;
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
     * Access method for statuses.
     *
     * @return the current value of statuses
     */
    public Statuses getStatuses() {
        return statuses;
    }

    /**
     * Setter method for statuses.
     *
     * @param aStatuses the new value for statuses
     */
    public void setStatuses(Statuses aStatuses) {
        statuses = aStatuses;
    }

    /**
     * Compares the key for this instance with another Proposals.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Proposals and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Proposals)) {
            return false;
        }
        Proposals that = (Proposals) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Proposals.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Proposals)) return false;
        return this.equalKeys(other) && ((Proposals)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Proposals |");
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
