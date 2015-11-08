package zad1.states;

public class BigOrSmallState extends State {


	@Override
	public String getString() {
		return "B/S";
	}

	@Override
	public State conclude(State s) {
		
		SmallLetterState small = new SmallLetterState();
		BigLetterState letter = new BigLetterState();
		
		if(s instanceof BigOrSmallState) return this;
		
		if(s.contains(small)){
			return small;
		}else if(s.contains(letter)){
			return letter;
		}else return this;
	}
	
	@Override
	public Boolean contains(State s) {
		if ((s instanceof BigLetterState) || (s instanceof SmallLetterState)){
			return true;
		}
		else{
			return false;
		}
	}

}
