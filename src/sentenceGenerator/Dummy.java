package sentenceGenerator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Dummy extends JFrame{

	Dummy(){
		choose();
	}
	public void choose(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
	}
	public static void main(String arg[]){
//		BnfTokenizer bnf = new BnfTokenizer();
//		bnf.tokenize("<sentence> ::= <noun phrase> <verb phrase>");
//		String token = null;
//		while(!"EOF".equals((token = bnf.nextToken()))){
//			System.out.println(token);
//		}
		
		Dummy sfc = new Dummy();
	    sfc.setVisible(true);
		
	}
}
