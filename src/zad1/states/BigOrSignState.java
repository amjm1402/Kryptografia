package zad1.states;

public class BigOrSignState extends State{

	@Override
	public String getString() {
		return "B/!";
	}

	@Override
	public State conclude(State s) {
		
		SignState sign = new SignState();
		BigLetterState letter = new BigLetterState();
		
		if(s instanceof BigOrSignState) return this;
		
		if(s.contains(sign)){
			return sign;
		}else if(s.contains(letter)){
			return letter;
		}else return this;
	}
	
	@Override
	public Boolean contains(State s) {
		if ((s instanceof BigLetterState) || (s instanceof SignState)){
			return true;
		}
		else{
			return false;
		}
	}

}
