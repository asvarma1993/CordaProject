package com.template;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

// ************
// * Contract *
// ************
public class LoCContract implements Contract {
    // This is used to identify our contract when building a transaction.
    public static final String ID = "com.template.LoCContract";

    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
    // does not throw an exception.
    @Override
    public void verify(LedgerTransaction tx) {}

    // Used to indicate the transaction's intent.
    public interface Commands extends CommandData {
        class CreateLoC implements Commands {}
        class ApproveLoC implements Commands {}
        class AdviceLoC implements Commands {}
        class AcceptLoC implements Commands {}
        class AlterLoC implements Commands{}
        class AmendLoC implements Commands{}
        class ApplyAlteredLoC implements  Commands{}
        class ApplyAmendedLoC implements  Commands{}
    }

}