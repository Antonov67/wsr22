package com.example.wsr22_clock;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Film {
    @SerializedName("movieId")
    @Expose
    public String movieId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("age")
    @Expose
    public String age;
    @SerializedName("images")
    @Expose
    public List<String> images = null;
    @SerializedName("poster")
    @Expose
    public String poster;
    @SerializedName("tags")
    @Expose
    public List<Tag> tags = null;

}

class Tag {

    @SerializedName("idTags")
    @Expose
    public String idTags;
    @SerializedName("tagName")
    @Expose
    public String tagName;

}