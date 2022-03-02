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

import com.imocha.lms.deals.entities.DealStage;
import com.imocha.lms.deals.entities.Deals;

@Entity(name="stages")
public class Stages implements Serializable {

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
    @Column(precision=10)
    private int probability;
    @Column(nullable=false, precision=10)
    private int priority;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @OneToMany(mappedBy="stages")
    private Set<Deals> deals;
    @OneToMany(mappedBy="stages")
    private Set<DealStage> dealStage;
    @ManyToOne
    @JoinColumn(name="created_by")
    private Users users;
    @ManyToOne(optional=false)
    @JoinColumn(name="pipeline_id", nullable=false)
    private Pipelines pipelines;

    /** Default constructor. */
    public Stages() {
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
     * Access method for probability.
     *
     * @return the current value of probability
     */
    public int getProbability() {
        return probability;
    }

    /**
     * Setter method for probability.
     *
     * @param aProbability the new value for probability
     */
    public void setProbability(int aProbability) {
        probability = aProbability;
    }

    /**
     * Access method for priority.
     *
     * @return the current value of priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Setter method for priority.
     *
     * @param aPriority the new value for priority
     */
    public void setPriority(int aPriority) {
        priority = aPriority;
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
     * Access method for deals.
     *
     * @return the current value of deals
     */
    public Set<Deals> getDeals() {
        return deals;
    }

    /**
     * Setter method for deals.
     *
     * @param aDeals the new value for deals
     */
    public void setDeals(Set<Deals> aDeals) {
        deals = aDeals;
    }

    /**
     * Access method for dealStage.
     *
     * @return the current value of dealStage
     */
    public Set<DealStage> getDealStage() {
        return dealStage;
    }

    /**
     * Setter method for dealStage.
     *
     * @param aDealStage the new value for dealStage
     */
    public void setDealStage(Set<DealStage> aDealStage) {
        dealStage = aDealStage;
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
     * Access method for pipelines.
     *
     * @return the current value of pipelines
     */
    public Pipelines getPipelines() {
        return pipelines;
    }

    /**
     * Setter method for pipelines.
     *
     * @param aPipelines the new value for pipelines
     */
    public void setPipelines(Pipelines aPipelines) {
        pipelines = aPipelines;
    }

    /**
     * Compares the key for this instance with another Stages.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Stages and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Stages)) {
            return false;
        }
        Stages that = (Stages) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Stages.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Stages)) return false;
        return this.equalKeys(other) && ((Stages)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Stages |");
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
