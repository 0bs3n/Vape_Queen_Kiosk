package com.thevapequeen.vapequeenkiosk.artesianblends;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class ArtesianBlend implements Parcelable{

    public String vqNumber;
    public String vqName;
    public String vqVGratio;
    public String vqPGratio;
    public String vqDescription;
    public String vqCategory;

    public ArtesianBlend(Parcel source){
        vqNumber = source.readString();
        vqName = source.readString();
        vqVGratio = source.readString();
        vqPGratio = source.readString();
        vqDescription = source.readString();
        vqCategory = source.readString();
    }

    public ArtesianBlend(String vqnumber, String vqname, String vqvg, String vqpg,
                         String vqscript, String vqcategory) {
        this.vqNumber = vqnumber;
        this.vqName = vqname;
        this.vqVGratio = vqvg;
        this.vqPGratio = vqpg;
        this.vqDescription = vqscript;
        this.vqCategory = vqcategory;
    }

    public int describeContents(){
        return this.hashCode();
    }

    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(vqNumber);
        dest.writeString(vqName);
        dest.writeString(vqVGratio);
        dest.writeString(vqPGratio);
        dest.writeString(vqDescription);
        dest.writeString(vqCategory);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public ArtesianBlend createFromParcel(Parcel in){
            return new ArtesianBlend(in);
        }
        public ArtesianBlend[] newArray(int size){
            return new ArtesianBlend[size];
        }
    };

    public String getVqNumber() {
        return vqNumber;
    }

    public void setVqNumber(String idnumber) {
        this.vqNumber = idnumber;
    }

    public String getVqName() {
        return vqName;
    }

    public void setVqName(String name) {
        this.vqName = name;
    }

    public String getVqVGratio() {
        return vqVGratio;
    }

    public void setVqVGratio(String vgratio) {
        this.vqVGratio = vgratio;
    }

    public String getVqPGratio() {
        return vqPGratio;
    }

    public void setVqPGratio(String pgratio) {
        this.vqPGratio = pgratio;
    }

    public String getVqDescription() {
        return vqDescription;
    }

    public void setVqDescription(String description) {
        this.vqDescription = description;
    }

    public String getVqCategory() {
        return vqCategory;
    }

    public void setVqCategory(String category) {
        this.vqCategory = category;
    }


}
