package com.capco.capcopay.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="Subscription")
@Table(name = "CAPCOPAY_SUBSCRIPTION", uniqueConstraints={@UniqueConstraint(columnNames ={"subscriptionId"})})
@DynamicUpdate
public class Subscription implements Serializable {
 
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastSubscriptionDate == null) ? 0 : lastSubscriptionDate.hashCode());
		result = prime * result + ((subscriptionId == null) ? 0 : subscriptionId.hashCode());
		result = prime * result + ((userAccounts == null) ? 0 : userAccounts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastSubscriptionDate == null) {
			if (other.lastSubscriptionDate != null)
				return false;
		} else if (!lastSubscriptionDate.equals(other.lastSubscriptionDate))
			return false;
		if (subscriptionId == null) {
			if (other.subscriptionId != null)
				return false;
		} else if (!subscriptionId.equals(other.subscriptionId))
			return false;
		if (userAccounts == null) {
			if (other.userAccounts != null)
				return false;
		} else if (!userAccounts.equals(other.userAccounts))
			return false;
		return true;
	}

	@Column(length=100)
	private String subscriptionId;
    
	@Column(length=100)
    private String bankName;

    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLastSubscriptionDate() {
		return lastSubscriptionDate;
	}

	public void setLastSubscriptionDate(Date lastSubscriptionDate) {
		this.lastSubscriptionDate = lastSubscriptionDate;
	}

	@ManyToOne
    private User user;
	
	@OneToMany( mappedBy = "subscription")	
    private List<UserAccounts> userAccounts;
    
	public List<UserAccounts> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<UserAccounts> userAccounts) {
		this.userAccounts = userAccounts;
	}

	@Temporal(TemporalType.TIMESTAMP)
    private Date lastSubscriptionDate;
}
