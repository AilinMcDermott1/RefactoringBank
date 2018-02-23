public class BankFiles extends BankApplication {

	
	public static void writeFile(){
		SaveTo.saveToFile();
		CloseFile.closeFile();
	}
	
	public static void saveFileAs(){
		SaveToFileAs.saveToFileAs();
		SaveTo.saveToFile();	
		CloseFile.closeFile();
	}
	
	public static void readFile(){
		OpenFile.openFileRead();
		ReadRecords.readRecords();
		CloseFile.closeFile();		
	}
}
