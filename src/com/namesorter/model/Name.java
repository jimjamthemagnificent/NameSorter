package com.namesorter.model;

public class Name implements Comparable<Name>{
    private final String givenNames;
    private final String surname;

    public Name(String givenNames, String surname){
        this.givenNames = givenNames;
        this.surname = surname;
    }

    @Override
    public String toString(){
        return String.join(" ", givenNames, surname);
    }

    @Override
    public int compareTo(Name o) {
        int surnameComparison = this.surname.compareTo(o.surname);
        if (surnameComparison == 0){
            return this.givenNames.compareTo(o.givenNames);
        }
        else {
            return surnameComparison;
        }
    }
}
