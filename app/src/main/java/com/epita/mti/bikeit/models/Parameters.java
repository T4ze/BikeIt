
package com.epita.mti.bikeit.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {

    @SerializedName("dataset")
    @Expose
    private List<String> dataset = null;
    @SerializedName("facet")
    @Expose
    private List<String> facet = null;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("rows")
    @Expose
    private Integer rows;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    public List<String> getDataset() {
        return dataset;
    }

    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
    }

    public List<String> getFacet() {
        return facet;
    }

    public void setFacet(List<String> facet) {
        this.facet = facet;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
