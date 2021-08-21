package com.template;/*
package com.template;

import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LoCState implements ContractState {
    private Party applicant;
    private Party beneficiary;
    private Party issuingBank;
    private Party advisingBank;
    private UniqueIdentifier locID;
    private Date applicationDate;
    private double amount;
    private String locStatus;

    public LoCState() {
    }

    public LoCState(Party applicant, Party beneficiary, Party issuingBank, Party advisingBank, UniqueIdentifier locID, Date applicationDate, double amount, String locStatus) {
        this.applicant = applicant;
        this.beneficiary = beneficiary;
        this.issuingBank = issuingBank;
        this.advisingBank = advisingBank;
        this.locID = locID;
        this.applicationDate = applicationDate;
        this.amount = amount;
        this.locStatus = locStatus;
    }

    public Party getApplicant() {
        return applicant;
    }

    public void setApplicant(Party applicant) {
        this.applicant = applicant;
    }

    public Party getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Party beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Party getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(Party issuingBank) {
        this.issuingBank = issuingBank;
    }

    public Party getAdvisingBank() {
        return advisingBank;
    }

    public void setAdvisingBank(Party advisingBank) {
        this.advisingBank = advisingBank;
    }

    public UniqueIdentifier getLocID() {
        return locID;
    }

    public void setLocID(UniqueIdentifier locID) {
        this.locID = locID;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLocStatus() {
        return locStatus;
    }

    public void setLocStatus(String locStatus) {
        this.locStatus = locStatus;
    }


    public List<AbstractParty> getParticipants() {
        return Arrays.asList(applicant, issuingBank, advisingBank,beneficiary);
    }
}
*/
