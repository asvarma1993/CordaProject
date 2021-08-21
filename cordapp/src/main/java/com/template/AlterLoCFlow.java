package com.template;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.Command;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.crypto.SecureHash;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@InitiatingFlow
@StartableByRPC
public class AlterLoCFlow extends FlowLogic<String> {

    private final static Logger logger = LoggerFactory.getLogger(AlterLoCFlow.class);
    private String locID;
    private String partyFrom;
    private String partyTo;
    private String comment;
    private String date;
  //  private LoCApplicationState locApplication;
   // private List<AbstractParty> parties;


    public AlterLoCFlow(String locID, String partyFrom, String partyTo, String comment, String date) {
        this.locID = locID;
        this.partyFrom = partyFrom;
        this.partyTo = partyTo;
        this.comment = comment;
        this.date = date;
    }

    private final ProgressTracker progressTracker = new ProgressTracker(ProgressTrackerStates.GETTING_NOTARY, ProgressTrackerStates.GETTING_COUNTERPARTIES, ProgressTrackerStates.GENERATING_TRANSACTION, ProgressTrackerStates.VERIFYING_TRANSACTION, ProgressTrackerStates.SIGNING_TRANSACTION, ProgressTrackerStates.FINALISING_TRANSACTION, ProgressTrackerStates.GETTING_PARTYONE, ProgressTrackerStates.GETTING_BANKA, ProgressTrackerStates.GETTING_BANKB);

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    @Suspendable
    public String call() throws FlowException {

        List<StateAndRef<LoCApplicationState>> applications = getServiceHub().getVaultService().queryBy(LoCApplicationState.class).getStates();
        List<StateAndRef<LoCApplicationState>> application = applications.stream()
                .filter(app -> locID.equals(app.getState().getData().getLocID().getId().toString())).collect(Collectors.toList());
        if (application.size() == 1) {
            logger.info("Getting Notaries");
            progressTracker.setCurrentStep(ProgressTrackerStates.GETTING_NOTARY);
            final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);
                LoCApplicationState locApplication=application.get(0).getState().getData();
            logger.info("Making command");

            Command command = new Command(new LoCContract.Commands.AlterLoC(), getOurIdentity().getOwningKey());
            CommentsState comments= new CommentsState(partyFrom, partyTo, comment, date);
            logger.info("comments "+comments.toString());
            List<CommentsState> updateComments = new ArrayList<CommentsState>();
            updateComments.addAll(locApplication.getComments());
            logger.info("update comments 1"+updateComments);
            updateComments.add(comments);
            logger.info("update comments "+updateComments);
            //   LoCState state= new LoCState(getOurIdentity(), locApplication.getBeneficiary(), locApplication.getIssuingBank(), locApplication.getAdvisingBank(), locApplication.getLocID(), locApplication.getApplicationDate(),locApplication.getAmount(), "Approved");
            LoCApplicationState state = new LoCApplicationState(locApplication.getLcNumber(), locApplication.getApplicant(), locApplication.getBeneficiary(),
                    locApplication.getIssuingBank(), locApplication.getAdvisingBank(),
                    locApplication.getLocID(), locApplication.getApplicationCreationDate(),
                    locApplication.getLcType(), locApplication.getApplicationRuleCode(), locApplication.getAppRuleDesc(), locApplication.getApplicantID(),
                    locApplication.getApplicantAddress(),
                    locApplication.getCurrency(), locApplication.getAmount(), locApplication.getCreditAmountTolerance(), locApplication.getDebitAmountTolerance(),
                    locApplication.getIssueDate(), locApplication.getExpiryDate(), locApplication.getExpiryPlace(),
                    locApplication.getPresentationDays(), locApplication.getShipmentDate(), locApplication.getShipmentPeriod(),
                    locApplication.getIncoTerms(), locApplication.getPartialShipment(),
                    locApplication.getTranshipmentShipment(), locApplication.getPortOfLoading(), locApplication.getPortOfDischarge(),
                    locApplication.getPlaceOfTakingInCharge(), locApplication.getFinalDestination(), locApplication.getBeneficiaryName(),
                    locApplication.getBeneficiaryAddress(),
                    locApplication.getIssuingBankName(), locApplication.getIssuingBankAddress(), locApplication.getAdvisingBankName(),
                    locApplication.getAdvisingBankAddress(), locApplication.getCreditAvailableBy(),
                    locApplication.getCreditAvailableWith(), locApplication.getBankName(), locApplication.getBankAddress(),
                    locApplication.getIfscCode(), locApplication.getChargesFrom(), locApplication.getChargesDefaultAccount(), locApplication.getWaiveCharges(),
                    locApplication.getChargeCodeOne(),
                    locApplication.getChargeDebitAccountNum(), locApplication.getChargeCurrency(), locApplication.getChargeAmount(),
                    locApplication.getCommissionCode(), locApplication.getWaiveCommissionFlag(), locApplication.getCommissionPartyCharge(),
                    locApplication.getCommissionCurrency(),
                    locApplication.getCommissionAmount(), locApplication.getCommissionAccount(), locApplication.getMarginRequired(),
                    locApplication.getMarginCalcBase(), locApplication.getMarginPercentage(), locApplication.getMarginDebitAccount(),
                    locApplication.getMarginAmount(),
                    locApplication.getMarginCreditAccount(), locApplication.getDraft(), locApplication.getPoPiContractRegNum(),
                    locApplication.getPoAmount(), locApplication.getPoDate(), locApplication.getDescOfGoods(),
                    locApplication.getDocsRequired(), locApplication.getAdditionalConditions(), updateComments, "ALTERED", locApplication.getAmendmentNumber(),
                    Arrays.asList(getOurIdentity(), locApplication.getApplicant()));
            logger.info("Building transaction");
            TransactionBuilder txBuilder = new TransactionBuilder(notary)
                    .addInputState(application.get(0))
                    .addOutputState(state, LoCContract.ID)
                    .addCommand(command);
         //   Instant currentTime = getServiceHub().getClock().instant();
         //   txBuilder.setTimeWindow(currentTime, Duration.ofSeconds(30));
            logger.info("Verifying transaction");
            progressTracker.setCurrentStep(ProgressTrackerStates.VERIFYING_TRANSACTION);
            txBuilder.verify(getServiceHub());
            logger.info("Signing transaction");
            progressTracker.setCurrentStep(ProgressTrackerStates.SIGNING_TRANSACTION);
            SignedTransaction stx = getServiceHub().signInitialTransaction(txBuilder);
            logger.info("Sending transaction");
            progressTracker.setCurrentStep(ProgressTrackerStates.FINALISING_TRANSACTION);
            SecureHash hash = subFlow(new FinalityFlow(stx)).getId();
            return hash.toString();

        } else {
            logger.info("No data found for : "+ locID);
            return "No data found!";
        }

    }
}
