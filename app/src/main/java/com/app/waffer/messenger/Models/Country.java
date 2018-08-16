package com.app.waffer.messenger.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Country {

    private String name;
    private List<String> topLevelDomain = null;
    private String alpha2Code;
    private String alpha3Code;
    private List<String> callingCodes = null;
    private String capital;
    private List<String> altSpellings = null;
    private String region;
    private String subregion;
    private Integer population;
    private List<Double> latlng = null;
    private String demonym;
    private Double area;
    private Double gini;
    private List<String> timezones = null;
    private List<String> borders = null;
    private String nativeName;
    private String numericCode;
    private List<Currency> currencies = null;
    private List<Language> languages = null;
    //private Translations translations;
    private String flag;
    //private List<RegionalBloc> regionalBlocs = null;
    private String cioc;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getGini() {
        return gini;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }



    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }



    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }


}












//
//public class RegionalBloc {
//
//    private String acronym;
//    private String name;
//    private List<Object> otherAcronyms = null;
//    private List<Object> otherNames = null;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    public String getAcronym() {
//        return acronym;
//    }
//
//    public void setAcronym(String acronym) {
//        this.acronym = acronym;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Object> getOtherAcronyms() {
//        return otherAcronyms;
//    }
//
//    public void setOtherAcronyms(List<Object> otherAcronyms) {
//        this.otherAcronyms = otherAcronyms;
//    }
//
//    public List<Object> getOtherNames() {
//        return otherNames;
//    }
//
//    public void setOtherNames(List<Object> otherNames) {
//        this.otherNames = otherNames;
//    }
//
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//}
//
//
//public class Translations {
//
//    private String de;
//    private String es;
//    private String fr;
//    private String ja;
//    private String it;
//    private String br;
//    private String pt;
//    private String nl;
//    private String hr;
//    private String fa;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    public String getDe() {
//        return de;
//    }
//
//    public void setDe(String de) {
//        this.de = de;
//    }
//
//    public String getEs() {
//        return es;
//    }
//
//    public void setEs(String es) {
//        this.es = es;
//    }
//
//    public String getFr() {
//        return fr;
//    }
//
//    public void setFr(String fr) {
//        this.fr = fr;
//    }
//
//    public String getJa() {
//        return ja;
//    }
//
//    public void setJa(String ja) {
//        this.ja = ja;
//    }
//
//    public String getIt() {
//        return it;
//    }
//
//    public void setIt(String it) {
//        this.it = it;
//    }
//
//    public String getBr() {
//        return br;
//    }
//
//    public void setBr(String br) {
//        this.br = br;
//    }
//
//    public String getPt() {
//        return pt;
//    }
//
//    public void setPt(String pt) {
//        this.pt = pt;
//    }
//
//    public String getNl() {
//        return nl;
//    }
//
//    public void setNl(String nl) {
//        this.nl = nl;
//    }
//
//    public String getHr() {
//        return hr;
//    }
//
//    public void setHr(String hr) {
//        this.hr = hr;
//    }
//
//    public String getFa() {
//        return fa;
//    }
//
//    public void setFa(String fa) {
//        this.fa = fa;
//    }
//
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//}


