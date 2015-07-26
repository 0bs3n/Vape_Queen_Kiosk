package com.thevapequeen.vapequeenkiosk.housejuices;

import android.graphics.Bitmap;

/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class ArtesianBlend {

    Bitmap vqBitmap;
    String vqNumber;
    String vqName;
    String vqVGratio;
    String vqPGratio;
    String vqDescription;
    String vqCategory;

    public ArtesianBlend() {
        //Empty Constructor for safety.
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

    public ArtesianBlend(String vqnumber, String vqname, String vqvg, String vqpg,
                         String vqscript) {
        this.vqNumber = vqnumber;
        this.vqName = vqname;
        this.vqVGratio = vqvg;
        this.vqPGratio = vqpg;
        this.vqDescription = vqscript;
    }

    public Bitmap getVqBitmap() {
        return vqBitmap;
    }

    public void setVqBitmap(Bitmap bitmap) {
        this.vqBitmap = bitmap;
    }

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
