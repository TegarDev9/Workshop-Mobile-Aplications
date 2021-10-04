package com.tegar.recycleviewdatamovies;

import android.os.Parcel;
import android.os.Parcelable;

/**implement Parcelable,sebelum mengimplemet install plugin Parselab;e dulu*/
public class Movies implements Parcelable {
    /**Mebuat data2 dengan string dan double*/
    private String title;
    private String genre;
    private String director;
    private double rating;
    private String description;
    private String releasedate;

/**membuat Consruktor dan getter setter*/
    public Movies(String title, String genre, String director, double rating, String description, String releasedate) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.rating = rating;
        this.description = description;
        this.releasedate = releasedate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.director);
        dest.writeString(this.genre);
        dest.writeDouble(this.rating);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.releasedate);
    }

    public void readFromParcel(Parcel source) {
        this.director = source.readString();
        this.genre = source.readString();
        this.rating = source.readDouble();
        this.title = source.readString();
        this.description = source.readString();
        this.releasedate = source.readString();
    }

    protected Movies(Parcel in) {
        this.director = in.readString();
        this.genre = in.readString();
        this.rating = in.readDouble();
        this.title = in.readString();
        this.description = in.readString();
        this.releasedate = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
