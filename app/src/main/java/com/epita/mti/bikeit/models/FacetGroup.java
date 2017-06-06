
package com.epita.mti.bikeit.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacetGroup {

    @SerializedName("facets")
    @Expose
    private List<Facet> facets = null;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
