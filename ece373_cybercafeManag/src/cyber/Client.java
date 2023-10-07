package cyber;

public abstract class Client {
	
	private Computer pc;
	protected int timeBalance;
	protected boolean isMember;
	protected boolean isNonMember;
	
	public Client() {
		pc = new Computer();
		timeBalance = 0;
		isMember=false;
		isNonMember=false;
	}
	
	public Client(Computer computer, int amount) {
		pc = computer;
		timeBalance = amount;
	}
	
	public void payForService(Service service) {
		// GUI pop up?
	}
	
	public void setPC(Computer aPC){
		pc = aPC;
	}
	
	public Computer getPC(){
		return pc;
	}
	
	public boolean assigningPC() {
		if (pc != null)
			return true;
		else 
			return false;
	}
	
	public void setBalance(int balance) {
		timeBalance = balance;
	}
	
	public int getBalance() {
		return timeBalance;
	}
	
	public abstract void buyTime(int amount);
	
}
