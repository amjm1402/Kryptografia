package zad1.states;

public class SmallOrSignState extends State {

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return "S/!";
	}

	@Override
	public State conclude(State s) {
		
	//	System.out.println("Concluding "+this.getString()+" "+s.getString());
		
		SmallLetterState small = new SmallLetterState();
		SignState sign = new SignState();
		
		if(s instanceof SmallOrSignState) return this;
		
		if(s.contains(small)){
			return small;
		}else if(s.contains(sign)){
			return sign;
		}else return this;
	}
	
	@Override
	public Boolean contains(State s) {
		if ((s instanceof SignState) || (s instanceof SmallLetterState)){
			return true;
		}
		else{
			return false;
		}
	}

}
