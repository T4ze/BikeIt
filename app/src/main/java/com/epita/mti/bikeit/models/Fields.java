
package com.epita.mti.bikeit.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("available_bike_stands")
    @Expose
    private Integer availableBikeStands;
    @SerializedName("available_bikes")
    @Expose
    private Integer availableBikes;
    @SerializedName("banking")
    @Expose
    private String banking;
    @SerializedName("bike_stands")
    @Expose
    private Integer bikeStands;
    @SerializedName("bonus")
    @Expose
    private String bonus;
    @SerializedName("contract_name")
    @Expose
    private String contractName;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("position")
    @Expose
    private List<Double> position = null;
    @SerializedName("status")
    @Expose
    private String status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAvailableBikeStands() {
        return availableBikeStands;
    }

    public void setAvailableBikeStands(Integer availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    public Integer getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(Integer availableBikes) {
        this.availableBikes = availableBikes;
    }

    public String getBanking() {
        return banking;
    }

    public void setBanking(String banking) {
        this.banking = banking;
    }

    public Integer getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(Integer bikeStands) {
        this.bikeStands = bikeStands;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
