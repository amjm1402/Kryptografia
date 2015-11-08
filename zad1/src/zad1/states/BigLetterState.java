package zad1.states;

public class BigLetterState extends State implements KnownState{

	@Override
	public String getString() {
		return "B";
	}

	@Override
	public State conclude(State s) {
		if (s instanceof BigLetterState || s instanceof UnknownState || s instanceof BigOrSignState || s instanceof BigOrSmallState){
			return this;
		}
		else {
			System.out.println("BLS ERROR!");
			return null;
		}
	}
	
	@Override
	public Boolean contains(State s) {
		if (s instanceof BigLetterState){
			return true;
		}
		else return false;
	}

}
