package com.goeasy.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="seller_roles", 
		joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="seller_id", referencedColumnName="id")}
	)
	private Set<Seller> sellerRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Seller> getSellerRoles() {
		return sellerRoles;
	}

	public void setSellerRoles(Set<Seller> sellerRoles) {
		this.sellerRoles = sellerRoles;
	}

	
}
