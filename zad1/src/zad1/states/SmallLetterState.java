package zad1.states;

public class SmallLetterState extends State implements KnownState {

	@Override
	public String getString() {
		return "S";
	}

	@Override
	public State conclude(State s) {
		if(s instanceof SmallLetterState||s instanceof SmallOrSignState||s instanceof BigOrSmallState||s instanceof UnknownState){
			return this;
		}
		else {
			System.out.println("SLS ERROR!");
			return null;
		}

	}

	@Override
	public Boolean contains(State s) {
		if(s instanceof SmallLetterState){
			return true;
		}
		else return false;
	}

}
