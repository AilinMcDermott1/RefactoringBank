import javax.swing.JOptionPane;

public class Records extends BankApplication{
	
//	deleteItem.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e){

		public static void deleteItem(){
			table.remove(currentItem);
			JOptionPane.showMessageDialog(null, "Account Deleted");


			currentItem=0;
			while(!table.containsKey(currentItem)){
				currentItem++;
			}
			DisplayDetails.displayDetails(currentItem);
		}

//	createItem.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e){
		
		public static void createItem(){
			new CreateBankDialog(table);		
		}



//	modifyItem.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e){
		
		public static void modifyItem(){
			surnameTextField.setEditable(true);
			firstNameTextField.setEditable(true);

			openValues = true;
		}

//
//	setInterest.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e){

		public static void setInterest(){
			String interestRateStr = JOptionPane.showInputDialog("Enter Interest Rate: (do not type the % sign)");
			if(interestRateStr!=null)
				interestRate = Double.parseDouble(interestRateStr);

		}

//	
//	setOverdraft.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent e){
		
		public static void setOverdraft(){
			if(table.get(currentItem).getAccountType().trim().equals("Current")){
				String newOverdraftStr = JOptionPane.showInputDialog(null, "Enter new Overdraft", JOptionPane.OK_CANCEL_OPTION);
				overdraftTextField.setText(newOverdraftStr);
				table.get(currentItem).setOverdraft(Double.parseDouble(newOverdraftStr));
			}
			else
				JOptionPane.showMessageDialog(null, "Overdraft only applies to Current Accounts");

		}

	}
