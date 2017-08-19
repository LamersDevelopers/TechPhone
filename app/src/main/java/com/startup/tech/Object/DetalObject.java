package com.startup.tech.Object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AlekseyG on 8/7/2017.
 */

public class DetalObject implements Parcelable {

    String code;
    String desc_;
    String unit;
    String barcode;
    String source;
    String status;

    public DetalObject(String code, String desc_, String unit, String barcode, String source, String status) {
        this.code = code;
        this.desc_ = desc_;
        this.unit = unit;
        this.barcode = barcode;
        this.source = source;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc_() {
        return desc_;
    }

    public void setDesc_(String desc_) {
        this.desc_ = desc_;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "DetalObject{" +
                "code='" + code + '\'' +
                ", desc_='" + desc_ + '\'' +
                ", unit='" + unit + '\'' +
                ", barcode='" + barcode + '\'' +
                ", source='" + source + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.desc_);
        dest.writeString(this.unit);
        dest.writeString(this.barcode);
        dest.writeString(this.source);
        dest.writeString(this.status);
    }

    protected DetalObject(Parcel in) {
        this.code = in.readString();
        this.desc_ = in.readString();
        this.unit = in.readString();
        this.barcode = in.readString();
        this.source = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<DetalObject> CREATOR = new Parcelable.Creator<DetalObject>() {
        @Override
        public DetalObject createFromParcel(Parcel source) {
            return new DetalObject(source);
        }

        @Override
        public DetalObject[] newArray(int size) {
            return new DetalObject[size];
        }
    };
}
