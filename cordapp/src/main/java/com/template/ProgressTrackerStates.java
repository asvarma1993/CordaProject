package com.template;

import net.corda.core.utilities.ProgressTracker;

public class ProgressTrackerStates {
    public static final ProgressTracker.Step GETTING_NOTARY = new ProgressTracker.Step("Getting Notary");
    public static final ProgressTracker.Step GETTING_COUNTERPARTIES = new ProgressTracker.Step("Getting Counter parties");
    public static final ProgressTracker.Step GENERATING_TRANSACTION = new ProgressTracker.Step("Generating Transaction");
    public static final ProgressTracker.Step VERIFYING_TRANSACTION = new ProgressTracker.Step("Verifying Transaction");
    public static final ProgressTracker.Step SIGNING_TRANSACTION = new ProgressTracker.Step("Signing Transaction");
    public static final ProgressTracker.Step FINALISING_TRANSACTION = new ProgressTracker.Step("Finalizing Transaction");
    public static final ProgressTracker.Step GETTING_PARTYONE = new ProgressTracker.Step("GETTING_PARTYONE");
    public static final ProgressTracker.Step GETTING_BANKA = new ProgressTracker.Step("GETTING_BANKA");
    public static final ProgressTracker.Step GETTING_BANKB = new ProgressTracker.Step("GETTING_BANKB");
}
