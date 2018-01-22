package DataStructures;
import java.io.FileWriter;
import java.io.IOException;


public class AusgabeInDatei {

	public AusgabeInDatei(){}
	
	public AusgabeInDatei(String fileName, String s, boolean newLine){
		FileWriter fw;
		try{
			fw = new FileWriter(fileName, true);
			if(newLine){
				fw.write(s + "\n");}
			else{
				fw.write(s);
			}
			fw.close();
		}
		catch(IOException e){
			System.out.println("Fehler beim Schreiben der Datei " + fileName);
			System.out.println(e.toString());
		}
	}
	
	public void write(String fileName, String s, boolean newLine){
		FileWriter fw;
		try{
			fw = new FileWriter(fileName, true);
			if(newLine){
				fw.write(s + "\n");}
			else{
				fw.write(s);
			}
			fw.close();
		}
		catch(IOException e){
			System.out.println("Fehler beim Schreiben der Datei " + fileName);
			System.out.println(e.toString());
		}
	}
}
