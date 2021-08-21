package com.template.webserver.project;


import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.Party;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="LoCApplication")
public class LoCApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="LOC_SEQ", initialValue = 10001)
    private Integer id;
    private String lcNumber;
    private String applicant;

    private String beneficiary;
    private String issuingBank;
    private String advisingBank;
    private String locID;
    private String applicationCreationDate;
    private String lcType;
    private String applicationRuleCode;
    private String appRuleDesc;
    private String applicantID;
    private String applicantAddress;
    private String currency;
    private double amount;
    private String creditAmountTolerance;
    private String debitAmountTolerance;
    private String issueDate;
    private String expiryDate;
    private String expiryPlace;
    private int presentationDays;
    private String shipmentDate;
    private int shipmentPeriod;
    private String incoTerms;
    private String partialShipment;
    private String transhipmentShipment;
    private String portOfLoading;
    private String portOfDischarge;
    private String placeOfTakingInCharge;
    private String finalDestination;
    //parites
    private String beneficiaryName;
    private String beneficiaryAddress;
    private String issuingBankName;
    private String issuingBankAddress;
    private String advisingBankName;
    private String advisingBankAddress;
    private String creditAvailableBy;
    private String creditAvailableWith;
    private String bankName;
    private String bankAddress;
    private String ifscCode;
    //charges
    private String chargesFrom;
    private String chargesDefaultAccount;
    private String waiveCharges;
    private String chargeCodeOne;
    private String chargeDebitAccountNum;
    private String chargeCurrency;
    private double chargeAmount;
    //commission
    private String commissionCode;
    private String waiveCommissionFlag;
    private String commissionPartyCharge;
    private String commissionCurrency;
    private double commissionAmount;
    private String commissionAccount;
    //margin
    private String marginRequired;
    private String marginCalcBase;
    private String marginPercentage;
    private String marginDebitAccount;
    private double marginAmount;
    private String marginCreditAccount;
    //Documents & Conditions
    private String draft;
    private  String poPiContractRegNum;
    private double poAmount;
    private String poDate;
    private String descOfGoods;
    private String docsRequired;
    private String additionalConditions;

    @ElementCollection
    private List<CommentsEntity> commentsEntities = new ArrayList<CommentsEntity>();
 //   private List attachments;

    private String locStatus;

    public LoCApplicationEntity() {
    }

    public LoCApplicationEntity(Integer id, String lcNumber, String applicant, String beneficiary, String issuingBank, String advisingBank, String locID, String applicationCreationDate, String lcType, String applicationRuleCode, String appRuleDesc, String applicantID, String applicantAddress, String currency, double amount, String creditAmountTolerance, String debitAmountTolerance, String issueDate, String expiryDate, String expiryPlace, int presentationDays, String shipmentDate, int shipmentPeriod, String incoTerms, String partialShipment, String transhipmentShipment, String portOfLoading, String portOfDischarge, String placeOfTakingInCharge, String finalDestination, String beneficiaryName, String beneficiaryAddress, String issuingBankName, String issuingBankAddress, String advisingBankName, String advisingBankAddress, String creditAvailableBy, String creditAvailableWith, String bankName, String bankAddress, String ifscCode, String chargesFrom, String chargesDefaultAccount, String waiveCharges, String chargeCodeOne, String chargeDebitAccountNum, String chargeCurrency, double chargeAmount, String commissionCode, String waiveCommissionFlag, String commissionPartyCharge, String commissionCurrency, double commissionAmount, String commissionAccount, String marginRequired, String marginCalcBase, String marginPercentage, String marginDebitAccount, double marginAmount, String marginCreditAccount, String draft, String poPiContractRegNum, double poAmount, String poDate, String descOfGoods, String docsRequired, String additionalConditions, List<CommentsEntity> commentsEntities, String locStatus) {
        this.id = id;
        this.lcNumber="LCID"+ id;
        this.applicant = applicant;
        this.beneficiary = beneficiary;
        this.issuingBank = issuingBank;
        this.advisingBank = advisingBank;
        this.locID = locID;
        this.applicationCreationDate = applicationCreationDate;
        this.lcType = lcType;
        this.applicationRuleCode = applicationRuleCode;
        this.appRuleDesc = appRuleDesc;
        this.applicantID = applicantID;
        this.applicantAddress = applicantAddress;
        this.currency = currency;
        this.amount = amount;
        this.creditAmountTolerance = creditAmountTolerance;
        this.debitAmountTolerance = debitAmountTolerance;
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
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAddress = beneficiaryAddress;
        this.issuingBankName = issuingBankName;
        this.issuingBankAddress = issuingBankAddress;
        this.advisingBankName = advisingBankName;
        this.advisingBankAddress = advisingBankAddress;
        this.creditAvailableBy = creditAvailableBy;
        this.creditAvailableWith = creditAvailableWith;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.ifscCode = ifscCode;
        this.chargesFrom = chargesFrom;
        this.chargesDefaultAccount = chargesDefaultAccount;
        this.waiveCharges = waiveCharges;
        this.chargeCodeOne = chargeCodeOne;
        this.chargeDebitAccountNum = chargeDebitAccountNum;
        this.chargeCurrency = chargeCurrency;
        this.chargeAmount = chargeAmount;
        this.commissionCode = commissionCode;
        this.waiveCommissionFlag = waiveCommissionFlag;
        this.commissionPartyCharge = commissionPartyCharge;
        this.commissionCurrency = commissionCurrency;
        this.commissionAmount = commissionAmount;
        this.commissionAccount = commissionAccount;
        this.marginRequired = marginRequired;
        this.marginCalcBase = marginCalcBase;
        this.marginPercentage = marginPercentage;
        this.marginDebitAccount = marginDebitAccount;
        this.marginAmount = marginAmount;
        this.marginCreditAccount = marginCreditAccount;
        this.draft = draft;
        this.poPiContractRegNum = poPiContractRegNum;
        this.poAmount = poAmount;
        this.poDate = poDate;
        this.descOfGoods = descOfGoods;
        this.docsRequired = docsRequired;
        this.additionalConditions = additionalConditions;
        this.commentsEntities = commentsEntities;
        this.locStatus = locStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLcNumber() {
        return "LCID"+ this.id;
    }

    public void setLcNumber(String lcNumber) {
        this.lcNumber = "LCID"+this.id;
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

    public String getLocID() {
        return locID;
    }

    public void setLocID(String locID) {
        this.locID = locID;
    }

    public String getApplicationCreationDate() {
        return applicationCreationDate;
    }

    public void setApplicationCreationDate(String applicationCreationDate) {
        this.applicationCreationDate = applicationCreationDate;
    }

    public String getLcType() {
        return lcType;
    }

    public void setLcType(String lcType) {
        this.lcType = lcType;
    }

    public String getApplicationRuleCode() {
        return applicationRuleCode;
    }

    public void setApplicationRuleCode(String applicationRuleCode) {
        this.applicationRuleCode = applicationRuleCode;
    }

    public String getAppRuleDesc() {
        return appRuleDesc;
    }

    public void setAppRuleDesc(String appRuleDesc) {
        this.appRuleDesc = appRuleDesc;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreditAmountTolerance() {
        return creditAmountTolerance;
    }

    public void setCreditAmountTolerance(String creditAmountTolerance) {
        this.creditAmountTolerance = creditAmountTolerance;
    }

    public String getDebitAmountTolerance() {
        return debitAmountTolerance;
    }

    public void setDebitAmountTolerance(String debitAmountTolerance) {
        this.debitAmountTolerance = debitAmountTolerance;
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

    public int getPresentationDays() {
        return presentationDays;
    }

    public void setPresentationDays(int presentationDays) {
        this.presentationDays = presentationDays;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public int getShipmentPeriod() {
        return shipmentPeriod;
    }

    public void setShipmentPeriod(int shipmentPeriod) {
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

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAddress() {
        return beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public String getIssuingBankName() {
        return issuingBankName;
    }

    public void setIssuingBankName(String issuingBankName) {
        this.issuingBankName = issuingBankName;
    }

    public String getIssuingBankAddress() {
        return issuingBankAddress;
    }

    public void setIssuingBankAddress(String issuingBankAddress) {
        this.issuingBankAddress = issuingBankAddress;
    }

    public String getAdvisingBankName() {
        return advisingBankName;
    }

    public void setAdvisingBankName(String advisingBankName) {
        this.advisingBankName = advisingBankName;
    }

    public String getAdvisingBankAddress() {
        return advisingBankAddress;
    }

    public void setAdvisingBankAddress(String advisingBankAddress) {
        this.advisingBankAddress = advisingBankAddress;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getChargesFrom() {
        return chargesFrom;
    }

    public void setChargesFrom(String chargesFrom) {
        this.chargesFrom = chargesFrom;
    }

    public String getChargesDefaultAccount() {
        return chargesDefaultAccount;
    }

    public void setChargesDefaultAccount(String chargesDefaultAccount) {
        this.chargesDefaultAccount = chargesDefaultAccount;
    }

    public String getWaiveCharges() {
        return waiveCharges;
    }

    public void setWaiveCharges(String waiveCharges) {
        this.waiveCharges = waiveCharges;
    }

    public String getChargeCodeOne() {
        return chargeCodeOne;
    }

    public void setChargeCodeOne(String chargeCodeOne) {
        this.chargeCodeOne = chargeCodeOne;
    }

    public String getChargeDebitAccountNum() {
        return chargeDebitAccountNum;
    }

    public void setChargeDebitAccountNum(String chargeDebitAccountNum) {
        this.chargeDebitAccountNum = chargeDebitAccountNum;
    }

    public String getChargeCurrency() {
        return chargeCurrency;
    }

    public void setChargeCurrency(String chargeCurrency) {
        this.chargeCurrency = chargeCurrency;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCommissionCode() {
        return commissionCode;
    }

    public void setCommissionCode(String commissionCode) {
        this.commissionCode = commissionCode;
    }

    public String getWaiveCommissionFlag() {
        return waiveCommissionFlag;
    }

    public void setWaiveCommissionFlag(String waiveCommissionFlag) {
        this.waiveCommissionFlag = waiveCommissionFlag;
    }

    public String getCommissionPartyCharge() {
        return commissionPartyCharge;
    }

    public void setCommissionPartyCharge(String commissionPartyCharge) {
        this.commissionPartyCharge = commissionPartyCharge;
    }

    public String getCommissionCurrency() {
        return commissionCurrency;
    }

    public void setCommissionCurrency(String commissionCurrency) {
        this.commissionCurrency = commissionCurrency;
    }

    public double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getCommissionAccount() {
        return commissionAccount;
    }

    public void setCommissionAccount(String commissionAccount) {
        this.commissionAccount = commissionAccount;
    }

    public String getMarginRequired() {
        return marginRequired;
    }

    public void setMarginRequired(String marginRequired) {
        this.marginRequired = marginRequired;
    }

    public String getMarginCalcBase() {
        return marginCalcBase;
    }

    public void setMarginCalcBase(String marginCalcBase) {
        this.marginCalcBase = marginCalcBase;
    }

    public String getMarginPercentage() {
        return marginPercentage;
    }

    public void setMarginPercentage(String marginPercentage) {
        this.marginPercentage = marginPercentage;
    }

    public String getMarginDebitAccount() {
        return marginDebitAccount;
    }

    public void setMarginDebitAccount(String marginDebitAccount) {
        this.marginDebitAccount = marginDebitAccount;
    }

    public double getMarginAmount() {
        return marginAmount;
    }

    public void setMarginAmount(double marginAmount) {
        this.marginAmount = marginAmount;
    }

    public String getMarginCreditAccount() {
        return marginCreditAccount;
    }

    public void setMarginCreditAccount(String marginCreditAccount) {
        this.marginCreditAccount = marginCreditAccount;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public String getPoPiContractRegNum() {
        return poPiContractRegNum;
    }

    public void setPoPiContractRegNum(String poPiContractRegNum) {
        this.poPiContractRegNum = poPiContractRegNum;
    }

    public double getPoAmount() {
        return poAmount;
    }

    public void setPoAmount(double poAmount) {
        this.poAmount = poAmount;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getDescOfGoods() {
        return descOfGoods;
    }

    public void setDescOfGoods(String descOfGoods) {
        this.descOfGoods = descOfGoods;
    }

    public String getDocsRequired() {
        return docsRequired;
    }

    public void setDocsRequired(String docsRequired) {
        this.docsRequired = docsRequired;
    }

    public String getAdditionalConditions() {
        return additionalConditions;
    }

    public void setAdditionalConditions(String additionalConditions) {
        this.additionalConditions = additionalConditions;
    }

    public List<CommentsEntity> getCommentsEntities() {
        return commentsEntities;
    }

    public void setCommentsEntities(List<CommentsEntity> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }

    public String getLocStatus() {
        return locStatus;
    }

    public void setLocStatus(String locStatus) {
        this.locStatus = locStatus;
    }

    @Override
    public String toString() {
        return "LoCApplicationEntity{" +
                "id=" + id +
                ", lcNumber='" + lcNumber + '\'' +
                ", applicant='" + applicant + '\'' +
                ", beneficiary='" + beneficiary + '\'' +
                ", issuingBank='" + issuingBank + '\'' +
                ", advisingBank='" + advisingBank + '\'' +
                ", locID=" + locID +
                ", applicationCreationDate=" + applicationCreationDate +
                ", lcType='" + lcType + '\'' +
                ", applicationRuleCode='" + applicationRuleCode + '\'' +
                ", appRuleDesc='" + appRuleDesc + '\'' +
                ", applicantID='" + applicantID + '\'' +
                ", applicantAddress='" + applicantAddress + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", creditAmountTolerance='" + creditAmountTolerance + '\'' +
                ", debitAmountTolerance='" + debitAmountTolerance + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", expiryPlace='" + expiryPlace + '\'' +
                ", presentationDays=" + presentationDays +
                ", shipmentDate='" + shipmentDate + '\'' +
                ", shipmentPeriod=" + shipmentPeriod +
                ", incoTerms='" + incoTerms + '\'' +
                ", partialShipment='" + partialShipment + '\'' +
                ", transhipmentShipment='" + transhipmentShipment + '\'' +
                ", portOfLoading='" + portOfLoading + '\'' +
                ", portOfDischarge='" + portOfDischarge + '\'' +
                ", placeOfTakingInCharge='" + placeOfTakingInCharge + '\'' +
                ", finalDestination='" + finalDestination + '\'' +
                ", beneficiaryName='" + beneficiaryName + '\'' +
                ", beneficiaryAddress='" + beneficiaryAddress + '\'' +
                ", issuingBankName='" + issuingBankName + '\'' +
                ", issuingBankAddress='" + issuingBankAddress + '\'' +
                ", advisingBankName='" + advisingBankName + '\'' +
                ", advisingBankAddress='" + advisingBankAddress + '\'' +
                ", creditAvailableBy='" + creditAvailableBy + '\'' +
                ", creditAvailableWith='" + creditAvailableWith + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAddress='" + bankAddress + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", chargesFrom='" + chargesFrom + '\'' +
                ", chargesDefaultAccount='" + chargesDefaultAccount + '\'' +
                ", waiveCharges='" + waiveCharges + '\'' +
                ", chargeCodeOne='" + chargeCodeOne + '\'' +
                ", chargeDebitAccountNum='" + chargeDebitAccountNum + '\'' +
                ", chargeCurrency='" + chargeCurrency + '\'' +
                ", chargeAmount=" + chargeAmount +
                ", commissionCode='" + commissionCode + '\'' +
                ", waiveCommissionFlag='" + waiveCommissionFlag + '\'' +
                ", commissionPartyCharge='" + commissionPartyCharge + '\'' +
                ", commissionCurrency='" + commissionCurrency + '\'' +
                ", commissionAmount=" + commissionAmount +
                ", commissionAccount='" + commissionAccount + '\'' +
                ", marginRequired='" + marginRequired + '\'' +
                ", marginCalcBase='" + marginCalcBase + '\'' +
                ", marginPercentage='" + marginPercentage + '\'' +
                ", marginDebitAccount='" + marginDebitAccount + '\'' +
                ", marginAmount=" + marginAmount +
                ", marginCreditAccount='" + marginCreditAccount + '\'' +
                ", draft='" + draft + '\'' +
                ", poPiContractRegNum='" + poPiContractRegNum + '\'' +
                ", poAmount=" + poAmount +
                ", poDate='" + poDate + '\'' +
                ", descOfGoods='" + descOfGoods + '\'' +
                ", docsRequired='" + docsRequired + '\'' +
                ", additionalConditions='" + additionalConditions + '\'' +
                ", comments='" + commentsEntities + '\'' +
                ", locStatus='" + locStatus + '\'' +
                '}';
    }
}
