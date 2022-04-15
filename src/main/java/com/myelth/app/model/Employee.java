package com.myelth.app.model;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8088220048382698693L;

	public Employee() {
	       
	   }
	   public Employee(Integer id, String firstName, String lastName, String email) {
	      super();
	      this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.email = email;
	   }
	    
	   private Integer id;
	   private String firstName;
	   private String lastName;
	   private String email;
	    
	   //getters and setters
	 
	   @Override
	   public String toString() {
	      return "Employee [id=" + id + ", firstName=" + firstName
	            + ", lastName=" + lastName + ", email=" + email + "]";
	   }
}
