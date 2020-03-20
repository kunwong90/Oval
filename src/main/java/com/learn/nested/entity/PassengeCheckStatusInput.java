package com.learn.nested.entity;

import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotNull;

import java.util.List;

public class PassengeCheckStatusInput {

    @AssertValid
    @NotNull
    private List<Passenger> passengers;

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PassengeCheckStatusInput{");
        sb.append("passengers=").append(passengers);
        sb.append('}');
        return sb.toString();
    }
}
