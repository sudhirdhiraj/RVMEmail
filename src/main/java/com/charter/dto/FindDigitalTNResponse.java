package com.charter.dto;

public class FindDigitalTNResponse {

  private String accountNumber;
  private String divisionID;
  private String soloAccountNumber;
  private String telephoneNumber;

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getDivisionID() {
    return divisionID;
  }

  public void setDivisionID(String divisionID) {
    this.divisionID = divisionID;
  }

  public String getSoloAccountNumber() {
    return soloAccountNumber;
  }

  public void setSoloAccountNumber(String soloAccountNumber) {
    this.soloAccountNumber = soloAccountNumber;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }
}


