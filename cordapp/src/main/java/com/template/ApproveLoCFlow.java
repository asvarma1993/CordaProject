package com.template;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.Command;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.crypto.SecureHash;
import net.corda.core.flows.*;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.template.ProgressTrackerStates.*;

@InitiatingFlow
@StartableByRPC
public class ApproveLoCFlow extends FlowLogic<String> {

    private final static Logger logger = LoggerFactory.getLogger(ApproveLoCFlow.class);

    private String locID;
    private String lcNumber;
    private Party applicant;
    private Party beneficiary;
    private Party issuingBank;
    private Party advisingBank;
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
    private CommentsState comments;
 //   private List attachments;


    public ApproveLoCFlow(String locID, String lcNumber, Party applicant, Party beneficiary, Party issuingBank, Party advisingBank,
                          String lcType, String applicationRuleCode, String appRuleDesc, String applicantID,
                          String applicantAddress, String currency, double amount, String creditAmountTolerance, String debitAmountTolerance,
                          String issueDate, String expiryDate, String expiryPlace, int presentationDays, String shipmentDate, int shipmentPeriod,
                          String incoTerms, String partialShipment, String transhipmentShipment, String portOfLoading, String portOfDischarge,
                          String placeOfTakingInCharge, String finalDestination, String beneficiaryName, String beneficiaryAddress,
                          String issuingBankName, String issuingBankAddress, String advisingBankName, String advisingBankAddress,
                          String creditAvailableBy, String creditAvailableWith, String bankName, String bankAddress, String ifscCode,
                          String chargesFrom, String chargesDefaultAccount, String waiveCharges, String chargeCodeOne,
                          String chargeDebitAccountNum, String chargeCurrency, double chargeAmount, String commissionCode,
                          String waiveCommissionFlag, String commissionPartyCharge, String commissionCurrency, double commissionAmount,
                          String commissionAccount, String marginRequired, String marginCalcBase, String marginPercentage, String marginDebitAccount,
                          double marginAmount, String marginCreditAccount, String draft, String poPiContractRegNum, double poAmount,
                          String poDate, String descOfGoods, String docsRequired, String additionalConditions, CommentsState comments) {
        this.locID = locID;
        this.lcNumber = lcNumber;
        this.applicant = applicant;
        this.beneficiary = beneficiary;
        this.issuingBank = issuingBank;
        this.advisingBank = advisingBank;
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
    }

    private final ProgressTracker progressTracker = new ProgressTracker(GETTING_NOTARY, GETTING_COUNTERPARTIES,GENERATING_TRANSACTION, VERIFYING_TRANSACTION, SIGNING_TRANSACTION, FINALISING_TRANSACTION, GETTING_PARTYONE, GETTING_BANKA, GETTING_BANKB);

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    @Suspendable
    public String call() throws FlowException {

        List<StateAndRef<LoCApplicationState>> applications = getServiceHub().getVaultService().queryBy(LoCApplicationState.class).getStates();
        List<StateAndRef<LoCApplicationState>> application=applications.stream()
                .filter(app -> locID.equals(app.getState().getData().getLocID().getId().toString())).collect(Collectors.toList());
        if(application.size()==1){
            logger.info("Getting Notaries");
            progressTracker.setCurrentStep(GETTING_NOTARY);
            final Party notary=getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);
            LoCApplicationState locApplicationState=application.get(0).getState().getData();
            logger.info("LoCState from vault : "+ locApplicationState.toString());
            logger.info("Making command");
            Command command= new Command(new LoCContract.Commands.ApproveLoC(),getOurIdentity().getOwningKey());
         //   LoCState state= new LoCState(getOurIdentity(), locApplication.getBeneficiary(), locApplication.getIssuingBank(), locApplication.getAdvisingBank(), locApplication.getLocID(), locApplication.getApplicationDate(),locApplication.getAmount(), "Approved");
            //List<CommentsState> updateComments = locApplicationState.getComments();
           /* if(null!=comments){
                updateComments.add(comments);
            }*/
            List<AbstractParty> participants= Arrays.asList(getOurIdentity(), locApplicationState.getApplicant(), locApplicationState.getAdvisingBank());
            LoCApplicationState state= new LoCApplicationState(lcNumber, locApplicationState.getApplicant(), locApplicationState.getBeneficiary(),
                    locApplicationState.getIssuingBank(), locApplicationState.getAdvisingBank(),
                    locApplicationState.getLocID(), locApplicationState.getApplicationCreationDate(),
                    lcType, applicationRuleCode, appRuleDesc, applicantID, applicantAddress,
                    currency, amount, creditAmountTolerance, debitAmountTolerance,
                    issueDate, expiryDate, expiryPlace, presentationDays, shipmentDate, shipmentPeriod, incoTerms, partialShipment,
                    transhipmentShipment, portOfLoading, portOfDischarge, placeOfTakingInCharge, finalDestination, beneficiaryName, beneficiaryAddress,
                    issuingBankName, issuingBankAddress, advisingBankName, advisingBankAddress, creditAvailableBy,
                    creditAvailableWith, bankName, bankAddress, ifscCode, chargesFrom, chargesDefaultAccount, waiveCharges, chargeCodeOne,
                    chargeDebitAccountNum, chargeCurrency, chargeAmount, commissionCode, waiveCommissionFlag, commissionPartyCharge, commissionCurrency,
                    commissionAmount, commissionAccount, marginRequired, marginCalcBase, marginPercentage, marginDebitAccount, marginAmount,
                    marginCreditAccount, draft, poPiContractRegNum, poAmount, poDate, descOfGoods, docsRequired, additionalConditions,  locApplicationState.getComments(),
                    "APPROVED", locApplicationState.getAmendmentNumber(), participants);

            logger.info("Building transaction");
            TransactionBuilder txBuilder= new TransactionBuilder(notary)
                    .addInputState(application.get(0))
                    .addOutputState(state, LoCContract.ID)
                    .addCommand(command);
         //    Instant currentTime = getServiceHub().getClock().instant();
         //   txBuilder.setTimeWindow(currentTime, Duration.ofSeconds(30));

            logger.info("Verifying transaction");
            progressTracker.setCurrentStep(VERIFYING_TRANSACTION);
            txBuilder.verify(getServiceHub());

            logger.info("Signing transaction");
            progressTracker.setCurrentStep(SIGNING_TRANSACTION);
            SignedTransaction stx= getServiceHub().signInitialTransaction(txBuilder);

            logger.info("Sending transaction");
            progressTracker.setCurrentStep(FINALISING_TRANSACTION);
            SecureHash hash=subFlow(new FinalityFlow(stx)).getId();
            return hash.toString();

        }else{
            return "No data found!";
        }

    }
}
