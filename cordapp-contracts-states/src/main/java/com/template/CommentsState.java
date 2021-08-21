package com.template;
import net.corda.core.schemas.QueryableState;
import net.corda.core.serialization.CordaSerializable;

import javax.persistence.Embeddable;

@CordaSerializable
public class CommentsState {
    private String partyFrom;
    private String partyTo;
    private String comment;
    private String date;

    public CommentsState(String partyFrom, String partyTo, String comment, String date) {
        this.partyFrom = partyFrom;
        this.partyTo = partyTo;
        this.comment = comment;
        this.date = date;
    }

    public String getPartyFrom() {
        return partyFrom;
    }

    public void setPartyFrom(String partyFrom) {
        this.partyFrom = partyFrom;
    }

    public String getPartyTo() {
        return partyTo;
    }

    public void setPartyTo(String partyTo) {
        this.partyTo = partyTo;
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

    @Override
    public String toString() {
        return "CommentsState{" +
                "partyFrom='" + partyFrom + '\'' +
                ", partyTo='" + partyTo + '\'' +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
