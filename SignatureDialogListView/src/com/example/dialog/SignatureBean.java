package com.example.dialog;

/**
 * 签名的基本信息
 */
public class SignatureBean {
    String SignarureName;
    int pictureID;

    public SignatureBean(String signarureName, int pictureID) {
        SignarureName = signarureName;
        this.pictureID = pictureID;
    }

    public String getSignarureName() {
        return SignarureName;
    }

    public void setSignarureName(String signarureName) {
        SignarureName = signarureName;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    @Override
    public String toString() {
        return "SignatureBean{" +
                "SignarureName='" + SignarureName + '\'' +
                ", pictureID=" + pictureID +
                '}';
    }
}
