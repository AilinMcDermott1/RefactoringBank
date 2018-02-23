import java.io.IOException;

import javax.swing.JOptionPane;

public class CloseFile extends BankFiles {
	
	public static void closeFile() 
	{
		try // close file and exit
		{
			if ( input != null )
				input.close();
		} // end try
		catch ( IOException ioException )
		{

			JOptionPane.showMessageDialog(null, "Error closing file.");
		} // end catch
	} // end method closeFile

}
