package com.vv.domain;

public class TdoVV {
    private String codification;
    private String model;
    private int quantity;
    private String measureUnit;

    public TdoVV(String codification, String model, int quantity, String measureUnit) {
        this.codification = codification;
        this.model = model;
        this.quantity = quantity;
        this.measureUnit = measureUnit;
    }

    public String getCodification() {
        return codification;
    }

    public String getModel() {
        return model;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }
}
