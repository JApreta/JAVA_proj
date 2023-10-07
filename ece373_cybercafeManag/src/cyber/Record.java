package cyber;

public class Record {

	private Cafe cafe;
	
	public Record() {}
	
	public void setCafeobj(Cafe cafe){
		this.cafe=cafe;
	}
	
	public Cafe getCafeobj(){
		return this.cafe;
	}
	
	
	public String printAllPC() {
		int i = 0;
		
		String text = "Computer Id: \t\tIP Address: \n";
		for (i = 0; i < cafe.getComputerList().size(); i++) {
			text = text +cafe.getComputerList().get(i).getID() +"\t\t\t\t"+cafe.getComputerList().get(i).getIP()+ "\n";
			
		}
		if(cafe.getComputerList().size()==0)
			text = "There is no Computers in the System";
		//System.out.println(text);
		return text;
	}
	
	public String printAllMember() {
		int i = 0;
		String text = "Member Id: \t\tMember Name: \n";
		
		for (i = 0; i <		cafe.getMembershipList().size(); i++) {
			text = text +cafe.getMembershipList().get(i).getID()+"\t\t\t\t"+ cafe.getMembershipList().get(i).getName() + "\n";
			
		}
		if(cafe.getMembershipList().size()==0)
			text = "There is no Members in the System";
		//System.out.println(text);
		return text;
	}
	
	public String printAllAvailablePC() {
		int i = 0;
		boolean flag=false;
		String text = "Computer Id: \t\tIP Address: \n";
		for (i = 0; i < cafe.getComputerList().size(); i++) {
			if (!cafe.getComputerList().get(i).getIsOccupied()){
				flag=true;
				//text = text +"Computer Id: "+ cafe.getComputerList().get(i).getID() +"\t\tIP Address: "+cafe.getComputerList().get(i).getIP()+ "\n";
				text = text +cafe.getComputerList().get(i).getID() +"\t\t\t\t"+cafe.getComputerList().get(i).getIP()+ "\n";
				
			}
			}
		if(!flag)
			text ="There is no Available PC at this Time";
		//System.out.println(text);
		return text;
	}
	
	public String printMemberInfo(Member m) {
		String text = "Name:\t\t" + m.getName() + "\n" + 
				"ID:\t\t" + m.getID() + "\n" + 
				"Email:\t\t" + m.getEmail() + "\n" + 
				"Phone:\t\t" + m.getPhoneNumber() + "\n" + 
				"Adress:\t\t" + m.getAddress() + "\n" + 
				"DOB:\t\t" + m.getBirthday() + "\n" + 
				"Plan:\t\t" + m.getPlan().getName() + "\n";
		//System.out.println(text);
		return text;
	}
	
	public String printPCInfo(Computer c) {
		String text = "Station Number:\t\t" + c.getStationNum() + "\n" + 
				"ID:\t\t\t\t\t" + c.getID() + "\n" + 
				"IP:\t\t\t\t\t" + c.getIP() + "\n" + 
				"Brand:\t\t\t\t" + c.getBrand() + "\n" + 
				"Model:\t\t\t\t" + c.getProcessorModel() + "\n" + 
				"Capacity:\t\t\t\t" + c.getCapacity() + "\n" + 
				"Operation System:\t\t" + c.getOS() + "\n";
		//System.out.println(text);
		return text;
	}
	}
