package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {
    private float amount;

    // public Balance() {
    // }

    // public Balance(float amount) {
    //     this.amount = amount;
    // }

    // public float getAmount() {
    //     return amount;
    // }

    // public void setAmount(float amount) {
    //     this.amount = amount;
    // }

    @Override
    public String toString() {
        return "Balance {amount=" + amount + "}";
    }
}
