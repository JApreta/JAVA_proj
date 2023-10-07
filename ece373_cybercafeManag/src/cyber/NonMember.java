package cyber;

public class NonMember extends Client{

	public NonMember() {
		super();
	}
	
	public void buyTime(int amount) {
		timeBalance = timeBalance + amount;
	}
	
}
