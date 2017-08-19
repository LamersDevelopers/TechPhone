package com.startup.tech.Object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CallObject implements Serializable, Parcelable {
    String address;
    String callDateTime;
    String callDesc;
    String callStatus;
    String callType;
    String charsType;
    String code;
    String custName;
    String id;
    String inService;
    String isContinue;
    String location;
    String projName;
    String recieverServiceName;
    String siteName;
    String userCode;

    public CallObject(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCharsType() {
        return charsType;
    }

    public void setCharsType(String charsType) {
        this.charsType = charsType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDateTime() {
        return callDateTime;
    }

    public void setCallDateTime(String callDateTime) {
        this.callDateTime = callDateTime;
    }

    public String getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(String isContinue) {
        this.isContinue = isContinue;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecieverServiceName() {
        return recieverServiceName;
    }

    public void setRecieverServiceName(String recieverServiceName) {
        this.recieverServiceName = recieverServiceName;
    }

    public String isInService() {
        return inService;
    }

    public void setInService(String inService) {
        this.inService = inService;
    }

    public String getCallDesc() {
        return callDesc;
    }

    public void setCallDesc(String callDesc) {
        this.callDesc = callDesc;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CallObject{" +
                "address='" + address + '\'' +
                ", callDateTime='" + callDateTime + '\'' +
                ", callDesc='" + callDesc + '\'' +
                ", callStatus='" + callStatus + '\'' +
                ", callType='" + callType + '\'' +
                ", charsType='" + charsType + '\'' +
                ", code='" + code + '\'' +
                ", custName='" + custName + '\'' +
                ", id='" + id + '\'' +
                ", inService='" + inService + '\'' +
                ", isContinue='" + isContinue + '\'' +
                ", location='" + location + '\'' +
                ", projName='" + projName + '\'' +
                ", recieverServiceName='" + recieverServiceName + '\'' +
                ", siteName='" + siteName + '\'' +
                ", userCode='" + userCode + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.callDateTime);
        dest.writeString(this.callDesc);
        dest.writeString(this.callStatus);
        dest.writeString(this.callType);
        dest.writeString(this.charsType);
        dest.writeString(this.code);
        dest.writeString(this.custName);
        dest.writeString(this.id);
        dest.writeString(this.inService);
        dest.writeString(this.isContinue);
        dest.writeString(this.location);
        dest.writeString(this.projName);
        dest.writeString(this.recieverServiceName);
        dest.writeString(this.siteName);
        dest.writeString(this.userCode);
    }

    protected CallObject(Parcel in) {
        this.address = in.readString();
        this.callDateTime = in.readString();
        this.callDesc = in.readString();
        this.callStatus = in.readString();
        this.callType = in.readString();
        this.charsType = in.readString();
        this.code = in.readString();
        this.custName = in.readString();
        this.id = in.readString();
        this.inService = in.readString();
        this.isContinue = in.readString();
        this.location = in.readString();
        this.projName = in.readString();
        this.recieverServiceName = in.readString();
        this.siteName = in.readString();
        this.userCode = in.readString();
    }

    public static final Parcelable.Creator<CallObject> CREATOR = new Parcelable.Creator<CallObject>() {
        @Override
        public CallObject createFromParcel(Parcel source) {
            return new CallObject(source);
        }

        @Override
        public CallObject[] newArray(int size) {
            return new CallObject[size];
        }
    };
}
