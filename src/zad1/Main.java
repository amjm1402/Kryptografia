package zad1;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args){
		
		TextContainer ciphertexts = new TextContainer("Ciphertexts", "ciphertext", "00000000");
		
		for(String arg: args){
			System.out.println("File found " +arg);
			ciphertexts.addText(arg);
		}
		
		Analyzer analyzer = new Analyzer(ciphertexts);
		analyzer.solveAll();
		
		
		//for(int i = 0; i<ciphertexts.getContent().size(); i++){
		//	analyzer.printState(i, 20);
		//}
		
		analyzer.printKey();
		
		analyzer.convertToPlaintexts();
		
		analyzer.eureka(20,0 ,"K");

		
		analyzer.eureka(13,1 ," Bardz");

		
		analyzer.eureka(15,7 ," podp");

		analyzer.eureka(20,12 ," zawo");

		analyzer.eureka(17,17 ," pr");

		analyzer.eureka(19,20 ," je");

		analyzer.eureka(16,23 ," pi");

		analyzer.eureka(14,26 ," je");

		analyzer.eureka(13,28 ," ko");

		analyzer.eureka(14,31 ," je");

		analyzer.eureka(16,33 ," tren");

		analyzer.eureka(9,38 ,"wa");

		analyzer.eureka(18,40 ," w");

		analyzer.eureka(19,42 ,' ');


		analyzer.eureka(7,43 ," stra");

		analyzer.eureka(6,48 ," Bronis");

		analyzer.eureka(16,55 ," zawodnik");

	//	analyzer.eureka(16,63 ,"k");

		analyzer.eureka(15,63 ," przegr");

		analyzer.eureka(14,70 ," cz");

		analyzer.eureka(15,73 ," z");

		analyzer.eureka(20,75 ," ");

		analyzer.eureka(9,76 ," ");

		analyzer.eureka(11,77 ," z");

		analyzer.eureka(13,79 ," Jestem");

		analyzer.eureka(13,86 ," prz");

		analyzer.eureka(14,90 ," Broni");

		analyzer.eureka(5,96 ," wyn");

		
		analyzer.eureka(14,100 ," Komoro");

		analyzer.eureka(16,107 ," dono");

		analyzer.eureka(20,112 ," c");

		analyzer.eureka(11,114 ,"m");

		analyzer.eureka(6,115 ,"info");

		analyzer.eureka(14,119 ," ");

		analyzer.eureka(5,120 ," Agencji M");
		analyzer.eureka(11, 130, " s");

		analyzer.eureka(8, 132, " Malgor");

		analyzer.eureka(16, 139, "tren");

		analyzer.eureka(5, 144, "Restrukt");

		analyzer.eureka(2, 152, 'a');
		analyzer.eureka(9, 153, ' ');
		analyzer.eureka(9, 154, 'Z');
		analyzer.eureka(12, 155, 'd');
		analyzer.eureka(12, 156, 'a');
		analyzer.eureka(13, 157, 'k');
		analyzer.eureka(13, 158, 'o');
		analyzer.eureka(13, 159, 'n');
		analyzer.eureka(18, 160, 'B');
		analyzer.eureka(18, 161, 'r');
		analyzer.eureka(18, 162, 'o');
		analyzer.eureka(18, 163, 'n');
		analyzer.eureka(18, 164, 'i');
		analyzer.eureka(18, 165, 's');
		analyzer.eureka(18, 166, 'l');
		analyzer.eureka(18, 167, 'a');

		analyzer.eureka(20, 168, 'K');
		analyzer.eureka(20, 169, 'r');
		analyzer.eureka(20, 170, 'y');
		analyzer.eureka(10, 171, 'i');
		analyzer.eureka(10, 172, 'n');


		analyzer.eureka(8, 177, 'i');
		analyzer.eureka(18, 178, 'k');
		analyzer.eureka(18, 179, 'i');
		analyzer.eureka(19, 180, 'a');
		analyzer.eureka(15, 181, 'u');
		analyzer.eureka(16, 182, 'h');
		analyzer.eureka(9, 183, 't');
		analyzer.eureka(16, 184, ' ');
		analyzer.eureka(8, 185, 'o');
		analyzer.eureka(8, 186, 'r');
		analyzer.eureka(8, 187, 'k');
		analyzer.eureka(8, 188, 'a');
		analyzer.eureka(7, 189, 'n');
		analyzer.eureka(7, 190, 'i');
		analyzer.eureka(7, 191, 'e');
		analyzer.eureka(19, 192, 'j');
		analyzer.eureka(19, 193, ' ');
		analyzer.eureka(15, 194, ' ');
		analyzer.eureka(3, 195, ' ');
		analyzer.eureka(16, 196, 'i');
		analyzer.eureka(16, 197, 'e');
		analyzer.eureka(10, 198, 'k');
		analyzer.eureka(9, 200, 'n');
		analyzer.eureka(9, 201, 'g');
		analyzer.eureka(9, 202, ' ');
		analyzer.eureka(8, 203, 'l');
		analyzer.eureka(8, 204, 'e');
		analyzer.eureka(9, 205, 't');
		analyzer.eureka(9, 206, ' ');
		analyzer.eureka(7, 207, ' ');
		analyzer.eureka(6, 208, "zie");
		analyzer.eureka(1, 210, "awa");
		analyzer.eureka(10, 213, "gender");
		analyzer.eureka(11, 219, "osci");
		analyzer.eureka(7, 223, "ele ");
		
		analyzer.eureka(20,63 ,"e");

















		




		
		analyzer.printPlaintextsAsChar(50);
		
		
	}
}
