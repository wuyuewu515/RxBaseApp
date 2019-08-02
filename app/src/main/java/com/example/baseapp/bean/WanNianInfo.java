package com.example.baseapp.bean;

public class WanNianInfo {
//      "date": "2018-11-25",
//              "weekDay": 7,
//              "yearTips": "戊戌",
//              "type": 1,
//              "chineseZodiac": "狗",
//              "solarTerms": "小雪后",
//              "typeDes" : "休息日",
//              "avoid": "移徙.入宅.安门.作梁.安葬",
//              "lunarCalendar": "10-18",
//              "suit": "祭祀.祈福.求嗣.斋醮.沐浴.冠笄.出行.理发.拆卸.解除.起基.动土.定磉.安碓硙.开池.掘井.扫舍.除服.成服.移柩.启攒.立碑.谢土",
//              "dayOfYear": 329,
//              "weekOfYear": 47


    private String date;
    private String weekDay;
    private String yearTips;
    private String type;
    private String chineseZodiac;
    private String solarTerms;
    private String typeDes;
    private String avoid;
    private String lunarCalendar;
    private String suit;
    private String dayOfYear;
    private String weekOfYear;

    public String getDate() {
        return date;
    }

    public WanNianInfo setDate(String date) {
        this.date = date;
        return this;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public WanNianInfo setWeekDay(String weekDay) {
        this.weekDay = weekDay;
        return this;
    }

    public String getYearTips() {
        return yearTips;
    }

    public WanNianInfo setYearTips(String yearTips) {
        this.yearTips = yearTips;
        return this;
    }

    public String getType() {
        return type;
    }

    public WanNianInfo setType(String type) {
        this.type = type;
        return this;
    }

    public String getChineseZodiac() {
        return chineseZodiac;
    }

    public WanNianInfo setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
        return this;
    }

    public String getSolarTerms() {
        return solarTerms;
    }

    public WanNianInfo setSolarTerms(String solarTerms) {
        this.solarTerms = solarTerms;
        return this;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public WanNianInfo setTypeDes(String typeDes) {
        this.typeDes = typeDes;
        return this;
    }

    public String getAvoid() {
        return avoid;
    }

    public WanNianInfo setAvoid(String avoid) {
        this.avoid = avoid;
        return this;
    }

    public String getLunarCalendar() {
        return lunarCalendar;
    }

    public WanNianInfo setLunarCalendar(String lunarCalendar) {
        this.lunarCalendar = lunarCalendar;
        return this;
    }

    public String getSuit() {
        return suit;
    }

    public WanNianInfo setSuit(String suit) {
        this.suit = suit;
        return this;
    }

    public String getDayOfYear() {
        return dayOfYear;
    }

    public WanNianInfo setDayOfYear(String dayOfYear) {
        this.dayOfYear = dayOfYear;
        return this;
    }

    public String getWeekOfYear() {
        return weekOfYear;
    }

    public WanNianInfo setWeekOfYear(String weekOfYear) {
        this.weekOfYear = weekOfYear;
        return this;
    }
}
