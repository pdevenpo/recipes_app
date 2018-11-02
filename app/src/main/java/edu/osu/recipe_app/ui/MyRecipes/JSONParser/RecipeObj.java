package edu.osu.recipe_app.JSONParser;

import java.util.ArrayList;

public class RecipeObj {

    private static String mId;
    private String mName;
    private String mSource;
    private int mPrepTime;
    private int mWaitTime;
    private int mCookTime;
    private int mServings;
    private String mComments;
    private int mCalories;
    private int mFat;
    private int mSatFat;
    private int mCarbs;
    private int mFiber;
    private int mSugar;
    private int mProtein;
    private String mInstructions;
    private ArrayList<String> mIngredients;
    private ArrayList<String> mTags;

    public RecipeObj() {

    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }

    public int getPrepTime() {
        return mPrepTime;
    }

    public void setPrepTime(int mPrepTime) {
        this.mPrepTime = mPrepTime;
    }

    public int getWaitTime() {
        return mWaitTime;
    }

    public void setWaitTime(int mWaitTime) {
        this.mWaitTime = mWaitTime;
    }

    public int getCookTime() {
        return mWaitTime;
    }

    public void setCookTime(int mCookTime) {
        this.mCookTime = mCookTime;
    }

    public int getServings() {
        return mServings;
    }

    public void setServings(int mServings) {
        this.mServings = mServings;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String mComments) {
        this.mComments = mComments;
    }

    public int getCalories() {
        return mCalories;
    }

    public void setCalories(int mCalories) {
        this.mCalories = mCalories;
    }

    public int getFat() {
        return mFat;
    }

    public void setFat(int mFat) {
        this.mFat = mFat;
    }

    public int getSatFat() {
        return mSatFat;
    }

    public void setSatFat(int mSatFat) {
        this.mSatFat = mSatFat;
    }

    public int getCarbs() {
        return mCarbs;
    }

    public void setCarbs(int mCarbs) {
        this.mCarbs = mCarbs;
    }

    public int getFiber() {
        return mFiber;
    }

    public void setFiber(int mFiber) {
        this.mFiber = mFiber;
    }

    public int getSugar() {
        return mSugar;
    }

    public void setSugar(int mSugar) {
        this.mSugar = mSugar;
    }

    public int getProtein() {
        return mProtein;
    }

    public void setProtein(int mProtein) {
        this.mProtein = mProtein;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String mInstructions) {
        this.mInstructions = mInstructions;
    }

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(ArrayList<String> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    public void setTags(ArrayList<String> mTags) {
        this.mTags = mTags;
    }


}
