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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.imocha.lms.users.entities.Users;

@Entity
@Table(name="discussions", indexes={@Index(name="discussionsDiscussionsCommentableTypeCommentableIdIndex", columnList="commentable_type,commentable_id")})
public class Discussions implements Serializable {

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
    @Column(name="commentable_type", length=191)
    private String commentableType;
    @Column(name="commentable_id", precision=20)
    private long commentableId;
    @Column(name="comment_body", nullable=false)
    private String commentBody;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="commented_by")
    private Users users;
    @OneToMany(mappedBy="discussions2")
    private Set<Discussions> discussions3;
    @ManyToOne
    @JoinColumn(name="comment_id_of_reply")
    private Discussions discussions2;

    /** Default constructor. */
    public Discussions() {
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
     * Access method for commentableType.
     *
     * @return the current value of commentableType
     */
    public String getCommentableType() {
        return commentableType;
    }

    /**
     * Setter method for commentableType.
     *
     * @param aCommentableType the new value for commentableType
     */
    public void setCommentableType(String aCommentableType) {
        commentableType = aCommentableType;
    }

    /**
     * Access method for commentableId.
     *
     * @return the current value of commentableId
     */
    public long getCommentableId() {
        return commentableId;
    }

    /**
     * Setter method for commentableId.
     *
     * @param aCommentableId the new value for commentableId
     */
    public void setCommentableId(long aCommentableId) {
        commentableId = aCommentableId;
    }

    /**
     * Access method for commentBody.
     *
     * @return the current value of commentBody
     */
    public String getCommentBody() {
        return commentBody;
    }

    /**
     * Setter method for commentBody.
     *
     * @param aCommentBody the new value for commentBody
     */
    public void setCommentBody(String aCommentBody) {
        commentBody = aCommentBody;
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
     * Access method for discussions3.
     *
     * @return the current value of discussions3
     */
    public Set<Discussions> getDiscussions3() {
        return discussions3;
    }

    /**
     * Setter method for discussions3.
     *
     * @param aDiscussions3 the new value for discussions3
     */
    public void setDiscussions3(Set<Discussions> aDiscussions3) {
        discussions3 = aDiscussions3;
    }

    /**
     * Access method for discussions2.
     *
     * @return the current value of discussions2
     */
    public Discussions getDiscussions2() {
        return discussions2;
    }

    /**
     * Setter method for discussions2.
     *
     * @param aDiscussions2 the new value for discussions2
     */
    public void setDiscussions2(Discussions aDiscussions2) {
        discussions2 = aDiscussions2;
    }

    /**
     * Compares the key for this instance with another Discussions.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Discussions and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Discussions)) {
            return false;
        }
        Discussions that = (Discussions) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Discussions.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Discussions)) return false;
        return this.equalKeys(other) && ((Discussions)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Discussions |");
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
