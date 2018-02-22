import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class BankFiles extends BankApplication {

	private static  JFileChooser fc;
	final static int TABLE_SIZE = 29;
	private static  RandomAccessFile input;
	private static  RandomAccessFile output;


	public void displayDetails(int currentItem) {	

		accountIDTextField.setText(table.get(currentItem).getAccountID()+"");
		accountNumberTextField.setText(table.get(currentItem).getAccountNumber());
		surnameTextField.setText(table.get(currentItem).getSurname());
		firstNameTextField.setText(table.get(currentItem).getFirstName());
		accountTypeTextField.setText(table.get(currentItem).getAccountType());
		balanceTextField.setText(table.get(currentItem).getBalance()+"");
		if(accountTypeTextField.getText().trim().equals("Current"))
			overdraftTextField.setText(table.get(currentItem).getOverdraft()+"");
		else
			overdraftTextField.setText("Only applies to current accs");

	}

	public static void openFileRead()
	{

		table.clear();

		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

		} else {
		}


		try // open file
		{
			if(fc.getSelectedFile()!=null)
				input = new RandomAccessFile( fc.getSelectedFile(), "r" );
		} // end try
		catch ( IOException ioException )
		{
			JOptionPane.showMessageDialog(null, "File Does Not Exist.");
		} // end catch

	} // end method openFile

	static String fileToSaveAs = "";

	public static void saveToFileAs()
	{

		fc = new JFileChooser();

		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			fileToSaveAs = file.getName();
			JOptionPane.showMessageDialog(null, "Accounts saved to " + file.getName());
		} else {
			JOptionPane.showMessageDialog(null, "Save cancelled by user");
		}


		try {
			if(fc.getSelectedFile()==null){
				JOptionPane.showMessageDialog(null, "Cancelled");
			}
			else
				output = new RandomAccessFile(fc.getSelectedFile(), "rw" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public static void closeFile() 
	{
		try // close file and exit
		{
			if ( input != null )
				input.close();
		} // end try
		catch ( IOException ioException )
		{

			JOptionPane.showMessageDialog(null, "Error closing file.");//System.exit( 1 );
		} // end catch
	} // end method closeFile

	public static void readRecords()
	{

		RandomAccessBankAccount record = new RandomAccessBankAccount();



		try // read a record and display
		{
			while ( true )
			{
				do
				{
					if(input!=null)
						record.read( input );
				} while ( record.getAccountID() == 0 );



				BankAccount ba = new BankAccount(record.getAccountID(), record.getAccountNumber(), record.getFirstName(),
						record.getSurname(), record.getAccountType(), record.getBalance(), record.getOverdraft());


				Integer key = Integer.valueOf(ba.getAccountNumber().trim());

				int hash = (key%TABLE_SIZE);


				while(table.containsKey(hash)){

					hash = hash+1;
				}

				table.put(hash, ba);


			} // end while
		} // end try
		catch ( EOFException eofException ) // close file
		{
			return; // end of file was reached
		} // end catch
		catch ( IOException ioException )
		{
			JOptionPane.showMessageDialog(null, "Error reading file.");
			System.exit( 1 );
		} // end catch
	}

	public static void saveToFile(){


		RandomAccessBankAccount record = new RandomAccessBankAccount();


		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
			record.setAccountID(entry.getValue().getAccountID());
			record.setAccountNumber(entry.getValue().getAccountNumber());
			record.setFirstName(entry.getValue().getFirstName());
			record.setSurname(entry.getValue().getSurname());
			record.setAccountType(entry.getValue().getAccountType());
			record.setBalance(entry.getValue().getBalance());
			record.setOverdraft(entry.getValue().getOverdraft());

			if(output!=null){

				try {
					record.write( output );
				} catch (IOException u) {
					u.printStackTrace();
				}
			}

		}


	}
}
