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

@Entity(name="user_social_profile_links")
public class UserSocialProfileLinks implements Serializable {

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
    @Column(length=191)
    private String facebook;
    @Column(length=191)
    private String twitter;
    @Column(length=191)
    private String linkedin;
    @Column(length=191)
    private String behance;
    @Column(length=191)
    private String youtube;
    @Column(length=191)
    private String instagram;
    @Column(length=191)
    private String dribble;
    @Column(name="google_plus", length=191)
    private String googlePlus;
    @Column(length=191)
    private String skype;
    @Column(length=191)
    private String pinterest;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    /** Default constructor. */
    public UserSocialProfileLinks() {
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
     * Access method for facebook.
     *
     * @return the current value of facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * Setter method for facebook.
     *
     * @param aFacebook the new value for facebook
     */
    public void setFacebook(String aFacebook) {
        facebook = aFacebook;
    }

    /**
     * Access method for twitter.
     *
     * @return the current value of twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * Setter method for twitter.
     *
     * @param aTwitter the new value for twitter
     */
    public void setTwitter(String aTwitter) {
        twitter = aTwitter;
    }

    /**
     * Access method for linkedin.
     *
     * @return the current value of linkedin
     */
    public String getLinkedin() {
        return linkedin;
    }

    /**
     * Setter method for linkedin.
     *
     * @param aLinkedin the new value for linkedin
     */
    public void setLinkedin(String aLinkedin) {
        linkedin = aLinkedin;
    }

    /**
     * Access method for behance.
     *
     * @return the current value of behance
     */
    public String getBehance() {
        return behance;
    }

    /**
     * Setter method for behance.
     *
     * @param aBehance the new value for behance
     */
    public void setBehance(String aBehance) {
        behance = aBehance;
    }

    /**
     * Access method for youtube.
     *
     * @return the current value of youtube
     */
    public String getYoutube() {
        return youtube;
    }

    /**
     * Setter method for youtube.
     *
     * @param aYoutube the new value for youtube
     */
    public void setYoutube(String aYoutube) {
        youtube = aYoutube;
    }

    /**
     * Access method for instagram.
     *
     * @return the current value of instagram
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     * Setter method for instagram.
     *
     * @param aInstagram the new value for instagram
     */
    public void setInstagram(String aInstagram) {
        instagram = aInstagram;
    }

    /**
     * Access method for dribble.
     *
     * @return the current value of dribble
     */
    public String getDribble() {
        return dribble;
    }

    /**
     * Setter method for dribble.
     *
     * @param aDribble the new value for dribble
     */
    public void setDribble(String aDribble) {
        dribble = aDribble;
    }

    /**
     * Access method for googlePlus.
     *
     * @return the current value of googlePlus
     */
    public String getGooglePlus() {
        return googlePlus;
    }

    /**
     * Setter method for googlePlus.
     *
     * @param aGooglePlus the new value for googlePlus
     */
    public void setGooglePlus(String aGooglePlus) {
        googlePlus = aGooglePlus;
    }

    /**
     * Access method for skype.
     *
     * @return the current value of skype
     */
    public String getSkype() {
        return skype;
    }

    /**
     * Setter method for skype.
     *
     * @param aSkype the new value for skype
     */
    public void setSkype(String aSkype) {
        skype = aSkype;
    }

    /**
     * Access method for pinterest.
     *
     * @return the current value of pinterest
     */
    public String getPinterest() {
        return pinterest;
    }

    /**
     * Setter method for pinterest.
     *
     * @param aPinterest the new value for pinterest
     */
    public void setPinterest(String aPinterest) {
        pinterest = aPinterest;
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
     * Compares the key for this instance with another UserSocialProfileLinks.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserSocialProfileLinks and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserSocialProfileLinks)) {
            return false;
        }
        UserSocialProfileLinks that = (UserSocialProfileLinks) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserSocialProfileLinks.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserSocialProfileLinks)) return false;
        return this.equalKeys(other) && ((UserSocialProfileLinks)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[UserSocialProfileLinks |");
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
