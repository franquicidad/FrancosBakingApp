package com.example.mac.francosbakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Process implements Parcelable {

//    "id": 0,
//            "shortDescription": "Recipe Introduction",
//            "description": "Recipe Introduction",
//            "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
//            "thumbnailURL": ""

    private int id;
    private String shortDescription,description,videoURL,thumbnailURL;

    public Process() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(this.shortDescription);
        parcel.writeString(this.description);
        parcel.writeString(this.videoURL);
        parcel.writeString(this.thumbnailURL);

    }

    protected Process(Parcel in){
        this.id=in.readInt();
        this.shortDescription=in.readString();
        this.description=in.readString();
        this.videoURL=in.readString();
        this.thumbnailURL=in.readString();
    }

    public static final Parcelable.Creator<Process> CREATOR=new Parcelable.Creator<Process>(){

        @Override
        public Process createFromParcel(Parcel parcel) {
            return new Process(parcel);
        }

        @Override
        public Process[] newArray(int size) {
            return new Process[size];
        }
    };
}
