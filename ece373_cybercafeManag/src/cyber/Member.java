
package cyber;


public class Member extends Client{

		private int iD;
		private MembershipPlan plan;
		private String name;
		private String address;
		private String email;
		private String birthday;
		private int phoneNum;
		
		public Member(){
			super();
			iD = 0;
			plan = null;
			name = "unknown";
			address = "unknown";
			email = "unknown";
			birthday = "unknown";
			phoneNum = 0;
		}
		
		public void setPlan(MembershipPlan aPlan){
			plan = aPlan;
		}
		
		public MembershipPlan getPlan(){
			return plan;
		}
		
		public void buyTime(int amount){
			setBalance(getBalance() + amount);
		}
		
		public void setName(String aName){
			name = aName;
		}
		
		public String getName(){
			return name;
		}
		
		public void setAddress(String anAddress){
			address = anAddress;
		}
		
		public String getAddress(){
			return address;
		}
		
		public void setEmail(String anEmail){
			email = anEmail;
		}
		
		public String getEmail(){
			return email;
		}
		
		public void setBirthday(String aBirthday){
			birthday = aBirthday;
		}
		
		public String getBirthday(){
			return birthday;
		}
		
		public void setPhoneNumber(int aPhoneNum){
			phoneNum = aPhoneNum;
		}
		
		public int getPhoneNumber(){
			return phoneNum;
		}
		
		public void setID(int iD){
			this.iD=iD;
		}
		
		
		public int getID(){
			return iD;
		}
	}



