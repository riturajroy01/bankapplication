package org.banking.bankaccount.domain;


import java.math.BigDecimal;


public class CreateAccountRequest {

    String customerID;

    BigDecimal initialCredit;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(BigDecimal initialCredit) {
        this.initialCredit = initialCredit;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "customerID='" + customerID + '\'' +
                ", initialCredit=" + initialCredit +
                '}';
    }
}
