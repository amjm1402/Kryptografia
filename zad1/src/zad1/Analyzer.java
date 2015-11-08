package zad1;

import java.util.ArrayList;
import java.util.Map;

import zad1.states.BigLetterState;
import zad1.states.BigOrSignState;
import zad1.states.KnownState;
import zad1.states.SignState;
import zad1.states.SmallLetterState;
import zad1.states.SmallOrSignState;
import zad1.states.State;

public class Analyzer {

	TextContainer ciphergraphs;
	Text key;
	TextContainer plaintexts;
	String signs;
	String bigLetters;
	String smallLetters;
	
	public Analyzer(TextContainer ciphergraphs){
		this.ciphergraphs = ciphergraphs;
		ArrayList<Integer> emptyKey = new ArrayList<Integer>();
		key = new Text(0, "key", emptyKey);
		plaintexts = new TextContainer("plaintexts", "message", "00000000");
		
		for(int i=0; i<ciphergraphs.getContent().size(); i++){
			plaintexts.addEmptyText(ciphergraphs.getContent().get(i).getBytes().size());
		}
		
		
		for(int i=32; i<=40; i++){
			signs=signs+(char) i;
		}
		
		for(int i=41; i<=90; i++){
			bigLetters=bigLetters+(char) i;
		}
	
		for(int i=97; i<=122; i++){
			smallLetters=smallLetters+(char) i;
		}
	
	}
	
	public void printState(int i, int rowLength){
		ciphergraphs.getContent().get(i).printStates(rowLength);
	}
	
	
	public void solve(int index){
		
		ArrayList<Text> content = ciphergraphs.getContent();
		Text analyzedCiphergraph = content.get(index);
		int containerSize = content.size();
		int textLength = analyzedCiphergraph.getBytes().size();
		int myIndex = index;
		int next = (index+1)%containerSize;
		int next2 = (index+2)%containerSize;
		Text nextText = content.get(next);
		Text next2Text = content.get(next2);
		int xorResult1, xorResult2, xorResult3, xor;
		State conclusion1, conclusion2, conclusion3;
		boolean found=false;
		State currentState;
		
		System.out.println("Analyzing ciphergraph no "+myIndex);
		
		for(int i = 0; i<textLength; i++){
			
			if(key.getBytes().size()<i){
				key.getBytes().add((int) 0);
			}
			
			currentState = analyzedCiphergraph.getStates().get(i);
			xor = analyzedCiphergraph.getBytes().get(i);

			
//			System.out.println("Byte no "+i+" "+analyzedCiphergraph.toFormattedBinaryString(xor, "00000000"));
//			System.out.println("State: "+currentState.getString());
//			System.out.println("------------------------------");
			
			found=false;
			
			next = (index+1)%containerSize;
			next2 = (index+2)%containerSize;
			nextText = content.get(next);
			next2Text = content.get(next2);
			
			while((next!=myIndex)&&(!found)){
	//			System.out.println("next "+next+" my index "+index);

				if(nextText.getBytes().size()<=i){
	//				System.out.println("Skipped!");
					next = (next+1)%containerSize;
					nextText = content.get(next);
					continue;
				}
				
				next2 = (next+1)%containerSize;
				next2Text = content.get(next2);

				while((next2!=myIndex)&&(!found)){
	//				System.out.println("next2 "+next2+" my index "+myIndex);
					
					
					if(next2Text.getBytes().size()<=i){
	//					System.out.println("Skipped!");

						next2 = (next2+1)%containerSize;
						next2Text = content.get(next2);
						continue;
					}
						

		//			System.out.println("Xoring with message "+next);
					xorResult1 = content.get(next).xorByte(xor, i);
		//			System.out.println("Result "+xorResult1);
		//			System.out.println(State.stateConverter(xorResult1).getString());
					
		//			System.out.println("Xoring with message "+next2);
					xorResult2 = content.get(next2).xorByte(xor, i);
		//			System.out.println("Result "+xorResult2);
		//			System.out.println(State.stateConverter(xorResult2).getString());
	
		//			System.out.println("Xoring message "+next+" with message "+next2);
					xorResult3 = content.get(next).xorByte(content.get(next2).getBytes().get(i), i);
		//			System.out.println("Result "+xorResult3);
		//			System.out.println(State.stateConverter(xorResult3).getString());
					
					conclusion1 = State.stateConverter(xorResult1).conclude(State.stateConverter(xorResult2));
					conclusion1 = conclusion1.conclude(analyzedCiphergraph.getStates().get(i));
					conclusion2 = State.stateConverter(xorResult1).conclude(State.stateConverter(xorResult3));
					conclusion2 = conclusion2.conclude(nextText.getStates().get(i));
					conclusion3 = State.stateConverter(xorResult2).conclude(State.stateConverter(xorResult3));
					conclusion3 = conclusion3.conclude(next2Text.getStates().get(i));
					
					
					

	//				System.out.println("Conclusions: "+conclusion1.getString()+" "+conclusion2.getString()+" "+conclusion3.getString());
					
					if(conclusion1 instanceof KnownState){
						found = true;
	//					System.out.println("Found!");
						
						State lastState = analyzedCiphergraph.getStates().get(i-1);
						State stateBeforeThat = null;
						if(i-2>0) stateBeforeThat = analyzedCiphergraph.getStates().get(i-2);
						
						if((conclusion1 instanceof BigLetterState) && (lastState instanceof SmallOrSignState)){
							analyzedCiphergraph.replace(i-1, new SignState());
							lastState = analyzedCiphergraph.getStates().get(i-1);

						}
						
						if((conclusion1 instanceof BigLetterState) && lastState instanceof SignState){
							if(stateBeforeThat instanceof SignState){
								int xoredValue = analyzedCiphergraph.getBytes().get(i-2) ^ 46;
								
								if(isReasonable(xoredValue)) key.replace(i-2, (char) xoredValue);
								if(!checkKey(i-2)){
									System.out.println("Nope dot");
									key.replace(i-2, (char) 0);
								}
								else{
									System.out.println("Yis dot");
								}
							}
							int xoredValue = analyzedCiphergraph.getBytes().get(i-1) ^ 32;
							
							if(isReasonable(xoredValue)){
								key.replace(i-1, (char) xoredValue);
								
								if(!checkKey(i-1)){
									System.out.println("Nope space");
									key.replace(i-1, (char) 0);
								}
								else{
									System.out.println("Yis space");
								}
								
							}
							else{
								xoredValue = analyzedCiphergraph.getBytes().get(i-1) ^ 34;
								
								if(isReasonable(xoredValue)){
									key.replace(i-1, (char) xoredValue);
									
									if(!checkKey(i-1)){
										System.out.println("Nope quote");
										key.replace(i-1, (char) 0);
									}
									else{
										System.out.println("Yis quote");
									}
								}

							}
							
							
						}
						
						if(conclusion1 instanceof SmallLetterState && lastState instanceof SignState){
								int xoredValue = analyzedCiphergraph.getBytes().get(i-1) ^ 32;
								
								if(isReasonable(xoredValue)){
									key.replace(i-1, (char) xoredValue);
								}
						}
						
						
					}
					
					analyzedCiphergraph.replace(i, conclusion1);
					nextText.replace(i, conclusion2);
					next2Text.replace(i, conclusion3);
					
					next2=(next2+1)%containerSize;
					next2Text = content.get(next2);
				}
					
				
				next=(next+1)%containerSize;
				nextText = content.get(next);
				

					
			}
			
		}
	}
	
