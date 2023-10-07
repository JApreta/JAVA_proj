package cyber;

public class Computer {


		private int iD;
		private String IP;
		private int stationNum;
		private String brand;
		private String processorModel;
		private String capacity;
		private String os;
		private boolean isOccupied;
		private double loginTime;
		private double logoutTime;
		protected Client userClient;
		
		public Computer(){
			iD = 0;
		    IP = "unknown";
		    stationNum = 0;
		    brand = "unknown";
		    processorModel = "unknown";
		    capacity = "unknown";
		    os = "unknown";
		    isOccupied = false;
		    loginTime = 0;
		    logoutTime = 0;
		}
		
		public void setID(int x){
			iD = x;
		}
		
		public int getID(){
			return iD;
		}
		
		public void setIP(String anIP){
			IP = anIP;
		}
		
		public String getIP(){
			return IP;
		}
		
		public void setStationNum(int x){
			stationNum = x;
		}
		
		public int getStationNum(){
			return stationNum;
		}
		
		public void setBrand(String aBrand){
			brand = aBrand;
		}
		
		public String getBrand(){
			return brand;
		}
		
		public void setProcessorModel(String aModel){
			processorModel = aModel;
		}
		
		public String getProcessorModel(){
			return processorModel;
		}
		
		public void setCapacity(String aCapacity){
			capacity = aCapacity;
		}
		
		public String getCapacity(){
			return capacity;
		}
		
		public void setOS(String anOS){
			os = anOS;
		}
		
		public String getOS(){
			return os;
		}
		
		public void setIsOccupied(boolean occupied){
			isOccupied = occupied;
		}
		
		public boolean getIsOccupied(){
			return isOccupied;
		}
		
		public void setLoginTime(double time){
			loginTime = time;
		}
		
		public double getLoginTime(){
			return loginTime;
		}
		
		public void setLogoutTime(double time){
			logoutTime = time;
		}
		
		public double getLogoutTime(){
			return logoutTime;
		}
		
	}



