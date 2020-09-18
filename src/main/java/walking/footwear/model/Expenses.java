package walking.footwear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Expenses {

    private static Expenses expenses=new Expenses(0);
    private double balance;
    private Expenses(double balance){
        this.balance=balance;
    }


    public double getBalance() {
        return balance;
    }

    public static Expenses getExpenses(){
        return expenses;
    }

    public void addBalance(double balance) {
        this.balance+=balance;
    }

    public void reduceBalance(double balance) {
        this.balance -= balance;
    }
}
