package com.template;

import kotlin.jvm.internal.Intrinsics;
import net.corda.core.node.ServiceHub;
import net.corda.core.node.services.CordaService;
import net.corda.core.serialization.SingletonSerializeAsToken;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

@CordaService
public class IDGenerator extends SingletonSerializeAsToken{
    private final AtomicInteger counter;
    private final ServiceHub services;
    public final int getLCId(){
        return this.counter.incrementAndGet();
    }
    public final ServiceHub getServices() {

        return this.services;
    }

    public IDGenerator(@NotNull ServiceHub services) {
      //  Intrinsics.checkParameterIsNotNull(services, "services");
        this.services = services;
        this.counter = new AtomicInteger(0);
    }
}
