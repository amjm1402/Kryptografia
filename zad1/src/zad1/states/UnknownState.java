package zad1.states;

public class UnknownState extends State {

	@Override
	public String getString() {
		return "?";
	}

	@Override
	public State conclude(State s) {
		if (s instanceof UnknownState == false){
			return s;
		}
		else return this;
	}

	@Override
	public Boolean contains(State s) {
		return false;
	}

}
