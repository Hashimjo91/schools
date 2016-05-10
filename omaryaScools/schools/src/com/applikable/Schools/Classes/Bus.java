package com.applikable.Schools.Classes;

/**
 * Created by Alhusban on 05/12/2015.
 */
public class Bus {
    String nID;
    String strTEID;
    String strCarNum;
    String strTESim;
    String nTEType;
    String strGroupName;
    String strOwnerName;
    String strOwnerTel;
    String strOwnerAddress;
    String strRemark;
    String strIconID;
    String nFuelBoxSize;
    String nMileageInit;
    String nInterval;
    String nOverSpeed;
    String nSMSNoticeState;

    public Bus() {
    }

    public Bus(String nID, String strTEID, String strCarNum, String strTESim, String nTEType, String strGroupName, String strOwnerName, String strOwnerTel, String strOwnerAddress, String strRemark, String strIconID, String nFuelBoxSize, String nMileageInit, String nInterval, String nOverSpeed, String nSMSNoticeState) {
        this.nID = nID;
        this.strTEID = strTEID;
        this.strCarNum = strCarNum;
        this.strTESim = strTESim;
        this.nTEType = nTEType;
        this.strGroupName = strGroupName;
        this.strOwnerName = strOwnerName;
        this.strOwnerTel = strOwnerTel;
        this.strOwnerAddress = strOwnerAddress;
        this.strRemark = strRemark;
        this.strIconID = strIconID;
        this.nFuelBoxSize = nFuelBoxSize;
        this.nMileageInit = nMileageInit;
        this.nInterval = nInterval;
        this.nOverSpeed = nOverSpeed;
        this.nSMSNoticeState = nSMSNoticeState;
    }

    public String getnID() {
        return nID;
    }

    public void setnID(String nID) {
        this.nID = nID;
    }

    public String getStrTEID() {
        return strTEID;
    }

    public void setStrTEID(String strTEID) {
        this.strTEID = strTEID;
    }

    public String getStrCarNum() {
        return strCarNum;
    }

    public void setStrCarNum(String strCarNum) {
        this.strCarNum = strCarNum;
    }

    public String getStrTESim() {
        return strTESim;
    }

    public void setStrTESim(String strTESim) {
        this.strTESim = strTESim;
    }

    public String getnTEType() {
        return nTEType;
    }

    public void setnTEType(String nTEType) {
        this.nTEType = nTEType;
    }

    public String getStrGroupName() {
        return strGroupName;
    }

    public void setStrGroupName(String strGroupName) {
        this.strGroupName = strGroupName;
    }

    public String getStrOwnerName() {
        return strOwnerName;
    }

    public void setStrOwnerName(String strOwnerName) {
        this.strOwnerName = strOwnerName;
    }

    public String getStrOwnerTel() {
        return strOwnerTel;
    }

    public void setStrOwnerTel(String strOwnerTel) {
        this.strOwnerTel = strOwnerTel;
    }

    public String getStrOwnerAddress() {
        return strOwnerAddress;
    }

    public void setStrOwnerAddress(String strOwnerAddress) {
        this.strOwnerAddress = strOwnerAddress;
    }

    public String getStrRemark() {
        return strRemark;
    }

    public void setStrRemark(String strRemark) {
        this.strRemark = strRemark;
    }

    public String getStrIconID() {
        return strIconID;
    }

    public void setStrIconID(String strIconID) {
        this.strIconID = strIconID;
    }

    public String getnFuelBoxSize() {
        return nFuelBoxSize;
    }

    public void setnFuelBoxSize(String nFuelBoxSize) {
        this.nFuelBoxSize = nFuelBoxSize;
    }

    public String getnMileageInit() {
        return nMileageInit;
    }

    public void setnMileageInit(String nMileageInit) {
        this.nMileageInit = nMileageInit;
    }

    public String getnInterval() {
        return nInterval;
    }

    public void setnInterval(String nInterval) {
        this.nInterval = nInterval;
    }

    public String getnOverSpeed() {
        return nOverSpeed;
    }

    public void setnOverSpeed(String nOverSpeed) {
        this.nOverSpeed = nOverSpeed;
    }

    public String getnSMSNoticeState() {
        return nSMSNoticeState;
    }

    public void setnSMSNoticeState(String nSMSNoticeState) {
        this.nSMSNoticeState = nSMSNoticeState;
    }
}