	public void solveAll(){
		for(int i=0; i<ciphergraphs.getContent().size(); i++){
			solve(i);
		}
	}
	

	
	public void bruteForceAttack(){
		
		ArrayList<Text> content = ciphergraphs.getContent();
		
		for(int i=0; i<=content.size(); i++){
					
		}
	}
	
	public void printKey(){
		System.out.println("Printing key");
		key.printAsChar(50);
	}

	public boolean isReasonable(int value){
		if((value>48&&value<57)||(value>65&&value<90)||(value>97&&value<122)){
			return true;
		}
		else return false;
	}
	
	public void convertToPlaintexts(){
		
		plaintexts = new TextContainer("plaintexts", "message", "00000000");
		plaintexts.addText(key);
		
		plaintexts = TextContainer.xorContainers(plaintexts, ciphergraphs, 0);
		
	}
	
	public void printPlaintextsAsChar(int rowLength){
		plaintexts.printMixed(rowLength,key);
	}
	
	public boolean checkKey(int position){
		int keyPart = key.getBytes().get(position);
		for(int i=0; i<ciphergraphs.getContent().size(); i++){

			Text t = ciphergraphs.getContent().get(i);
			if(ciphergraphs.getContent().size()>position){
				int xorValue = t.xorByte(keyPart, position);
				if(!isReasonable(xorValue)) return false;
			}
			
		}
		return true;
	}
	
	public void checkPosition(int xoredValue, int i, String msg){
		if(isReasonable(xoredValue)) key.replace(i-2, (char) xoredValue);
		if(!checkKey(i-2)){
			System.out.println("Nope "+msg);
			key.replace(i-2, (char) 0);
		}
		else{
			System.out.println("Yis "+msg);
		}
	}
	
	
	public void eureka(int textIndex, int position, char sign){
		
		System.out.println("Eureka!");
		
		plaintexts.getContent().get(textIndex).replace(position, sign);
		
		int xor = sign^ciphergraphs.getContent().get(textIndex).getBytes().get(position);
		
		key.replace(position, (char) xor);
		
		convertToPlaintexts();
		
		
		
	}
	
	public void eureka(int textIndex, int position, String text){
		for(int i=0; i<text.length(); i++){
			eureka(textIndex, position+i, text.charAt(i));
		}
	}
	
	
}
