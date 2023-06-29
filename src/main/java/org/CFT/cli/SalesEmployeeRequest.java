package org.CFT.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SalesEmployeeRequest {
    private String name;
    private double salary;
    private String national_insurance_number;
    private String bank_account_number;
    private float commission_rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getNational_insurance_number() {
        return national_insurance_number;
    }

    public void setNational_insurance_number(String national_insurance_number) {
        this.national_insurance_number = national_insurance_number;
    }

    public String getBank_account_number() {
        return bank_account_number;
    }

    public void setBank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }

    public float getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(float commission_rate) {
        this.commission_rate = commission_rate;
    }

    @JsonCreator
    public SalesEmployeeRequest(
            @JsonProperty("name") String name,
            @JsonProperty("salary") double salary,
            @JsonProperty("national_insurance_number") String national_insurance_number,
            @JsonProperty("bank_account_number") String bank_account_number,
            @JsonProperty("commission_rate") float commission_rate) {
        this.name = name;
        this.salary = salary;
        this.national_insurance_number = national_insurance_number;
        this.bank_account_number = bank_account_number;
        this.commission_rate = commission_rate;
    }
}
