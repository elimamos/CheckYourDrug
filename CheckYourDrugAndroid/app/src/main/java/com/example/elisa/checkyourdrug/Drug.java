package com.example.elisa.checkyourdrug;

/**
 * Created by Elisa on 2017-11-13.
 */

public class Drug {
  private  String name;
    private   String similar;
    private  Double price;
    private String substance;
    Drug(    String sName, String sSimilar, Double sPrice, String sSubstance){
        name=sName;
        similar=sSimilar;
        price=sPrice;
        substance=sSubstance;
    }
    String getDrugName(){
        return name;
    }
    String getDrugSimilar(){
        return similar;
    }
    String getDrugSubstance(){
        return substance;
    }
    Double getDrugPrice(){
        return price;
    }



}
