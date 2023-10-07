
package cyber;

import java.util.ArrayList;

public class Cafe {


	private ArrayList<Computer> computerList= new ArrayList<>();
	private ArrayList<Member> membershipList= new ArrayList<>();
	private ArrayList<Service> serviceList= new ArrayList<>();
	private ArrayList<MembershipPlan> PlanList= new ArrayList<>();
	protected  ArrayList<NonMember> nonMemberdaily_List= new ArrayList<>();
	protected int time;
	protected int closingTime;
	protected Employee user;
	
	
	public  Cafe(){
		
		user =new Employee();
		time=0;
		closingTime=0;
		
	}
	
	
	public boolean timeEqualsClosingTime() {
		
		if(time==closingTime)
			return true;
		else
			return false;
	}
	
	
	public void addService(Service aService){
		serviceList.add(aService);
	}

	public ArrayList<Service> getServiceList(){
		return serviceList;
	}
	
	public void addPlan(MembershipPlan aPlan){
		PlanList.add(aPlan);
	}

	public ArrayList<MembershipPlan> getPlanList(){
		return PlanList;
	}
	
	
	public void addPC(Computer newPC){
		computerList.add(newPC);
	}
	
	public ArrayList<Computer> getComputerList(){
		return computerList;
	}
	
	public void addMember(Member newMember){
		membershipList.add(newMember);
	}

	public ArrayList<Member> getMembershipList(){
		return membershipList;
	}
	
	
	public boolean removePC(int pcID){
		boolean flag=false;
		
		for(Computer pc: computerList){
			if(pc.getID()==pcID){
				computerList.remove(pc);
				flag=true;
				break;
			}
			
		}	
		
		return flag;
	}
	
	public boolean removeMember(int memberID){
		
		boolean flag=false;
		
		for(Member mbr: membershipList){
			
			if(mbr.getID()==memberID){
				membershipList.remove(mbr);
				flag=true;
				break;
			}
			
		}	
		
		return flag;
		
		
	}
	
	
	public Computer updatePC(int pcID){
		
	
		Computer apc=null;
		
		for(Computer pc: computerList){
			if(pc.getID()==pcID){
				apc=pc;
				break;
			}
			
		}	
		
		return apc;
	}
	
	public Member updateMember(int memberID){
		
		Member amember=null;
		
		for(Member mbr: membershipList){
			
			if(mbr.getID()==memberID){
				membershipList.remove(mbr);
				amember=mbr;
				break;
			}	
		}	
		
		return amember;
		
	}
	
	public void forceQuitAll(){
		
		for(Computer pc: computerList){
			pc.setLoginTime(0);
			pc.setLogoutTime(0);
			pc.setIsOccupied(false);
			
		}
		
	}
	
	
	public Member findMember(int memberID){
		
		Member amember=null;
		
		for(Member mbr: membershipList){
			
			if(mbr.getID()==memberID){
				amember=mbr;
				break;
			}	
		}	
		
		return amember;
		
	}
}
