package cn.net.xyd.entity;

/**
 * @FileName:cn.net.xyd.entity.LiCaiProductEntity.java
 * @author:Phoenix
 * @date:2015-09-23 17:43
 * @Version:V1.0
 */
public class LicaiProductEntity {
    private static final String TAG = "LiCaiProductEntity";
    private String name;
    private double borrowApr;//利率
    private int tenderAccountMin;//最投资额
    private int borrowPeriod;//借款期限（月）
    private double borrowAccountWait;//剩余额度
    private double borrowAccountScale;//完成率
    private int borrowValidTime;//借款有效期
    private int reverifyTime;//审核时间
    private String borroContent;//借款描述

    public static String getTAG() {
        return TAG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBorrowApr() {
        return borrowApr;
    }

    public void setBorrowApr(double borrowApr) {
        this.borrowApr = borrowApr;
    }

    public int getTenderAccountMin() {
        return tenderAccountMin;
    }

    public void setTenderAccountMin(int tenderAccountMin) {
        this.tenderAccountMin = tenderAccountMin;
    }

    public int getBorrowPeriod() {
        return borrowPeriod;
    }

    public void setBorrowPeriod(int borrowPeriod) {
        this.borrowPeriod = borrowPeriod;
    }

    public double getBorrowAccountWait() {
        return borrowAccountWait;
    }

    public void setBorrowAccountWait(double borrowAccountWait) {
        this.borrowAccountWait = borrowAccountWait;
    }

    public double getBorrowAccountScale() {
        return borrowAccountScale;
    }

    public void setBorrowAccountScale(double borrowAccountScale) {
        this.borrowAccountScale = borrowAccountScale;
    }

    public int getBorrowValidTime() {
        return borrowValidTime;
    }

    public void setBorrowValidTime(int borrowValidTime) {
        this.borrowValidTime = borrowValidTime;
    }

    public int getReverifyTime() {
        return reverifyTime;
    }

    public void setReverifyTime(int reverifyTime) {
        this.reverifyTime = reverifyTime;
    }

    public String getBorroContent() {
        return borroContent;
    }

    public void setBorroContent(String borroContent) {
        this.borroContent = borroContent;
    }
}
