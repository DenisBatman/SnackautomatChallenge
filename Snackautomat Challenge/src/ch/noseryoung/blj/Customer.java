package ch.noseryoung.blj;

public class Customer {
    private double credit;
    final String name;

    public Customer(String name){
        credit = 10;
        this.name = name;
    }

    public double getCredit() {
        return credit;
    }
    public void setCredit(double newCredit){
        credit = newCredit;
    }
    public String getName(){
        return name;
    }
}

