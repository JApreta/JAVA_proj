package cyber;

/*
 * 
 * Tests Client, Member, Service, and Computer classes
 * 
 * 
 */
public class Driver1 {
	public static void main(String[] args) 
	{
	Member m1 = new Member();
	Service s1 = new Service();
	Computer c1 = new Computer();
	MembershipPlan mp1 = new MembershipPlan();
	
	m1.setPlan(mp1);
	m1.setName("Steve");
	m1.setAddress("Set address");
	m1.setEmail("s@email.com");
	m1.setBirthday("7/7/7");
	m1.setPhoneNumber(1234567);
	m1.setID(11111);
	m1.buyTime(50);
	m1.setPC(c1);
	
	s1.setName("Service");
	s1.setPrice(3.33);
	
	c1.setID(13);
	c1.setIP("192.0.0");
	c1.setStationNum(1);
	c1.setBrand("Intel");
	c1.setProcessorModel("i7-3770");
	c1.setCapacity("16 GB");
	c1.setOS("Linux");
	c1.setIsOccupied(true);
	c1.setLoginTime(1230);
	c1.setLogoutTime(1230);
	
	System.out.println("Member Test");
	System.out.println(m1.getPlan().getName());
	System.out.println(m1.getName());
	System.out.println(m1.getAddress());
	System.out.println(m1.getEmail());
	System.out.println(m1.getBirthday());
	System.out.println(m1.getPhoneNumber());
	System.out.println(m1.getID());
	System.out.println(m1.getBalance());
	System.out.println(m1.getPC().getIP());
	System.out.println();
	
	System.out.println("Service Test");
	System.out.println(s1.getName());
	System.out.println(s1.getPrice());
	System.out.println();
	
	System.out.println("Computer Test");
	System.out.println(c1.getID());
	System.out.println(c1.getIP());
	System.out.println(c1.getStationNum());
	System.out.println(c1.getBrand());
	System.out.println(c1.getProcessorModel());
	System.out.println(c1.getCapacity());
	System.out.println(c1.getOS());
	System.out.println(c1.getIsOccupied());
	System.out.println(c1.getLoginTime());
	System.out.println(c1.getLogoutTime());
	
	}
}
