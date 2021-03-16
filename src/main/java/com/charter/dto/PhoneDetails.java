package com.charter.dto;

import java.util.List;

public class PhoneDetails {

    private String phoneNumber;
    
    private List<String> emailAddressList;

 public String getPhoneNumber() {
  return phoneNumber;
 }

 public void setPhoneNumber(String phoneNumber) {
  this.phoneNumber = phoneNumber;
 }

 public List<String> getEmailAddressList() {
  return emailAddressList;
 }

 public void setEmailAddressList(List<String> emailAddressList) {
  this.emailAddressList = emailAddressList;
 }

}