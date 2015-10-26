package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import zad1.states.State;

public class TextContainer {
	private String name;
	private String type;
	private String format;
	private ArrayList<Text> content;
	private int maxLength;
	
	
	public TextContainer(String name, String type, String format){
		this.type = type;
		this.format = format;
		this.content = new ArrayList<Text>();
		this.name = name;
	}
	
	public void addText(Text t){
		content.add(t);
	}
	
	public void addText(ArrayList<Integer> text){
		Text newText = new Text(content.size(), type, text);
		content.add(newText);
	}
	
	public void addEmptyText(int length){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		
		for(int i=0; i<length; i++){
			list.add((int) '_');
		}
		
		Text t = new Text(this.content.size(),type,list);
		this.content.add(t);

	}
	
	public void addText(String fileName){
		
		try{
			Scanner sc = new Scanner(new File(fileName));
			ArrayList<Integer> newList = new ArrayList<Integer>();
			
			while(sc.hasNext()){
				
				 String line = sc.nextLine();  
				 String[] words = line.split(" ");
				 
				 for(String word: words){
					 //System.out.println(word);
					 newList.add(Integer.parseInt(word, 2));
				 }
				 
			}
			
			this.addText(newList);
			sc.close();
		}catch (FileNotFoundException e){
			System.out.println("File "+fileName+" not found");
		}
		
	}
	
	public void removeText(int i){
		content.remove(i);
	}
	
	public void removeText(Text t){
		content.remove(t);
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Text> getContent() {
		return content;
	}
	
	//xoruje ity el. kontenera 1 z itym elementem kontenera 2
	public static TextContainer xorContainers(TextContainer c1, TextContainer c2){
		TextContainer result = new TextContainer(c1.getName()+" xor "+c2.getName(), "result" , "00000000");
		
		int smaller = Math.min(c1.getContent().size(), c2.getContent().size());
		
		for(int i = 0; i<smaller; i++){
			
			ArrayList<Integer> xor = Text.xorTexts(c1.getContent().get(i), c2.getContent().get(i));
			result.addText(xor);
			
			System.out.println("Added text ");
			
			
		}
		
		
		return result;
	}
	
	public static TextContainer xorContainers(TextContainer c1, TextContainer c2, int textPosition){
				

		
		TextContainer result = new TextContainer(c1.getName()+" xor "+c2.getName(), "result" , "00000000");
				
		for(int i = 0; i<c2.getContent().size(); i++){
			
			ArrayList<Integer> xor = Text.xorTexts(c1.getContent().get(textPosition), c2.getContent().get(i));
			result.addText(xor);
						
			
		}
		
		
		return result;
		
	}
	
	
	
	public void printAllAsBytes(int rowLength){
		
		for(Text t: content){
			t.printAsBinaryString(rowLength, format);
		}
		
	}
	
	public void printAllAsChar(int rowLength){
		for(Text t: content){
			t.printAsChar(rowLength);
		}
	}
	

	
	public void empty(){
		content.removeAll(content);
	}
	
	// xoruje wszystkie teksty na i-tej pozycji z danym bajtem
	public ArrayList<Integer> xorByte(int xor, int index){
		ArrayList<Integer> newList = new ArrayList<Integer>();
		
		for(Text t: content){
			newList.add(t.xorByte(xor, index));
		}
		
		return newList;
	}
	
	public void printMixed(int rowLength, Text key){
		
		for(Text t: content){
			t.printMixed(rowLength, key);
		}
	}
	
	
}
