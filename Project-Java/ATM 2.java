package Displey;
import java.io.*;
import java.util.*;
interface ATMmethods {
    public void withdraw(int amount);
    public void deposit(int amount);
    public int viewBalance();
    public boolean login(String pass);
}
class User implements ATMmethods {
    private int balance;
    private Deque<String> history;
    private String password;
    User() {
        this.balance = 0;
        this.history = new ArrayDeque<>();
        this.password = "password";
    }
    User(int amount) {
        this.balance = amount;
        this.password = "password";
        this.history = new ArrayDeque<>();
    }
    User(String pass) {
        this.balance = 0;
        this.history = new ArrayDeque<>();
        this.password = pass;
    }
    public void withdraw(int amount) {
        try {
            if(this.balance < amount) throw new Exception("Balance less than amount being withdrawn");
            else {
                this.balance -= amount;
                if(this.history.size() == 10) {
                    this.history.pollFirst();
                }
                this.history.offerLast("Withdrawn amount " + String.valueOf(amount));
                System.out.println("Withdraw Successfull");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deposit(int amount) {
        this.balance += amount;
        if(this.history.size() == 10) {
            this.history.pollFirst();
        }
        this.history.offerLast("Deposited amount " + String.valueOf(amount));
        System.out.println("Deposit Successfull");
    }
    public int viewBalance() {
        return this.balance;
    }
    public boolean login(String pass) {
        return this.password.equals(pass);
    }
    public String toString() {
        return this.history.toString();
    }
}
public class ATM
{
	public static void main(String[] args) throws Exception{
	    User user1 = new User("1234");
	    Scanner in = new Scanner(System.in);
	    try {
	        System.out.println("Enter the password : ");
	        String pass = in.nextLine();
	        if(!user1.login(pass)) throw new Exception("Invalid password");
	        else System.out.println("Login Successfull\n");
	    }
	    catch(Exception e) {
	        System.out.println(e.getMessage());
	        return;
        }
	    while(true) {
	        System.out.println("1. Press 1 to deposit\n2. Press 2 to withdraw\n3. Press 3 to view balance\n4. Press 4 to print statement\n5. Press 5 to exit");
	        int ch = in.nextInt();
	        switch(ch) {
	            case 1:
	                System.out.println("Enter the amount : ");
	                int bal = in.nextInt();
	                user1.deposit(bal);
	                break;
	            case 2:
	                System.out.println("Enter the amount : ");
	                int amount = in.nextInt();
	                user1.withdraw(amount);
	                break;
	            case 3:
	                System.out.println(user1.viewBalance());
	                break;
	            case 4:
	                try {
	                    File statement = new File("statement.txt");
	                    if(statement.createNewFile()) {
	                        BufferedWriter bw = new BufferedWriter(new FileWriter(statement));
	                        bw.write(user1.toString());
	                        bw.close();
	                    }
	                    else {
	                        throw new Exception("An error occured");
	                    }
	                }
	                catch(Exception e) {
	                    System.out.println(e.getMessage());
	                }
	                break;
	            case 5:
	                return;
	        }
	    }
	}
}