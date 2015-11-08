package zad1;

import java.util.ArrayList;

import zad1.states.State;
import zad1.states.UnknownState;

public class Text {
	
	private ArrayList<Integer> bytes;
	private int index; 
	private String type;
	private ArrayList<State> states;
	
	public Text(int index, String type, ArrayList<Integer> list){
		this.index = index;
		this.type = type;
		bytes = list;
		states = new ArrayList<State>(); 
		for(Integer i : bytes){
			states.add(new UnknownState());
		}
	}
	
	public void printAsBinaryString(int rowLength, String format){
		
		System.out.println(type+" "+index+": ");
		int rowCounter=0;
		
		for(Integer letter: bytes){
			System.out.print(toFormattedBinaryString(letter, format)+" ");
			rowCounter++;
			
			if(rowCounter==rowLength){
				System.out.println();
				rowCounter=0;
			}
		}
		
		System.out.println();
	}
	
	public void printAsChar(int rowLength){
		

		System.out.println(type+" "+index+": ");
		int rowCounter=0;
		
		for(Integer letter: bytes){
			System.out.print((char) letter.intValue()+" ");
			rowCounter++;
			
			if(rowCounter==rowLength){
				System.out.println();
				rowCounter=0;
			}
		}
		
		System.out.println();
		
		
	}
	
	public String toFormattedBinaryString(int character, String format){
		
		String currentByte = Integer.toBinaryString(character);

		currentByte = format.substring(currentByte.length())+currentByte;
		return currentByte;
	}
	
	public ArrayList<Integer> getBytes() {
		return bytes;
	}
	
	public void setBytes(ArrayList<Integer> bytes) {
		this.bytes = bytes;
	}
	
	
	public static ArrayList<Integer> xorTexts(Text t1, Text t2){
		
	
		int shorter = Math.min(t1.getBytes().size(), t2.getBytes().size());
		ArrayList<Integer> newList = new ArrayList<Integer>();
		
		
		for(int i = 0; i<shorter; i++){
			newList.add(t1.getBytes().get(i)^t2.getBytes().get(i));
		}
		
		return newList;
		
	}
	
	public Integer xorByte(int xorByte, int index){
		return xorByte^this.bytes.get(index);
	}

	public void replace(int index, char replacement){
		bytes.remove(index);
		bytes.add(index, (int) replacement);
	}
	
	public ArrayList<State> getStates() {
		return states;
	}
	
	public void replace(int index, State replacement){
		
		//System.out.println("Replacing in message "+this.index+" byte number "+index+" to "+replacement.getString());
		states.remove(index);
		states.add(index, replacement);
	}
	
	public void printStates(int rowLength){
		
		System.out.println(type+" "+index+": ");
		int rowCounter=0;
		
		for(State state: states){
			System.out.print(state.getString()+" ");
			rowCounter++;
			
			if(rowCounter==rowLength){
				System.out.println();
				rowCounter=0;
			}
		}
		
		System.out.println();
		
	}
	
	public void printMixed(int rowLength, Text key){
		
		System.out.println(type+" "+index+": ");
		int rowCounter=0;
		
		for(int i=0; i<bytes.size(); i++){
			if(key.getBytes().get(i)==0) System.out.print(states.get(i).getString()+" ");
			else {
				System.out.print(""+(char) bytes.get(i).intValue()+" ");
			}
			rowCounter++;
			
			if(rowCounter==rowLength){
				System.out.println();
				rowCounter=0;
			}
		}
		
		System.out.println();
		
	}
		
}
