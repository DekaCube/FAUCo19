package com.example.faucovid_19info;
/**
* Data Class for Holding Country Data
*/
public class CountryCovidData {
    private String country;
    private double totalCases;
    private double newCases;
    private double totalDeaths;
    private double newDeaths;
    private String flagURL;
    private double recovered;
    private double critical;
    private double active;
    private double casesPM;
    private double deathsPM;
    private double tests;
    private double testsPM;

    public CountryCovidData(){
        //Too many parameters for constructor
    }
    //Setters


    public void setCountry(String c){
        this.country = c;
    }


    public void setTotalCases(double t){
        this.totalCases = t;
    }


    public void setNewCases(double n){
        this.newCases = n;
    }
    public void setTotalDeaths(double d){
        this.totalDeaths = d;
    }
    public void setNewDeaths(double n){
        this.newDeaths = n;
    }
    public void setFlagURL(String url){
        this.flagURL = url;
    }
    public void setRecovered(double r){
        this.recovered = r;
    }
    public void setCritical(double c){
        this.critical = c;
    }
    public void setActive(double a){
        this.active = a;
    }
    public void setCasesPerMillion(double cpm){
        this.casesPM = cpm;
    }
    public void setDeathsPerMillion(double dpm){
        this.deathsPM = dpm;
    }
    public void setTests(double t){
        this.tests = t;
    }
    public void setTestsPerMillion(double tmp){
        this.testsPM = tmp;
    }
    //Getters
    public String getCountry(){
        return this.country;
    }

    public String getFlagURL(){
        return this.flagURL;
    }

    public double getTotalCases(){
        return this.totalCases;
    }

    public double getNewCases(){
        return this.newCases;
    }

    public double getTotalDeaths(){
        return this.totalDeaths;
    }

    public double getNewDeaths(){
        return this.newDeaths;
    }

    public double getRecovered(){
        return this.recovered;
    }

    public double getCritical(){
        return this.critical;
    }

    public double getActive(){
        return this.active;
    }

    public double getCasesPerMillion(){
        return this.casesPM;
    }

    public double getDeathsPerMillion(){
        return this.deathsPM;
    }

    public double getTests(){
        return this.tests;
    }

    public double getTestsPerMillion(){
        return this.testsPM;
    }

}




