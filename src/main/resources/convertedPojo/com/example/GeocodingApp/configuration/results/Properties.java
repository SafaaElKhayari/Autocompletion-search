
package com.example.GeocodingApp.configuration.results;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Nom",
    "Nature",
    "Indice",
    "Comm_arr",
    "Observatio",
    "SURFACE",
    "AFFECTATIO",
    "couleur",
    "Shape_Leng",
    "Shape_Area"
})
@Generated("jsonschema2pojo")
public class Properties {

    @JsonProperty("Nom")
    private String nom;
    @JsonProperty("Nature")
    private String nature;
    @JsonProperty("Indice")
    private String indice;
    @JsonProperty("Comm_arr")
    private String commArr;
    @JsonProperty("Observatio")
    private String observatio;
    @JsonProperty("SURFACE")
    private Double surface;
    @JsonProperty("AFFECTATIO")
    private Object affectatio;
    @JsonProperty("couleur")
    private Object couleur;
    @JsonProperty("Shape_Leng")
    private Double shapeLeng;
    @JsonProperty("Shape_Area")
    private Double shapeArea;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("Nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Properties withNom(String nom) {
        this.nom = nom;
        return this;
    }

    @JsonProperty("Nature")
    public String getNature() {
        return nature;
    }

    @JsonProperty("Nature")
    public void setNature(String nature) {
        this.nature = nature;
    }

    public Properties withNature(String nature) {
        this.nature = nature;
        return this;
    }

    @JsonProperty("Indice")
    public String getIndice() {
        return indice;
    }

    @JsonProperty("Indice")
    public void setIndice(String indice) {
        this.indice = indice;
    }

    public Properties withIndice(String indice) {
        this.indice = indice;
        return this;
    }

    @JsonProperty("Comm_arr")
    public String getCommArr() {
        return commArr;
    }

    @JsonProperty("Comm_arr")
    public void setCommArr(String commArr) {
        this.commArr = commArr;
    }

    public Properties withCommArr(String commArr) {
        this.commArr = commArr;
        return this;
    }

    @JsonProperty("Observatio")
    public String getObservatio() {
        return observatio;
    }

    @JsonProperty("Observatio")
    public void setObservatio(String observatio) {
        this.observatio = observatio;
    }

    public Properties withObservatio(String observatio) {
        this.observatio = observatio;
        return this;
    }

    @JsonProperty("SURFACE")
    public Double getSurface() {
        return surface;
    }

    @JsonProperty("SURFACE")
    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Properties withSurface(Double surface) {
        this.surface = surface;
        return this;
    }

    @JsonProperty("AFFECTATIO")
    public Object getAffectatio() {
        return affectatio;
    }

    @JsonProperty("AFFECTATIO")
    public void setAffectatio(Object affectatio) {
        this.affectatio = affectatio;
    }

    public Properties withAffectatio(Object affectatio) {
        this.affectatio = affectatio;
        return this;
    }

    @JsonProperty("couleur")
    public Object getCouleur() {
        return couleur;
    }

    @JsonProperty("couleur")
    public void setCouleur(Object couleur) {
        this.couleur = couleur;
    }

    public Properties withCouleur(Object couleur) {
        this.couleur = couleur;
        return this;
    }

    @JsonProperty("Shape_Leng")
    public Double getShapeLeng() {
        return shapeLeng;
    }

    @JsonProperty("Shape_Leng")
    public void setShapeLeng(Double shapeLeng) {
        this.shapeLeng = shapeLeng;
    }

    public Properties withShapeLeng(Double shapeLeng) {
        this.shapeLeng = shapeLeng;
        return this;
    }

    @JsonProperty("Shape_Area")
    public Double getShapeArea() {
        return shapeArea;
    }

    @JsonProperty("Shape_Area")
    public void setShapeArea(Double shapeArea) {
        this.shapeArea = shapeArea;
    }

    public Properties withShapeArea(Double shapeArea) {
        this.shapeArea = shapeArea;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Properties withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Properties.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nom");
        sb.append('=');
        sb.append(((this.nom == null)?"<null>":this.nom));
        sb.append(',');
        sb.append("nature");
        sb.append('=');
        sb.append(((this.nature == null)?"<null>":this.nature));
        sb.append(',');
        sb.append("indice");
        sb.append('=');
        sb.append(((this.indice == null)?"<null>":this.indice));
        sb.append(',');
        sb.append("commArr");
        sb.append('=');
        sb.append(((this.commArr == null)?"<null>":this.commArr));
        sb.append(',');
        sb.append("observatio");
        sb.append('=');
        sb.append(((this.observatio == null)?"<null>":this.observatio));
        sb.append(',');
        sb.append("surface");
        sb.append('=');
        sb.append(((this.surface == null)?"<null>":this.surface));
        sb.append(',');
        sb.append("affectatio");
        sb.append('=');
        sb.append(((this.affectatio == null)?"<null>":this.affectatio));
        sb.append(',');
        sb.append("couleur");
        sb.append('=');
        sb.append(((this.couleur == null)?"<null>":this.couleur));
        sb.append(',');
        sb.append("shapeLeng");
        sb.append('=');
        sb.append(((this.shapeLeng == null)?"<null>":this.shapeLeng));
        sb.append(',');
        sb.append("shapeArea");
        sb.append('=');
        sb.append(((this.shapeArea == null)?"<null>":this.shapeArea));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.commArr == null)? 0 :this.commArr.hashCode()));
        result = ((result* 31)+((this.surface == null)? 0 :this.surface.hashCode()));
        result = ((result* 31)+((this.nature == null)? 0 :this.nature.hashCode()));
        result = ((result* 31)+((this.indice == null)? 0 :this.indice.hashCode()));
        result = ((result* 31)+((this.shapeLeng == null)? 0 :this.shapeLeng.hashCode()));
        result = ((result* 31)+((this.couleur == null)? 0 :this.couleur.hashCode()));
        result = ((result* 31)+((this.affectatio == null)? 0 :this.affectatio.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.nom == null)? 0 :this.nom.hashCode()));
        result = ((result* 31)+((this.observatio == null)? 0 :this.observatio.hashCode()));
        result = ((result* 31)+((this.shapeArea == null)? 0 :this.shapeArea.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Properties) == false) {
            return false;
        }
        Properties rhs = ((Properties) other);
        return ((((((((((((this.commArr == rhs.commArr)||((this.commArr!= null)&&this.commArr.equals(rhs.commArr)))&&((this.surface == rhs.surface)||((this.surface!= null)&&this.surface.equals(rhs.surface))))&&((this.nature == rhs.nature)||((this.nature!= null)&&this.nature.equals(rhs.nature))))&&((this.indice == rhs.indice)||((this.indice!= null)&&this.indice.equals(rhs.indice))))&&((this.shapeLeng == rhs.shapeLeng)||((this.shapeLeng!= null)&&this.shapeLeng.equals(rhs.shapeLeng))))&&((this.couleur == rhs.couleur)||((this.couleur!= null)&&this.couleur.equals(rhs.couleur))))&&((this.affectatio == rhs.affectatio)||((this.affectatio!= null)&&this.affectatio.equals(rhs.affectatio))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nom == rhs.nom)||((this.nom!= null)&&this.nom.equals(rhs.nom))))&&((this.observatio == rhs.observatio)||((this.observatio!= null)&&this.observatio.equals(rhs.observatio))))&&((this.shapeArea == rhs.shapeArea)||((this.shapeArea!= null)&&this.shapeArea.equals(rhs.shapeArea))));
    }

}
