package com.example.faucovid_19info;


/**
 * Data class for holding State Data
 */
public class StateCovidData {
    private String state;
    private boolean isFetched;
    private double cases;
    private double todaycases;
    private double deaths;
    private double todaydeaths;
    private double tests;
    private double testspermillion;

    /**
     *  Constructor-> must use , no setters for this class.
     * @param state name of the state
     * @param cases number of cases
     * @param tcase number of new cases
     * @param death number of deaths
     * @param tdeath number of new deaths
     * @param test number of tests
     * @param tpm tests per million
     */
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

    /**
     * Unused function, checked for null reference to object instead.
     * @return bool representing if populated with data or not.
     */
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
