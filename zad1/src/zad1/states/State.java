package zad1.states;

public abstract class State {
	
	public abstract String getString();
	public abstract State conclude(State s);
	public abstract Boolean contains(State s);


	public static State stateConverter(int i){
		int state = i>>5;
		
		switch (state){
		case 0:
			return new UnknownState();
		case 1:
			return new BigOrSmallState();
		case 2:
			return new SmallOrSignState();
		case 3:
			return new BigOrSignState();
		default: 
			return null;
		}
	}
	

}
