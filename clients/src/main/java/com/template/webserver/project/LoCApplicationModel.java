package com.template.webserver.project;

import java.util.Date;

public class LoCApplicationModel{
    private String applicant;
    private String beneficiary;
    private String issuingBank;
    private String advisingBank;
    private Date applicationDate;
    private double amount;
    private String locStatus;
    private String currency;
    private String debitAmountTolerance;
    private String creditAmountTolerance;
    private String issueDate;
    private String expiryDate;
    private String expiryPlace;
    private String presentationDays;
    private String shipmentDate;
    private String shipmentPeriod;
    private String incoTerms;
    private String partialShipment;
    private String transhipmentShipment;
    private String portOfLoading;
    private String portOfDischarge;
    private String placeOfTakingInCharge;
    private String finalDestination;
    private String creditAvailableBy;
    private String creditAvailableWith;

    public LoCApplicationModel() {
    }

    public LoCApplicationModel(String applicant, String beneficiary, String issuingBank, String advisingBank, Date applicationDate, int amount, String locStatus, String currency, String debitAmountTolerance, String creditAmountTolerance, String issueDate, String expiryDate, String expiryPlace, String presentationDays, String shipmentDate, String shipmentPeriod, String incoTerms, String partialShipment, String transhipmentShipment, String portOfLoading, String portOfDischarge, String placeOfTakingInCharge, String finalDestination, String creditAvailableBy, String creditAvailableWith) {
        this.applicant = applicant;
        this.beneficiary = beneficiary;
        this.issuingBank = issuingBank;
        this.advisingBank = advisingBank;
        this.applicationDate = applicationDate;
        this.amount = amount;
        this.locStatus = locStatus;
        this.currency = currency;
        this.debitAmountTolerance = debitAmountTolerance;
        this.creditAmountTolerance = creditAmountTolerance;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.expiryPlace = expiryPlace;
        this.presentationDays = presentationDays;
        this.shipmentDate = shipmentDate;
        this.shipmentPeriod = shipmentPeriod;
        this.incoTerms = incoTerms;
        this.partialShipment = partialShipment;
        this.transhipmentShipment = transhipmentShipment;
        this.portOfLoading = portOfLoading;
        this.portOfDischarge = portOfDischarge;
        this.placeOfTakingInCharge = placeOfTakingInCharge;
        this.finalDestination = finalDestination;
        this.creditAvailableBy = creditAvailableBy;
        this.creditAvailableWith = creditAvailableWith;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getAdvisingBank() {
        return advisingBank;
    }

    public void setAdvisingBank(String advisingBank) {
        this.advisingBank = advisingBank;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDebitAmountTolerance() {
        return debitAmountTolerance;
    }

    public void setDebitAmountTolerance(String debitAmountTolerance) {
        this.debitAmountTolerance = debitAmountTolerance;
    }

    public String getCreditAmountTolerance() {
        return creditAmountTolerance;
    }

    public void setCreditAmountTolerance(String creditAmountTolerance) {
        this.creditAmountTolerance = creditAmountTolerance;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryPlace() {
        return expiryPlace;
    }

    public void setExpiryPlace(String expiryPlace) {
        this.expiryPlace = expiryPlace;
    }

    public String getPresentationDays() {
        return presentationDays;
    }

    public void setPresentationDays(String presentationDays) {
        this.presentationDays = presentationDays;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShipmentPeriod() {
        return shipmentPeriod;
    }

    public void setShipmentPeriod(String shipmentPeriod) {
        this.shipmentPeriod = shipmentPeriod;
    }

    public String getIncoTerms() {
        return incoTerms;
    }

    public void setIncoTerms(String incoTerms) {
        this.incoTerms = incoTerms;
    }

    public String getPartialShipment() {
        return partialShipment;
    }

    public void setPartialShipment(String partialShipment) {
        this.partialShipment = partialShipment;
    }

    public String getTranshipmentShipment() {
        return transhipmentShipment;
    }

    public void setTranshipmentShipment(String transhipmentShipment) {
        this.transhipmentShipment = transhipmentShipment;
    }

    public String getPortOfLoading() {
        return portOfLoading;
    }

    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

    public String getPlaceOfTakingInCharge() {
        return placeOfTakingInCharge;
    }

    public void setPlaceOfTakingInCharge(String placeOfTakingInCharge) {
        this.placeOfTakingInCharge = placeOfTakingInCharge;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    public String getCreditAvailableBy() {
        return creditAvailableBy;
    }

    public void setCreditAvailableBy(String creditAvailableBy) {
        this.creditAvailableBy = creditAvailableBy;
    }

    public String getCreditAvailableWith() {
        return creditAvailableWith;
    }

    public void setCreditAvailableWith(String creditAvailableWith) {
        this.creditAvailableWith = creditAvailableWith;
    }
}