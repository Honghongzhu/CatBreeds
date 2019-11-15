package com.example.catbreeds;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CatBreed{

    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String description;

    @SerializedName("weight_imperial")
    private String weight;

//    @Embedded()
//    private Weight weight;

    private String temperament;
    private String origin;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("wikipedia_url")
    private String wikipediaUrl;

    @SerializedName("dog_friendly")
    private int dogFriendly;

    @ColumnInfo(name = "isFavorite")
    private int isFavorite;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public void setDogFriendly(int dogFriendly) {
        this.dogFriendly = dogFriendly;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

//    public void setWeight(Weight weight) {
//        this.weight = weight;
//    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWeight() {
        return weight;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public int getDogFriendly() {
        return dogFriendly;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

//    public Weight getWeight() {
//        return weight;
//    }

//    public static class Weight{
//        @ColumnInfo(name = "weight_imperial")
//        private String imperial_weight;
//        @ColumnInfo(name = "weight_metric")
//        private String metric_weight;
//
//        public Weight(){
//
//        }
//
//        public void setImperial_weight(String imperial_weight) {
//            this.imperial_weight = imperial_weight;
//        }
//
//        public void setMetric_weight(String metric_weight) {
//            this.metric_weight = metric_weight;
//        }
//
//        public String getImperial_weight() {
//            return imperial_weight;
//        }
//
//        public String getMetric_weight() {
//            return metric_weight;
//        }
//    }


}
