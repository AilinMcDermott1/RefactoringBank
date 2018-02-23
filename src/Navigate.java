import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Navigate extends BankApplication{



	public static void first(){
		if(table.isEmpty()){
			JOptionPane.showMessageDialog(null, "Make sure there is an account created. ");
		}else{
			saveOpenValues();

			currentItem=0;
			while(!table.containsKey(currentItem)){
				currentItem++;
			}
			DisplayDetails.displayDetails(currentItem);
		}
	}


	public static void next1(){
		if(table.isEmpty()){
			JOptionPane.showMessageDialog(null, "Make sure there is an account created. ");
		}else{
		ArrayList<Integer> keyList = new ArrayList<Integer>();
		int i=0;

		while(i<TABLE_SIZE){
			i++;
			if(table.containsKey(i))
				keyList.add(i);
		}

		int maxKey = Collections.max(keyList);

		saveOpenValues();	

		if(currentItem<maxKey){
			currentItem++;
			while(!table.containsKey(currentItem)){
				currentItem++;
			}
		}
		DisplayDetails.displayDetails(currentItem);	
		}
	}


	public static void prev(){
		if(table.isEmpty()){
			JOptionPane.showMessageDialog(null, "Make sure there is an account created. ");
		}else{
			ArrayList<Integer> keyList = new ArrayList<Integer>();
			int i=0;

			while(i<TABLE_SIZE){
				i++;
				if(table.containsKey(i))
					keyList.add(i);
			}

			int minKey = Collections.min(keyList);

			if(currentItem>minKey){
				currentItem--;
				while(!table.containsKey(currentItem)){
					currentItem--;
				}
			}
			DisplayDetails.displayDetails(currentItem);				
		}
	}


	public static void last(){
		if(table.isEmpty()){
			JOptionPane.showMessageDialog(null, "Make sure there is an account created. ");
		}else{
			saveOpenValues();

			currentItem =29;

			while(!table.containsKey(currentItem)){
				currentItem--;

			}
			DisplayDetails.displayDetails(currentItem);
		}
	}

	public static void listAll(){
		JFrame frame = new JFrame("TableDemo");
		String col[] = {"ID","Number","Name", "Account Type", "Balance", "Overdraft"};

		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		jTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(jTable);
		jTable.setAutoCreateRowSorter(true);

		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {


			Object[] objs = {entry.getValue().getAccountID(), entry.getValue().getAccountNumber(), 
					entry.getValue().getFirstName().trim() + " " + entry.getValue().getSurname().trim(), 
					entry.getValue().getAccountType(), entry.getValue().getBalance(), 
					entry.getValue().getOverdraft()};

			tableModel.addRow(objs);
		}
		frame.setSize(600,500);
		frame.add(scrollPane);
		frame.setVisible(true);			
	}


	public static void findBySurname(){
		String sName = JOptionPane.showInputDialog("Search for surname: ");
		boolean found = false;

		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {

			if(sName.equalsIgnoreCase((entry.getValue().getSurname().trim()))){
				found = true;
				accountIDTextField.setText(entry.getValue().getAccountID()+"");
				accountNumberTextField.setText(entry.getValue().getAccountNumber());
				surnameTextField.setText(entry.getValue().getSurname());
				firstNameTextField.setText(entry.getValue().getFirstName());
				accountTypeTextField.setText(entry.getValue().getAccountType());
				balanceTextField.setText(entry.getValue().getBalance()+"");
				overdraftTextField.setText(entry.getValue().getOverdraft()+"");
			}
		}		
		if(found)
			JOptionPane.showMessageDialog(null, "Surname  " + sName + " found.");
		else
			JOptionPane.showMessageDialog(null, "Surname " + sName + " not found.");
	}


	public static void findByAccount(){
		String accNum = JOptionPane.showInputDialog("Search for account number: ");
		boolean found = false;

		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {

			if(accNum.equals(entry.getValue().getAccountNumber().trim())){
				found = true;
				accountIDTextField.setText(entry.getValue().getAccountID()+"");
				accountNumberTextField.setText(entry.getValue().getAccountNumber());
				surnameTextField.setText(entry.getValue().getSurname());
				firstNameTextField.setText(entry.getValue().getFirstName());
				accountTypeTextField.setText(entry.getValue().getAccountType());
				balanceTextField.setText(entry.getValue().getBalance()+"");
				overdraftTextField.setText(entry.getValue().getOverdraft()+"");						

			}			 
		}
		if(found)
			JOptionPane.showMessageDialog(null, "Account number " + accNum + " found.");
		else
			JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");

	}
}
