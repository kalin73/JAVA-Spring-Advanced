package com.softuni.mobilelesec.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class UserActivationLinkEntity extends BaseEntity {

  @OneToOne
  private UserEntity user;

  private String code;

  public UserEntity getUser() {
    return user;
  }

  public UserActivationLinkEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public String getCode() {
    return code;
  }

  public UserActivationLinkEntity setCode(String code) {
    this.code = code;
    return this;
  }

}
