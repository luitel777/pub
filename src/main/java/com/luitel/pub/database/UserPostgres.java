package com.luitel.pub.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pubbers")
public class UserPostgres{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "email", unique = true)
  private String email;

  private String password;
  private String role;

  public Integer getId(){
    return this.id;
  }
  public String getUsername(){
    return this.username;
  }
  public String getEmail(){
    return this.email;
  }
  public String getPassword(){
    return this.password;
  }
  public String getRole(){
    return this.role;
  }

  public void setUsername(String username){
    this.username = username;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setPassword(String password){
    this.password = password;
  }

  
}
