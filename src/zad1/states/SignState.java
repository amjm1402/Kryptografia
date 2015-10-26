package zad1.states;

public class SignState extends State implements KnownState{

	@Override
	public String getString() {
		return "Z";
	}

	@Override
	public State conclude(State s) {
		
		if (s instanceof SignState || s instanceof UnknownState || s instanceof SmallOrSignState||s instanceof BigOrSignState){
			return this;
		}
		else {
			System.out.println("tis is it " + s.getString());

			System.out.println("SS ERROR!");
			return null;
		}
	}

	@Override
	public Boolean contains(State s) {
		if (s instanceof SignState){
			return true;
		}
		else return false;
	}

}
