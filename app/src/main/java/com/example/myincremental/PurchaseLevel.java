package com.example.myincremental;

public class PurchaseLevel {

    public String Name;

    public int Level;

    public double Boost;

    public long Cost;

    public PurchaseLevel(String name, double boost, long cost) {
        Name = name;
        Level = 0;
        Boost = boost;
        Cost = cost;
    }

    public void increaseLevel() {
        this.Level++;
    }
}
