import java.util.Map;
import javax.swing.JOptionPane;

public class Transactions extends BankApplication{

	public static void calculateInterest() {
		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
			if(entry.getValue().getAccountType().equals("Current")){
				double equation = 1 + ((interestRate)/100);
				entry.getValue().setBalance(entry.getValue().getBalance()*equation);
				JOptionPane.showMessageDialog(null, "Balances Updated");
				DisplayDetails.displayDetails(entry.getKey());
			}
		}
	}

	public static void deposit() {

		String accNum = JOptionPane.showInputDialog("Account number to deposit into: ");
		boolean found = false;
		if(accNum!=null) {

			for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
				if(accNum.equals(entry.getValue().getAccountNumber().trim())){
					found = true;
					String toDeposit = JOptionPane.showInputDialog("Account found, Enter Amount to Deposit: ");
					if(toDeposit!=null) {


						if (Double.parseDouble(toDeposit)<0) {
							JOptionPane.showMessageDialog(null, "Can't deposit a negative amount.");
						}
						else {


							entry.getValue().setBalance(entry.getValue().getBalance() + Double.parseDouble(toDeposit));
							DisplayDetails.displayDetails(entry.getKey());
						}
					} else {
						DisplayDetails.displayDetails(entry.getKey());
					}
				}
			}
			if (!found)
				JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
		}else {
			DisplayDetails.displayDetails(currentItem);}
	}

	public static void withdraw() {
		String accNum = JOptionPane.showInputDialog("Account number to withdraw from: ");

		if(accNum!=null) {

			for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {


				if(accNum.equals(entry.getValue().getAccountNumber().trim())){

					String toWithdraw = JOptionPane.showInputDialog("Account found, Enter Amount to Withdraw: ");

					if(toWithdraw != null) {
						if(entry.getValue().getAccountType().trim().equals("Current")){
							if(Double.parseDouble(toWithdraw) > entry.getValue().getBalance() + entry.getValue().getOverdraft())
								JOptionPane.showMessageDialog(null, "Transaction exceeds overdraft limit");
							else{
								entry.getValue().setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
								DisplayDetails.displayDetails(entry.getKey());
							}
						}
						else if(entry.getValue().getAccountType().trim().equals("Deposit")){
							if(Double.parseDouble(toWithdraw) <= entry.getValue().getBalance()){
								entry.getValue().setBalance(entry.getValue().getBalance()-Double.parseDouble(toWithdraw));
								DisplayDetails.displayDetails(entry.getKey());
							}
							else
								JOptionPane.showMessageDialog(null, "Insufficient funds.");
						}
					}else {
						DisplayDetails.displayDetails(entry.getKey());}
				}				
			}
		}else {
			DisplayDetails.displayDetails(currentItem);}
	}
}
