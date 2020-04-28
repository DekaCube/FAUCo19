package com.example.faucovid_19info;


//Data class for storing State Data
public class StateCovidData {
    private String state;
    private boolean isFetched;
    private double cases;
    private double todaycases;
    private double deaths;
    private double todaydeaths;
    private double tests;
    private double testspermillion;

    public StateCovidData(String state,double cases,double tcase,double death,double tdeath,double test,double tpm){
        this.isFetched = true;
        this.cases = cases;
        this.todaycases = tcase;
        this.deaths = death;
        this.todaydeaths = tdeath;
        this.tests = test;
        this.testspermillion = tpm;
        this.state = state;
    }
    //Will prob just check that object reference ! null instead
    public boolean isFetched(){
        return this.isFetched;
    }

    public double getCases(){
        return this.cases;
    }

    public double getTodayCases(){
        return this.todaycases;
    }

    public double getDeaths(){
        return this.deaths;
    }

    public double getTodayDeaths(){
        return this.todaydeaths;
    }

    public double getTests(){
        return this.tests;
    }

    public double getTPM(){
        return this.testspermillion;
    }

    public String getState(){
        return this.state;
    }
}
