package com.template;/*
package com.template;

import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;
import net.corda.core.schemas.PersistentStateRef;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoCSchemaV1 extends MappedSchema{

    public LoCSchemaV1() {
        super(LoCSchema.class, 1, ImmutableList.of(PersistentLoC.class));
    }

    @Embeddable
    public static class Comments extends PersistentState {

        private String from;
        private String to;
        private String comment;
        private String date;


        public Comments(String from, String to, String comment, String date) {
            this.from = from;
            this.to = to;
            this.comment = comment;
            this.date = date;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }


    @Entity
    @Table(name="LoCState")
    public static class PersistentLoC extends PersistentState{

        @Column(name = "lcNumber")private String lcNumber;
        @Column(name = "applicant")private String applicant;
        @Column(name = "beneficiary") private String beneficiary;
        @Column(name = "issuingBank") private String issuingBank;
        @Column(name = "advisingBank") private String advisingBank;
        @Column(name = "locID")private UUID locID;
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

        @OneToMany(targetEntity = Comments.class)
        private List<Comments> comments = new ArrayList<Comments>();

        @Column(name = "locStatus")private String locStatus;
        private String participants;

        public PersistentLoC(){

        }
        public PersistentLoC(String lcNumber, String applicant, String beneficiary, String issuingBank, String advisingBank, UUID locID, String applicationCreationDate, String lcType, String applicationRuleCode, String appRuleDesc, String applicantID, String applicantAddress, String currency, double amount, String creditAmountTolerance, String debitAmountTolerance, String issueDate, String expiryDate, String expiryPlace, int presentationDays, String shipmentDate, int shipmentPeriod, String incoTerms, String partialShipment, String transhipmentShipment, String portOfLoading, String portOfDischarge, String placeOfTakingInCharge, String finalDestination, String beneficiaryName, String beneficiaryAddress, String issuingBankName, String issuingBankAddress, String advisingBankName, String advisingBankAddress, String creditAvailableBy, String creditAvailableWith, String bankName, String bankAddress, String ifscCode, String chargesFrom, String chargesDefaultAccount, String waiveCharges, String chargeCodeOne, String chargeDebitAccountNum, String chargeCurrency, double chargeAmount, String commissionCode, String waiveCommissionFlag, String commissionPartyCharge, String commissionCurrency, double commissionAmount, String commissionAccount, String marginRequired, String marginCalcBase, String marginPercentage, String marginDebitAccount, double marginAmount, String marginCreditAccount, String draft, String poPiContractRegNum, double poAmount, String poDate, String descOfGoods, String docsRequired, String additionalConditions, List<Comments> comments, String locStatus, String participants) {

            this.lcNumber = lcNumber;
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
            this.comments = comments;
            this.locStatus = locStatus;
            this.participants = participants;
        }

        public String getLcNumber() {
            return lcNumber;
        }

        public String getApplicant() {
            return applicant;
        }

        public String getBeneficiary() {
            return beneficiary;
        }

        public String getIssuingBank() {
            return issuingBank;
        }

        public String getAdvisingBank() {
            return advisingBank;
        }

        public UUID getLocID() {
            return locID;
        }

        public String getApplicationCreationDate() {
            return applicationCreationDate;
        }

        public String getLcType() {
            return lcType;
        }

        public String getApplicationRuleCode() {
            return applicationRuleCode;
        }

        public String getAppRuleDesc() {
            return appRuleDesc;
        }

        public String getApplicantID() {
            return applicantID;
        }

        public String getApplicantAddress() {
            return applicantAddress;
        }

        public String getCurrency() {
            return currency;
        }

        public double getAmount() {
            return amount;
        }

        public String getCreditAmountTolerance() {
            return creditAmountTolerance;
        }

        public String getDebitAmountTolerance() {
            return debitAmountTolerance;
        }

        public String getIssueDate() {
            return issueDate;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public String getExpiryPlace() {
            return expiryPlace;
        }

        public int getPresentationDays() {
            return presentationDays;
        }

        public String getShipmentDate() {
            return shipmentDate;
        }

        public int getShipmentPeriod() {
            return shipmentPeriod;
        }

        public String getIncoTerms() {
            return incoTerms;
        }

        public String getPartialShipment() {
            return partialShipment;
        }

        public String getTranshipmentShipment() {
            return transhipmentShipment;
        }

        public String getPortOfLoading() {
            return portOfLoading;
        }

        public String getPortOfDischarge() {
            return portOfDischarge;
        }

        public String getPlaceOfTakingInCharge() {
            return placeOfTakingInCharge;
        }

        public String getFinalDestination() {
            return finalDestination;
        }

        public String getBeneficiaryName() {
            return beneficiaryName;
        }

        public String getBeneficiaryAddress() {
            return beneficiaryAddress;
        }

        public String getIssuingBankName() {
            return issuingBankName;
        }

        public String getIssuingBankAddress() {
            return issuingBankAddress;
        }

        public String getAdvisingBankName() {
            return advisingBankName;
        }

        public String getAdvisingBankAddress() {
            return advisingBankAddress;
        }

        public String getCreditAvailableBy() {
            return creditAvailableBy;
        }

        public String getCreditAvailableWith() {
            return creditAvailableWith;
        }

        public String getBankName() {
            return bankName;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public String getIfscCode() {
            return ifscCode;
        }

        public String getChargesFrom() {
            return chargesFrom;
        }

        public String getChargesDefaultAccount() {
            return chargesDefaultAccount;
        }

        public String getWaiveCharges() {
            return waiveCharges;
        }

        public String getChargeCodeOne() {
            return chargeCodeOne;
        }

        public String getChargeDebitAccountNum() {
            return chargeDebitAccountNum;
        }

        public String getChargeCurrency() {
            return chargeCurrency;
        }

        public double getChargeAmount() {
            return chargeAmount;
        }

        public String getCommissionCode() {
            return commissionCode;
        }

        public String getWaiveCommissionFlag() {
            return waiveCommissionFlag;
        }

        public String getCommissionPartyCharge() {
            return commissionPartyCharge;
        }

        public String getCommissionCurrency() {
            return commissionCurrency;
        }

        public double getCommissionAmount() {
            return commissionAmount;
        }

        public String getCommissionAccount() {
            return commissionAccount;
        }

        public String getMarginRequired() {
            return marginRequired;
        }

        public String getMarginCalcBase() {
            return marginCalcBase;
        }

        public String getMarginPercentage() {
            return marginPercentage;
        }

        public String getMarginDebitAccount() {
            return marginDebitAccount;
        }

        public double getMarginAmount() {
            return marginAmount;
        }

        public String getMarginCreditAccount() {
            return marginCreditAccount;
        }

        public String getDraft() {
            return draft;
        }

        public String getPoPiContractRegNum() {
            return poPiContractRegNum;
        }

        public double getPoAmount() {
            return poAmount;
        }

        public String getPoDate() {
            return poDate;
        }

        public String getDescOfGoods() {
            return descOfGoods;
        }

        public String getDocsRequired() {
            return docsRequired;
        }

        public String getAdditionalConditions() {
            return additionalConditions;
        }

        public List<Comments> getComments() {
            return comments;
        }

        public String getLocStatus() {
            return locStatus;
        }

        public String getParticipants() {
            return participants;
        }
    }
}
*/
