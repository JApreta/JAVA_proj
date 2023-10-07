package cyber;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * we are using minutes instead of hours in the timer for time sake during simulation
 * 
*/
public class CafeGUI extends Application{
	
	int client=0;
	private boolean haveMembers=false;
	private BorderPane bp ;
	private Cafe cafe= new Cafe();
	private boolean userValidation=false;
	private String givenName,givenAddress,givenEmail,givenBirthD,givenPhoneNum,givenPlanName;
	private String giveniP,givenStationNum,givenBrand,givenprocessorModel,givenCapacity,givenOs;
	private int pcIDgen=100, memberIDgen=200;
	private ComboBox <String> PlansCB = new ComboBox<>();
	private ComboBox <String> MembersCB = new ComboBox<>();
	private ComboBox <String> PCCB = new ComboBox<>();
	private GridPane mainGPane= new GridPane();
	private TextArea infoWindow;
	int col=0,row=0;
	private MenuBar menubar= new MenuBar();
	private Menu memberMenu = new Menu("Member");
	private Menu pcMenu = new Menu("Computer");
	private Menu recordMenu = new Menu("Record");
	private Menu userMenu = new Menu("User");
	private Menu delPCsubMenu=new Menu("Delete PC");
	private MembershipPlan buyPlan=new MembershipPlan();
	private RadioButton rb1= new RadioButton(" Member ");
	private RadioButton rb2= new RadioButton(" Non Member ");
	private boolean logOutflagmember=false,logOutflagNonmember=false;
	private ArrayList <TextField>timTF = new ArrayList<>();
	private ToggleGroup gpclient = new ToggleGroup();
	private ToggleGroup gpPC = new ToggleGroup();
	private Record cafeRecord=new Record();
	private ArrayList <Button> pcs= new ArrayList<>();	
	private ArrayList <Integer> cnt= new ArrayList<>();
	private String pctoDelete;
	private String membertoDelete;
	private String givenPCiD;
	private String givenMemberiD;
	private String memberData;
	private Label memberlabel;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage myStage) throws InterruptedException 

	{
		
		
		initPlans();
		
		infoWindow=new TextArea();
		
		infoWindow.setEditable(false);
		for (int i = 1; i <= 8; i++) {
			   System.out.print(-3+8*i+ " ");
			}
		
		final String FONT_NAME = "Georgia";
		myStage.getIcons().add(new Image("/res/logoC.png"));
		myStage.setTitle("CyberCafe");
		myStage.setOnCloseRequest(e->e.consume());
		PlansCB.getItems().addAll("Plan1","Plan2","Plan3","Plan4");
		
		
		
		memberMenu.getItems().add(new MenuItem("Add Member"));
		memberMenu.getItems().add(new MenuItem("Update Member"));
		memberMenu.getItems().add(new MenuItem("Delete Member"));
		memberMenu.getItems().add(new MenuItem("Member Data"));
		
		
		//delPCsubMenu.getItems().add(MembersCB);
		pcMenu.getItems().add(new MenuItem("Add PC"));
		pcMenu.getItems().add(new MenuItem("Update PC"));
		pcMenu.getItems().add(new MenuItem("Delete PC"));
		pcMenu.getItems().add(new MenuItem("PC Data"));
		
		
		
		
		recordMenu.getItems().add(new MenuItem("All PCs"));
		recordMenu.getItems().add(new MenuItem("All Members"));
		recordMenu.getItems().add(new MenuItem("All Available Pc"));
		//recordMenu.getItems().add(new MenuItem("All Available Pc"));
		
		userMenu.getItems().add(new MenuItem("Set Closure Time"));
		userMenu.getItems().add(new MenuItem("Logout"));
		
		menubar.getMenus().addAll(memberMenu,pcMenu,recordMenu,userMenu);
		
		menubar.setStyle("-fx-background-color: silver;");
		
		(new MenuListener()).actionPerformed();
		bp = new BorderPane();
		bp.setTop(menubar);
		bp.setStyle("-fx-background-color: aliceblue;");
		
		
		mainGPane.setVgap(10);
		mainGPane.setHgap(10);
		mainGPane.setPadding(new Insets(25, 25, 25, 25));
		
		//mainGPane.getChildren().get(0).setStyle("-fx-border-color: red;-fx-border-width: 5px;");
		gpclient.getToggles().addAll(rb1,rb2);
		bp.setCenter(mainGPane);
		
	
		
		Scene scene = new Scene(bp, 650, 350); 
		
		myStage.setScene(scene);
	
		
        myStage.show();    
        	
        	(new LoginWindow()).userLogin();//call the login window username Adim passcode 123A
       
        
        	
	}

	public Button pcIcon(){
		
		Button bt= new Button();
		bt.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/res/computer.png"),50,50,true,true)));
		bt.setStyle("-fx-border-color: green;  -fx-border-width: 5px;");
		
		pcs.add(bt);
		assigmentPC();
		
		return pcs.get(pcs.size()-1);
	}
	
	private class MenuListener 
	{
		public void actionPerformed(){
			
			//add Member sub-menu
			memberMenu.getItems().get(0).setOnAction((ActionEvent e)->{
				addMember();
		});
		
			//Update Member sub-menu
			memberMenu.getItems().get(1).setOnAction((ActionEvent e)->{
				displayUpdateMember();
		});
		
			
			//Delete Member sub-menu
			memberMenu.getItems().get(2).setOnAction((ActionEvent e)->{
				
				
				if(cafe.getMembershipList().size()==0)
					confirmationDialog("Error Deleting Member","Member List is Empty");
				else{
					deleteMember();
				
				//PCCB.valueProperty().set(null);
				MembersCB.getChildrenUnmodifiable().remove(membertoDelete);
				MembersCB.getSelectionModel().clearSelection();//clear selection
				
				
				
					
				if(MembersCB.getItems().indexOf(membertoDelete)!=-1){
						//MembersCB.getItems().clear();
						
						if(cafe.removeMember(Integer.parseInt(membertoDelete))){
							
						confirmationDialog("Deleting Member","Member deleted from the System");
						
						}
					
					else
					confirmationDialog("Error Deleting Member","You Must Select a Member Id");
					
			}
				membertoDelete="-1";
			
				}		});
		
			//Member Data sub-menu
			memberMenu.getItems().get(3).setOnAction((ActionEvent e)->{
				displayMember();

		});
		
			
			//add PC sub-menu
			pcMenu.getItems().get(0).setOnAction((ActionEvent e)->{
				addComputer();
		});
			//update PC sub-menu
			pcMenu.getItems().get(1).setOnAction((ActionEvent e)->{
				displayUpdatePC();
		});
			
			//delete PC sub-menu
			pcMenu.getItems().get(2).setOnAction((ActionEvent e)->{
				
				cafeRecord.setCafeobj(cafe);
				if(cafe.getComputerList().size()==0)
					confirmationDialog("Error Deleting PC","Computer List is Empty");
				else{
				deletePC();
				
				//PCCB.valueProperty().set(null);
				PCCB.getChildrenUnmodifiable().remove(pctoDelete);
				PCCB.getSelectionModel().clearSelection();//clear selection
				
				
				
					
				if(PCCB.getItems().indexOf(pctoDelete)!=-1){
					//if(pcs.get(Integer.parseInt(pctoDelete)%10).getStyle().equals("-fx-border-color: green;  -fx-border-width: 5px;")){
					if(!cafe.getComputerList().get(Integer.parseInt(pctoDelete)%10).getIsOccupied()){	
						PCCB.getItems().clear();
						
						if(cafe.removePC(Integer.parseInt(pctoDelete))){
							
						//cafe.getComputerList().remove(Integer.parseInt(pctoDelete)%10);
						pcs.remove(Integer.parseInt(pctoDelete)%10);
						mainGPane.getChildren().remove(Integer.parseInt(pctoDelete)%10);
						confirmationDialog("Deleting PC","Computer deleted from the System");
						
						pcIDgen=100;
						
						if(row>1 && col==1)row--;
						for(Computer pc: cafe.getComputerList()){
							PCCB.getItems().add(Integer.toString(pcIDgen));
							pc.setID(pcIDgen++);
						}
						}
					}
					else
					confirmationDialog("Error Deleting PC","This PC can't be delete because a client is using it");
					
			}
				pctoDelete="-1";
			
				}	

				
		});
			//PC DATA
			pcMenu.getItems().get(3).setOnAction((ActionEvent e)->{
				displayPC();
		});
			
			//printAllPC sub-menu
			recordMenu.getItems().get(0).setOnAction((ActionEvent e)->{
				
				cafeRecord.setCafeobj(cafe);
				
				confirmationDialog("List of All Computers",cafeRecord.printAllPC() );

		});
		
			//printAllMember sub-menu
			recordMenu.getItems().get(1).setOnAction((ActionEvent e)->{
				cafeRecord.setCafeobj(cafe);
				
				confirmationDialog("List of All Members",cafeRecord.printAllMember() );
		});
			//printAll available PC sub-menu
			recordMenu.getItems().get(2).setOnAction((ActionEvent e)->{
				cafeRecord.setCafeobj(cafe);
				
				confirmationDialog("Available PC",cafeRecord.printAllAvailablePC() );
		});
				
			//add Member sub-menu
			userMenu.getItems().get(0).setOnAction((ActionEvent e)->{
				setClosureTime();
		});
		
			//Logout
			userMenu.getItems().get(1).setOnAction((ActionEvent e)->{
				System.exit(0);
		});
			
			
		
		}
		
	}
	
	private  class LoginWindow {
		
		private String givenUsername;
		private String givenPassword;
		
		Stage window=new Stage();
		
		
		
		public void userLogin(){
			
			window.setOnCloseRequest(e->e.consume());
			
			
			Label userNamelb= new Label("Username");
			Label passwordlb= new Label("Password");
			
			TextField userNametf= new TextField();
			TextField passwordtf= new TextField();
			
			Button ok_button= new Button("OK");
			Button cancel_button= new Button("Cancel");
			
			ok_button.setMinWidth(50);
			cancel_button.setMinWidth(50);
			
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
			
		
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Adm Login");
			window.setMinHeight(250);
			window.setMaxWidth(300);
			
			cancel_button.setOnAction((ActionEvent e)->{ System.exit(0);});	
					
			
			ok_button.setOnAction((ActionEvent e)->
			{
				
				givenUsername=userNametf.getText();
				givenPassword=passwordtf.getText();
				
				
				if(givenUsername.equals(cafe.user.getUsername()) && givenPassword.equals(cafe.user.getPassword())){
					userValidation=true;
					window.close(); }
				
				userNametf.clear();
				passwordtf.clear(); });			
			
			
			grid.add(userNamelb,1, 0);
			grid.add(userNametf,2,0);
			
			grid.add(passwordlb,1, 2);
			grid.add(passwordtf,2,2);
			
			grid.add(ok_button,1, 4);
			grid.add(cancel_button,1,5);
				
			Scene scene = new Scene(grid, 300, 50); // Width, Height
			
			window.setScene(scene);
			
			window.showAndWait();;
		}
		
		
	}
	
	public void addMember(){
		
		 Stage window=new Stage();
		
		TextField nametf= new TextField();
		TextField addresstf= new TextField();
		TextField emailtf= new TextField();
		
		
		TextField birthDtf= new TextField();
		TextField phoneNumtf= new TextField();
		
		
		Button add_button= new Button();
		add_button.setMinWidth(50);
		
		Button addPlan_button= new Button("Add Plan");
		addPlan_button.setMinWidth(100);
		
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
		
	
		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("/res/AM.png"));
		window.setTitle("Member Information");
		window.setMinHeight(360);
		window.setMaxWidth(350);
		window.setResizable(false);
	
				
		
		add_button.setOnAction((ActionEvent e)->
		{
			
			givenName=nametf.getText();
			givenAddress=addresstf.getText();
			givenEmail=emailtf.getText();
			givenBirthD=birthDtf.getText();
			givenPhoneNum=phoneNumtf.getText();
			givenPlanName=PlansCB.getValue();
			
			
			
			if( isNumeric(givenPhoneNum)== true && givenName.isEmpty()==false 
					&& givenAddress.isEmpty()==false && givenEmail.isEmpty()==false
				    && givenBirthD.isEmpty()== false && givenPhoneNum.isEmpty()==false
				    &&!isSpace(givenName)&&!isSpace(givenAddress)&&!isSpace(givenEmail)
				    &&!isSpace(givenBirthD)&&!isSpace(givenPhoneNum)){
				
				Member aMember= new Member();
				
				aMember.setID(memberIDgen);
				aMember.setName(givenName);
				aMember.setAddress(givenAddress);
				aMember.setEmail(givenEmail);
				aMember.setBirthday(givenBirthD);
				aMember.setPhoneNumber(Integer.parseInt(givenPhoneNum));
				aMember.setPlan(buyPlan);
				aMember.timeBalance=buyPlan.getTotalHours();
				buyPlan=new MembershipPlan();;
							
				MembersCB.getItems().add(Integer.toString(memberIDgen));
				
				
				cafe.addMember(aMember);
				memberIDgen++;
				
				confirmationDialog("Confirmation","New Member Added");
				window.close(); 
				}
		
			
			
			nametf.clear();
			addresstf.clear();
			emailtf.clear();
			birthDtf.clear();
			phoneNumtf.clear();
		
		
		});			
		
		
		addPlan_button.setOnAction((ActionEvent e)->{
			memberBuyTime();
			
		});	
		
		grid.add(new Label("Name"),1, 0);
		grid.add(nametf,2,0);
		
		grid.add(new Label("Address"),1, 2);
		grid.add(addresstf,2,2);
		
		grid.add(new Label("Email"),1, 3);
		grid.add(emailtf,2,3);
		
		grid.add(new Label("Birthdate"),1, 4);
		grid.add(birthDtf,2,4);
		
		grid.add(new Label("Phone Number"),1, 5);
		grid.add(phoneNumtf,2,5);
		
		grid.add(addPlan_button,1, 6);
				
		add_button.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/res/friend.png"),50,50,true,true)));
		
		grid.add(add_button,3, 8);
		
		grid.setStyle("-fx-background-color: skyblue;");
		Scene scene = new Scene(grid, 300, 50); // Width, Height
		
		window.setScene(scene);
		
		window.showAndWait();;
	}
	
	public void addComputer(){
		
			Stage window=new Stage();
			
			TextField iPtf= new TextField();
			TextField stationNumtf= new TextField();
			TextField brandtf= new TextField();
			
			
			TextField processorModeltf= new TextField();
			TextField capacitytf= new TextField();
			
			TextField ostf= new TextField();
			
			
			Button add_button= new Button("ADD");
			
			
			add_button.setMinWidth(50);
			
			
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
			
		
			window.initModality(Modality.APPLICATION_MODAL);
			window.getIcons().add(new Image("/res/addPC.png"));
			window.setTitle("Computer Information");
			window.setMinHeight(360);
			window.setMaxWidth(350);
			window.setResizable(false);
		
				
			
			add_button.setOnAction((ActionEvent e)->
			{
				
				giveniP=iPtf.getText();
				givenStationNum=stationNumtf.getText();
				givenBrand=brandtf.getText();
				givenprocessorModel=processorModeltf.getText();
				givenCapacity=capacitytf.getText();
				givenOs=ostf.getText();
				
				
				if(isNumeric(givenStationNum)== true&&giveniP.isEmpty()==false && givenStationNum.isEmpty()==false && givenBrand.isEmpty()==false
						&& givenprocessorModel.isEmpty()==false  && givenCapacity.isEmpty()==false && givenOs.isEmpty()==false
						&&!isSpace(giveniP)&&!isSpace(givenStationNum)&&!isSpace(givenBrand)&&!isSpace(givenprocessorModel)
						&&!isSpace(givenCapacity)&&!isSpace(givenOs)){
					
					PCCB.getItems().add(Integer.toString(pcIDgen));
					Computer pc=new Computer();
					pc.setID(pcIDgen++);
					pc.setIP(giveniP);
					pc.setStationNum(Integer.parseInt(givenStationNum));
					pc.setBrand(givenBrand);
					pc.setProcessorModel(givenprocessorModel);
					pc.setCapacity(givenCapacity);
					pc.setOS(givenOs);
					
					cafe.addPC(pc);
					
					
					mainGPane.add(pcIcon(),row,col++);
					if(col==3){
						row++;
						col=0;}
					//add code to check for field with space and ensure that the 
					window.close();
					}

				
				iPtf.clear();
				stationNumtf.clear();
				brandtf.clear();
				processorModeltf.clear();
				capacitytf.clear();
				ostf.clear();
			
			
			});			
			
			
			grid.add(new Label("IP"),1, 0);
			grid.add(iPtf,2,0);
			
			grid.add(new Label("Station#"),1, 2);
			grid.add(stationNumtf,2,2);
			
			grid.add(new Label("Brand"),1, 3);
			grid.add(brandtf,2,3);
			
			grid.add(new Label("Processor Model"),1, 4);
			grid.add(processorModeltf,2,4);
			
			grid.add(new Label("Capacity"),1, 5);
			grid.add(capacitytf,2,5);
			
			grid.add(new Label("OS"),1, 6);
			grid.add(ostf,2,6);
			
			add_button.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/res/add-computer-icon-44932.png"),50,50,true,true)));
			
			grid.add(add_button,3, 8);
			
			grid.setStyle("-fx-background-color: skyblue;");
			Scene scene = new Scene(grid, 300, 50); // Width, Height
			
			window.setScene(scene);
			
			window.showAndWait();;
		
		
		
	}
	
	public void confirmationDialog(String title, String message){
		
		Stage window= new Stage();
		window.setTitle(title);
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/log.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		Button ok_button= new Button("OK");
		ok_button.setMinWidth(50);
		
		ok_button.setOnAction(e-> window.close());
		
		VBox vb= new VBox(20);
		vb.setPadding(new Insets(15, 15, 15, 15));
		vb.getChildren().addAll(new Label(message), ok_button);
	
		vb.setAlignment(Pos.CENTER);
	
	
		
		Scene scene = new Scene(vb); 
		
		window.setScene(scene);
		
		window.showAndWait();;
	}	
		
	public void assigmentPC(){
		
		
		for(Button bnt: pcs ){
			
			bnt.setOnAction((ActionEvent e)->{
					
				
				if(bnt.getStyle().equals("-fx-border-color: green;  -fx-border-width: 5px;")){
					
					 InputDialog();
					 
					 if(rb1.isSelected()|| rb2.isSelected()){
						 
						 if(rb1.isSelected() && (!cafe.timeEqualsClosingTime() || cafe.time==0)){//if PC is being assigned to a Member before closure time
							 
							 InputDialog();
							 
							 if(this.haveMembers){
								 
								 client = Integer.parseInt(MembersCB.getValue());
								 MembersCB.valueProperty().set(null);
							 
								 for(Member mbr: cafe.getMembershipList()){
								 
									 if(mbr.getID()==client && mbr.timeBalance>0){
									 
										 for(Computer pc: cafe.getComputerList()){
											 if(pc.getID()%10 == pcs.indexOf(bnt) ){
												 cafe.getComputerList().get(pc.getID()%10);
												 pc.setIsOccupied(true);
												 pc.userClient= new Member();
												 pc.userClient=mbr;
												 pc.userClient.isMember=true;
												 bnt.setStyle("-fx-border-color: red;  -fx-border-width: 5px;");
											
											
													
												 Timer timer = new Timer();
												 timer.schedule(new TimerTask(){
												 int cntr=0;
									        	 public void run() {
									        		cntr++;
													
													cnt.add(pcs.indexOf(bnt),cntr);
													timTF.add(new TextField());
													timTF.get(pcs.indexOf(bnt)).setEditable(false);
													timTF.get(pcs.indexOf(bnt)).setText(Integer.toString(cnt.get(pcs.indexOf(bnt))/60)+":"+cnt.get(pcs.indexOf(bnt))%60);
										            
										            
										            if(cnt.get(pcs.indexOf(bnt))/60==pc.userClient.getBalance()){
										            	
										            	pc.userClient.setBalance(pc.userClient.getBalance()-(cnt.get(pcs.indexOf(bnt))/60));
										            	bnt.setStyle("-fx-border-color: green;  -fx-border-width: 5px;");
										            	pc.setIsOccupied(false);
										            	//System.out.println("Time's up!");
										            	//timTF.get(pcs.indexOf(bnt)).setText("");
										            	timer.cancel(); //Terminate the timer thread
										        }
										            if(logOutflagmember){
										            	System.out.println("Member time before logout"+pc.userClient.getBalance());
										            	pc.userClient.setBalance(pc.userClient.getBalance()-(cnt.get(pcs.indexOf(bnt))/60));
										    			
										            	if(cnt.get(pcs.indexOf(bnt))%60>0)
										            		pc.userClient.setBalance(pc.userClient.getBalance()-1);
										            	//System.out.println("Member time after logout"+pc.userClient.getBalance());	
										            	pc.setIsOccupied(false);
										            	bnt.setStyle("-fx-border-color: green;  -fx-border-width: 5px;");
										            	//System.out.println("Time's up!");
										            	
										            	timer.cancel(); //Terminate the timer thread
										            	logOutflagmember=false;
										        }
										            }
									        },1000,1000);// pc.user.getBalance()*1000*60);
											
										
										 }
									 }
																		 }
								 
								 else if(mbr.getID()==client && mbr.timeBalance==0){
									 
									 
									 //if member does not have balance call buy member time
									 memberBuyTime();
									 mbr.timeBalance=buyPlan.getTotalHours();
									 mbr.setPlan(buyPlan);
									 buyPlan=null;
									 //flag=true;
								 }
							 }
							 
							 
							
							 
							 }
							 
						 }
						 
						 else if(rb2.isSelected() && (!cafe.timeEqualsClosingTime() || cafe.time==0)){//if PC is being assig to a NON MEMBER before closure time
							 
							 //buy time and make pc red 
							 nonMemberBuyTime();
							 for(Computer pc: cafe.getComputerList()){
								 
								 if(pc.getID()%10 == pcs.indexOf(bnt)  && pc.getIsOccupied()==false ){
									 cafe.getComputerList().get(pc.getID()%10);
									pc.setIsOccupied(true);
									pc.userClient= new NonMember();
									pc.userClient=cafe.nonMemberdaily_List.get(cafe.nonMemberdaily_List.size()-1);
									pc.userClient.isNonMember=true;
									bnt.setStyle("-fx-border-color: red;  -fx-border-width: 5px;");
									
											
									Timer timer = new Timer();
							        timer.schedule(new TimerTask(){
							        	int cntr=0;
							        	public void run() {
							        		cntr++;
											
											cnt.add(pcs.indexOf(bnt),cntr);
											
											timTF.add(new TextField());
											timTF.get(pcs.indexOf(bnt)).setEditable(false);
											
											timTF.get(pcs.indexOf(bnt)).setText(Integer.toString(cnt.get(pcs.indexOf(bnt))/60)+":"+cnt.get(pcs.indexOf(bnt))%60);
								            if(cnt.get(pcs.indexOf(bnt))/60==pc.userClient.getBalance() || cafe.timeEqualsClosingTime() && cafe.time>0){
								            	
								            	pc.userClient.setBalance(pc.userClient.getBalance()-(cnt.get(pcs.indexOf(bnt))/60));
								            	pc.setIsOccupied(false);
								            	bnt.setStyle("-fx-border-color: green;  -fx-border-width: 5px;");
								            	//System.out.println("Time's up!");
								            	//timTF.get(pcs.indexOf(bnt)).setText("");
								            	timer.cancel(); //Terminate the timer thread
								        }
								            
								            if(logOutflagNonmember){
								            	//System.out.println("Non Member time before logout"+pc.userClient.getBalance());
								            	pc.userClient.setBalance(0);
								            	//System.out.println("Non Member time after logout"+pc.userClient.getBalance());
								            	pc.setIsOccupied(false);
								            	bnt.setStyle("-fx-border-color: green;  -fx-border-width: 5px;");
								            	//System.out.println("Time's up!");
								            	timer.cancel(); //Terminate the timer thread
								            	logOutflagNonmember=false;
								        }
								            
								            
								            }
							        },1000,1000);
									
								
								 }
							 }
							 
							 
							 
						 }
						 
						 
						 rb1.setSelected(false);
						 rb2.setSelected(false);
										}
				}
				
				else if(bnt.getStyle().equals("-fx-border-color: red;  -fx-border-width: 5px;")){
					
					 for(Computer pc: cafe.getComputerList()){
						 if(pc.getID()%10 == pcs.indexOf(bnt) ){
							 
							 System.out.println(cnt.get(pcs.indexOf(bnt))/60+":"+cnt.get(pcs.indexOf(bnt))%60);
					         
				 
							 Stage window= new Stage();
								window.setTitle("Current Time ");
								window.setMinWidth(350);
								window.getIcons().add(new Image("/res/log.png"));
								window.initModality(Modality.APPLICATION_MODAL);
								
								Button oky_button= new Button("OK");
								oky_button.setMinWidth(50);
								Button logOut_button= new Button("logout");
								logOut_button.setMinWidth(50);
								
								oky_button.setOnAction(e1-> window.close());
								
								logOut_button.setOnAction((ActionEvent e2)->{
									if(pc.userClient.isNonMember)
										logOutflagNonmember=true;
									
									if(pc.userClient.isMember)
										logOutflagmember=true;
									bnt.setStyle("-fx-border-color: green;  -fx-border-width: 5px;");
									window.close();
									
									
								});
								
								VBox vb= new VBox(20);
								vb.setPadding(new Insets(15, 15, 15, 15));
								vb.getChildren().addAll(timTF.get(pcs.indexOf(bnt)),logOut_button, oky_button);
								
								vb.setAlignment(Pos.CENTER_LEFT);
								Scene scene = new Scene(vb); 
								
								window.setScene(scene);
								window.showAndWait();
								
							 
				 }
					}
					
				}
					
			});		
			
		}
		
	}
	
	public String InputDialog(){
			
		
		Stage window= new Stage();
		window.setTitle("Assign PC ");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/log.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		Button ok_button= new Button("OK");
		ok_button.setMinWidth(50);
		
		ok_button.setOnAction(e-> window.close());
		
		VBox vb= new VBox(20);
		vb.setPadding(new Insets(15, 15, 15, 15));
		

		
		
		if(rb1.isSelected()|| rb2.isSelected()){
			
			if(cafe.getMembershipList().size()!=0){
				vb.getChildren().addAll(new Label("Member ID:"),MembersCB, ok_button);
				this.haveMembers=true;
				}
			
			
			else
			
				vb.getChildren().addAll(new Label("Error assigning PC: There is not registed Member in the System") ,ok_button);
			
		}
		
	 
		else
			vb.getChildren().addAll(new Label("Would you like to Assign tis PC to:"),rb1,rb2, ok_button);
		
		
	
		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
		
		window.setScene(scene);
		window.showAndWait();
		
			
	
		return "nada" ;
	}	
	
	public void deletePC(){
			
		
		Stage window= new Stage();
		window.setOnCloseRequest(e->e.consume());
		window.setTitle("Delete Computer ");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/log.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		Button ok_button= new Button("OK");
		ok_button.setMinWidth(50);
		
		Button cancel_button= new Button("Cancel");
		cancel_button.setMinWidth(50);
		
		ok_button.setOnAction((e)->{
			pctoDelete= PCCB.getValue();
			window.close();});
		
		cancel_button.setOnAction((e)->{
			window.close();});
		
		VBox vb= new VBox(20);
		vb.setPadding(new Insets(15, 15, 15, 15));
		

		
		
		
			vb.getChildren().addAll(new Label("Select the Computer Id that you wish to remove: "),PCCB, ok_button,cancel_button);
		
		
	
		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
		
		window.setScene(scene);
		window.showAndWait();
		
			
	
		
	}	
	
	public void deleteMember(){
			
		
		Stage window= new Stage();
		window.setOnCloseRequest(e->e.consume());
		window.setTitle("Delete Computer ");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/log.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		Button ok_button= new Button("OK");
		ok_button.setMinWidth(50);
		
		Button cancel_button= new Button("Cancel");
		ok_button.setMinWidth(50);
		
		ok_button.setOnAction((e)->{
			membertoDelete= MembersCB.getValue();
			window.close();});
		
		cancel_button.setOnAction((e)->{
			window.close();});
		
		VBox vb= new VBox(20);
		vb.setPadding(new Insets(15, 15, 15, 15));
	
		vb.getChildren().addAll(new Label("Select the Member Id that you wish to remove: "),MembersCB, ok_button, cancel_button);
	
		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
		
		window.setScene(scene);
		window.showAndWait();
		
			
	
		
	}	

	public void displayPC(){
		Stage window = new Stage();
		window.setTitle("Select PC");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/computer.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		
		
		if(PCCB.getItems().size() != 0){

		Button ok_button = new Button("OK");
		ok_button.setMinWidth(50);

		ok_button.setOnAction((ActionEvent e)-> {
			
			givenPCiD=PCCB.getValue();
			
			if(PCCB.getSelectionModel().getSelectedIndex()!=-1)
				PCDetails(givenPCiD);
			PCCB.getSelectionModel().clearAndSelect(-1);;
			window.close();
			
		 });
		
		
		VBox vb = new VBox(20); 
		vb.setPadding(new Insets(15,15,15,15));

		vb.getChildren().addAll(new Label("Select PC to view"), PCCB, ok_button);

		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
				
		window.setScene(scene);
		window.showAndWait();
		}
		else{
			confirmationDialog("Error in displaying PCs", "There are no PCs to display");
		}
	}
	
	public void PCDetails(String ID){
		int x = Integer.parseInt(ID);
		
		Computer aPC = cafe.updatePC(x);
		
		Stage window = new Stage();
		window.setTitle("PC Details");

		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("/res/computer.png"));
		window.setTitle("PC " + aPC.getID());
		window.setMinHeight(200);
		window.setMaxWidth(350);
		window.setResizable(false);
		
		Button ok_button = new Button("OK");
		ok_button.setMinWidth(50);

		ok_button.setOnAction((ActionEvent e)-> {
			window.close();
		});

		
		cafeRecord.setCafeobj(cafe);
		
		VBox vb = new VBox(20); 
		vb.setPadding(new Insets(15,15,15,15));

		vb.getChildren().addAll(new Label(cafeRecord.printPCInfo(aPC)), ok_button);

		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
		


		vb.setStyle("-fx-background-color: skyblue;");

		

		window.setScene(scene);
		window.show();
	}
	
	public void displayMember(){
		Stage window = new Stage();
		window.setTitle("Select Member");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/AM.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		if(MembersCB.getItems().size() != 0){
		Button ok_button = new Button("OK");
		ok_button.setMinWidth(50);

		ok_button.setOnAction((ActionEvent e)-> {
			givenMemberiD=MembersCB.getValue();
			
			if(MembersCB.getSelectionModel().getSelectedIndex()!=-1)
				memberDetails(givenMemberiD);
			MembersCB.getSelectionModel().clearAndSelect(-1);;
			window.close();
		 });
		

		VBox vb = new VBox(20); 
		vb.setPadding(new Insets(15,15,15,15));

		vb.getChildren().addAll(new Label("Select Member to view"), MembersCB, ok_button);

		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
				
		window.setScene(scene);
		window.showAndWait();;
		}
		else{
			confirmationDialog("Error in Displaying Members", "There are no members to display");
		}
	}
	
	public void memberDetails(String inID){
		
		int x = Integer.parseInt(inID);
		Member aMember = cafe.findMember(x);
		Stage window = new Stage();
		window.setTitle("Member Details");
		window.getIcons().add(new Image("/res/AM.png"));
		

		Button addHours = new Button("Add Hours");
		addHours.setMinWidth(50);
		
		addHours.setOnAction((ActionEvent e) -> {
			Stage popup=new Stage();
			
			if(aMember.getBalance()>0){
			
			TextField Price_hourtf= new TextField(Double.toString(aMember.getPlan().getPricePerHour()));
			TextField Total_Pricetf= new TextField("00.00");
			TextField Total_hourstf= new TextField();
			
			Price_hourtf.setEditable(false);
			Total_Pricetf.setEditable(false);
			Total_hourstf.setEditable(true);
			
			
			//Price_hourtf.setMaxSize(100, 50);
			
			Button buy_button= new Button("Buy");
			buy_button.setMinWidth(50);
			//buy_button.setMinHeight(50);
			
			Button totalPrice_button= new Button("Total");
			totalPrice_button.setMinWidth(50);
			//buy_button.setMinHeight(50);
			
			
			GridPane grid2 = new GridPane();
			grid2.setHgap(10);
			grid2.setVgap(10);
			grid2.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
			
		
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle(" Add Hours ");
			popup.setMinHeight(250);
			popup.setMaxWidth(350);
			popup.setResizable(false);
		
					
			
			grid2.add(new Label("Price/hour"),1, 0);
			grid2.add(Price_hourtf,2,0);
			
			grid2.add(new Label("Total Price"),1, 4);
			grid2.add(Total_Pricetf,2,4);
			
			grid2.add(new Label("Total Hours"),1, 3);
			grid2.add(Total_hourstf,2,3);
			
			totalPrice_button.setOnAction((ActionEvent f)->{ 
				if(!Total_hourstf.getText().isEmpty())
					Total_Pricetf.setText(Double.toString(Double.parseDouble(Total_hourstf.getText()) * aMember.getPlan().getPricePerHour()));});	
			
			buy_button.setOnAction((ActionEvent f)->{ 
				
					aMember.buyTime(Integer.parseInt(Total_hourstf.getText()));
					memberData="Name:\t\t\t" + aMember.getName()+"\n"+
							   "ID:\t\t\t\t" + aMember.getID()+"\n"+
							   "Total Hours: \t\t" + aMember.getBalance()+"\n"+
							   "Price Per Hour: $\t" + aMember.getPlan().getPricePerHour() + "/Hr";
									
						
					popup.close();
					window.close();
					memberDetails(inID);
					});	
		

			grid2.add(buy_button,3, 6);
			grid2.add(totalPrice_button,1, 6);
			
			grid2.setStyle("-fx-background-color: skyblue;");
			Scene s = new Scene(grid2, 300, 50); 
			
			popup.setScene(s);
			
			popup.showAndWait();;
			}
			else{
				confirmationDialog("Error Adding Hours", "Member does not have an Active Plan");

			}	
		});
		
		Button changePlan = new Button("Upgrade/Renew Plan");
		changePlan.setMinWidth(50);
		
		changePlan.setOnAction((ActionEvent e) -> {
			Stage window3=new Stage();
			
			TextField Price_hourtf= new TextField();
			TextField Total_Pricetf= new TextField();
			TextField Validation_Daystf= new TextField();
			
			//PlansCB.setValue(null);
			
			PlansCB.getItems().clear();PlansCB.getItems().addAll("Plan1","Plan2","Plan3","Plan4");
			
			
			Price_hourtf.setEditable(false);
			Total_Pricetf.setEditable(false);
			Validation_Daystf.setEditable(false);
			
			
			//Price_hourtf.setMaxSize(100, 50);
			
			Button buy_button= new Button("Buy");
			buy_button.setMinWidth(50);
			buy_button.setMinHeight(50);
			

			GridPane grid3 = new GridPane();
			grid3.setHgap(10);
			grid3.setVgap(10);
			grid3.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
			
		
			window3.initModality(Modality.APPLICATION_MODAL);
			window3.setTitle(" Member Upgrade Plan ");
			window3.setMinHeight(300);
			window3.setMaxWidth(350);
			window3.setResizable(false);
		
					
			
			buy_button.setOnAction((ActionEvent f)->
			{
				for(MembershipPlan mp: cafe.getPlanList()){
					if(mp.getName().equals(PlansCB.getSelectionModel().getSelectedItem()) ){
						aMember.setPlan(mp);
						aMember.buyTime(mp.getTotalHours());
						memberData="Name:\t\t\t" + aMember.getName()+"\n"+
								   "ID:\t\t\t\t" + aMember.getID()+"\n"+
								   "Total Hours: \t\t" + aMember.getBalance()+"\n"+
								   "Price Per Hour: $\t" + aMember.getPlan().getPricePerHour() + "/Hr";
												break;
					}
				}
				window3.close();
				window.close();
				memberDetails(inID);
			});	
			
			
			grid3.add(new Label("Plan"),1, 0);
			grid3.add(PlansCB,2,0);
			
			PlansCB.setOnAction((ActionEvent g) ->{
				//if(!(PlansCB.getSelectionModel().getSelectedItem()).isEmpty()){
					
				for(MembershipPlan mp: cafe.getPlanList()){
				
					if(mp.getName().equals(PlansCB.getSelectionModel().getSelectedItem())){
					
					Price_hourtf.setText(Double.toString(mp.getPricePerHour()));
					Total_Pricetf.setText(Double.toString(mp.getTotalPrice()));
					Validation_Daystf.setText(Integer.toString(mp.getValidationDays()));
					}
				}});
			
			grid3.add(new Label("Price/hour"),1, 2);
			grid3.add(Price_hourtf,2,2);
			
			grid3.add(new Label("Total Price"),1, 3);
			grid3.add(Total_Pricetf,2,3);
			
			grid3.add(new Label("Validation"),1, 4);
			grid3.add(Validation_Daystf,2,4);
			
			grid3.add(buy_button,3, 8);
			
			grid3.setStyle("-fx-background-color: skyblue;");
			Scene scene3 = new Scene(grid3, 300, 50); // Width, Height
			
			window3.setScene(scene3);
			
			window3.showAndWait();;
		});

		Button close = new Button("Close");
		close.setMinWidth(50);
		close.setOnAction((ActionEvent e) -> {window.close(); return;});

		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("/res/AM.png"));
		window.setTitle("Member Information");
		window.setMinHeight(350);
		window.setMaxWidth(350);
		window.setResizable(false);

		

			memberData="Name:\t\t\t" + aMember.getName()+"\n"+
					   "ID:\t\t\t\t" + aMember.getID()+"\n"+
					   "Total Hours: \t\t" + aMember.getBalance()+"\n"+
					   "Price Per Hour: $\t" + aMember.getPlan().getPricePerHour() + "/Hr";
					
		memberlabel=new Label(memberData);
		
			
		VBox vb = new VBox(20); 
		vb.setPadding(new Insets(15,15,15,15));

		vb.getChildren().addAll(memberlabel,addHours,changePlan,close);
		vb.setStyle("-fx-background-color: skyblue;");
		vb.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(vb, 300, 50); 
		

		window.setScene(scene);
		window.show();
		
	}	
	
	public void initPlans(){
		
		MembershipPlan mp1= new MembershipPlan();
		MembershipPlan mp2= new MembershipPlan();
		MembershipPlan mp3= new MembershipPlan();
		MembershipPlan mp4= new MembershipPlan();
		
		mp1.setName("Plan1");
		mp1.setID(1);
		mp1.setPricePerHour(0.75);
		mp1.setTotalHours(5);
		mp1.setTotalPrice(3.75);
		mp1.setValidationDays(5);
		
		
		mp2.setName("Plan2");
		mp2.setID(2);
		mp2.setPricePerHour(0.75);
		mp2.setTotalHours(10);
		mp2.setTotalPrice(7.50);
		mp2.setValidationDays(7);
		
		
		mp3.setName("Plan3");
		mp3.setID(3);
		mp3.setPricePerHour(0.75);
		mp3.setTotalHours(15);
		mp3.setTotalPrice(11.25);
		mp3.setValidationDays(10);
		
		mp4.setName("Plan4");
		mp4.setID(4);
		mp4.setPricePerHour(0.75);
		mp4.setTotalHours(20);
		mp4.setTotalPrice(15);
		mp4.setValidationDays(14);
		
		cafe.addPlan(mp1);
		cafe.addPlan(mp2);
		cafe.addPlan(mp3);
		cafe.addPlan(mp4);
		
	}
	
	public void memberBuyTime(){
		
		
		 Stage window=new Stage();
			
			TextField Price_hourtf= new TextField();
			TextField Total_Pricetf= new TextField();
			TextField Validation_Daystf= new TextField();
			
			Price_hourtf.setEditable(false);
			Total_Pricetf.setEditable(false);
			Validation_Daystf.setEditable(false);
			
			
			//Price_hourtf.setMaxSize(100, 50);
			
			Button buy_button= new Button("Buy");
			buy_button.setMinWidth(50);
			buy_button.setMinHeight(50);
			
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
			
		
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle(" Member ADD Plan ");
			window.setMinHeight(300);
			window.setMaxWidth(350);
			window.setResizable(false);
		
					
			
			buy_button.setOnAction((ActionEvent e)->
			{
				window.close();
			});	
			
			
			grid.add(new Label("Plan"),1, 0);
			grid.add(PlansCB,2,0);
			
			grid.add(new Label("Price/hour"),1, 2);
			grid.add(Price_hourtf,2,2);
			
			grid.add(new Label("Total Price"),1, 3);
			grid.add(Total_Pricetf,2,3);
			
			grid.add(new Label("Validation"),1, 4);
			grid.add(Validation_Daystf,2,4);
			
			
			PlansCB.setOnAction((ActionEvent e)->{
				//if(!(PlansCB.getSelectionModel().getSelectedItem()).isEmpty()){
					
				for(MembershipPlan mp: cafe.getPlanList()){
				
					if(mp.getName().equals(PlansCB.getSelectionModel().getSelectedItem())){
					
					Price_hourtf.setText(Double.toString(mp.getPricePerHour()));
					Total_Pricetf.setText(Double.toString(mp.getTotalPrice()));
					Validation_Daystf.setText(Integer.toString(mp.getValidationDays()));
					buyPlan=mp;
				//}
							
			}	}
				});	
			PlansCB.getItems().clear();PlansCB.getItems().addAll("Plan1","Plan2","Plan3","Plan4");
			
			
			Price_hourtf.setEditable(false);
			
			grid.add(buy_button,3, 8);
			
			grid.setStyle("-fx-background-color: skyblue;");
			Scene scene = new Scene(grid, 400, 50); // Width, Height
			
			window.setScene(scene);
			
			window.showAndWait();;
			
			
	}
	
	public void nonMemberBuyTime(){
		
		
		Stage window=new Stage();
		
		TextField Price_hourtf= new TextField(" 1.00 ");
		TextField Total_Pricetf= new TextField("00.00");
		TextField Total_hourstf= new TextField();
		
		Price_hourtf.setEditable(false);
		Total_Pricetf.setEditable(false);
		Total_hourstf.setEditable(true);
		
		
		//Price_hourtf.setMaxSize(100, 50);
		
		Button buy_button= new Button("Buy");
		buy_button.setMinWidth(50);
		//buy_button.setMinHeight(50);
		
		Button totalPrice_button= new Button("Total");
		buy_button.setMinWidth(50);
		//buy_button.setMinHeight(50);
		
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
		
	
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(" Non Member Buy Time ");
		window.setMinHeight(250);
		window.setMaxWidth(350);
		window.setResizable(false);
	
				
		
		grid.add(new Label("Price/hour"),1, 0);
		grid.add(Price_hourtf,2,0);
		
		grid.add(new Label("Total Price"),1, 4);
		grid.add(Total_Pricetf,2,4);
		
		grid.add(new Label("Total Hours"),1, 3);
		grid.add(Total_hourstf,2,3);
		
		totalPrice_button.setOnAction((ActionEvent e)->{ 
			if(!Total_hourstf.getText().isEmpty())
				Total_Pricetf.setText(Total_hourstf.getText()+".00");});	
		
		buy_button.setOnAction((ActionEvent e)->{ 
			
				Total_Pricetf.setText(Total_hourstf.getText());
				NonMember nonMember=	new NonMember();
				
				nonMember.isNonMember=true;
				nonMember.timeBalance= Integer.parseInt(Total_hourstf.getText());
				cafe.nonMemberdaily_List.add(nonMember);
						
				window.close();});	
	

		grid.add(buy_button,3, 6);
		grid.add(totalPrice_button,1, 6);
		
		grid.setStyle("-fx-background-color: skyblue;");
		Scene scene = new Scene(grid, 400, 50); 
		
		window.setScene(scene);
		
		window.showAndWait();;
		
		
	}
	
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	public boolean isSpace(String s){
		boolean isWhitespace = s.matches("^\\s*$");
		return isWhitespace;
	}
	
	public void setClosureTime(){
		

		 Stage window= new Stage();
		 TextField timeCTf=new TextField();
			window.setTitle("Closure Time ");
			window.setMinWidth(350);
			window.getIcons().add(new Image("/res/log.png"));
			window.initModality(Modality.APPLICATION_MODAL);
			
			Button oky_button= new Button("OK");
			oky_button.setMinWidth(50);
			
			
			oky_button.setOnAction(e1->{
				
				String cTime=timeCTf.getText();
				
				if(isNumeric(cTime) && !isSpace(cTime)){
					
					cafe.time= Integer.parseInt(cTime);
					
					Timer closeTimer= new Timer();
					
					closeTimer.schedule(new TimerTask(){
			        	int cntr=0;
			        	public void run() {
			        		 
			        		
			        		cntr++;
			        		cafe.closingTime=cntr/60;
			        		if(cafe.timeEqualsClosingTime()){
			        			
			        		closeTimer.cancel();}
							
			        	} },1000,1000);
					
				}
				else{
					confirmationDialog("Error Setting Closure Time ","The Entered Value is Invalid\n Try Again" );
					setClosureTime();
				}
				window.close();});
			
			
			
			VBox vb= new VBox(20);
			vb.setPadding(new Insets(15, 15, 15, 15));
			vb.getChildren().addAll(new Label("Enter the Closure Time"),timeCTf,oky_button);
			
			vb.setAlignment(Pos.CENTER_LEFT);
			Scene scene = new Scene(vb); 
			
			window.setScene(scene);
			window.showAndWait();
			
		 


		
	}

	public void displayUpdatePC(){
		Stage window = new Stage();
		window.setTitle("Select PC");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/computer.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		if(PCCB.getItems().size() != 0){

		Button ok_button = new Button("OK");
		ok_button.setMinWidth(50);

		ok_button.setOnAction((ActionEvent e)-> {
			updatePC(PCCB.getValue());
			PCCB.getSelectionModel().clearAndSelect(-1);
			window.close();
		 });
		

		VBox vb = new VBox(20); 
		vb.setPadding(new Insets(15,15,15,15));

		vb.getChildren().addAll(new Label("Select PC to view"), PCCB, ok_button);

		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
				
		window.setScene(scene);
		window.showAndWait();
		}
		else{
			confirmationDialog("Error in displaying PCs", "There are no PCs to display");
		}
	}
	
	public void updatePC(String inID){
		int x = Integer.parseInt(inID);
		
		Computer aPC = cafe.updatePC(x);
		
		Stage window = new Stage();
		window.setTitle("PC Details");

		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("/res/computer.png"));
		window.setTitle("PC " + aPC.getID());
		window.setMinHeight(360);
		window.setMaxWidth(350);
		
		TextField IPTF = new TextField(aPC.getIP());
		TextField StationNumTF = new TextField(Integer.toString(aPC.getStationNum()));
		TextField brandTF = new TextField(aPC.getBrand());
		TextField processorModelTF = new TextField(aPC.getProcessorModel());
		TextField capacityTF = new TextField(aPC.getCapacity());
		TextField osTF = new TextField(aPC.getOS());
		
		Button update = new Button("Update Info");
		update.setMinWidth(50);
		
		update.setOnAction((ActionEvent e) -> {
			
			if( !IPTF.getText().isEmpty() &&!isSpace(IPTF.getText()))
				aPC.setIP(IPTF.getText());
			
			if(isNumeric(StationNumTF.getText())&& !StationNumTF.getText().isEmpty() &&!isSpace(StationNumTF.getText()))
				aPC.setStationNum(Integer.parseInt(StationNumTF.getText()));
			
			if( !brandTF.getText().isEmpty() &&!isSpace(brandTF.getText()))
				aPC.setBrand(brandTF.getText());
			
			if( !processorModelTF.getText().isEmpty() &&!isSpace(processorModelTF.getText()))
				aPC.setProcessorModel(processorModelTF.getText());
			
			if( !capacityTF.getText().isEmpty() &&!isSpace(capacityTF.getText()))
				aPC.setCapacity(capacityTF.getText());
			
			if( !osTF.getText().isEmpty() &&!isSpace(osTF.getText()))
				aPC.setOS(osTF.getText());
			
			window.close();
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		grid.add(new Label("IP Address: "), 1, 0);
		grid.add(IPTF,  2,  0);
		
		grid.add(new Label("Station Num: "), 1, 2);
		grid.add(StationNumTF,  2,  2);
		
		grid.add(new Label("Brand: "), 1,  4);
		grid.add(brandTF,  2,  4);
		
		grid.add(new Label("Processor: "), 1,  6);
		grid.add(processorModelTF,  2,  6);
		
		grid.add(new Label("Capacity: "), 1, 8);
		grid.add(capacityTF,  2,  8);
		
		grid.add(new Label(" OS: "), 1,  10);
		grid.add(osTF, 2, 10);
		
		grid.add(update, 1, 12);
		
		grid.setStyle("-fx-background-color: skyblue;");
		Scene s = new Scene(grid, 400, 50); 
		window.setMinHeight(370);
		window.setMaxWidth(350);
		window.setScene(s);
		
		window.showAndWait();
		
		
		
		
	}
		
	public void displayUpdateMember(){
		Stage window = new Stage();
		window.setTitle("Select Member");
		window.setMinWidth(350);
		window.getIcons().add(new Image("/res/AM.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		
		if(MembersCB.getItems().size() != 0){
		Button ok_button = new Button("OK");
		ok_button.setMinWidth(50);

		ok_button.setOnAction((ActionEvent e)-> {
			updateMember(MembersCB.getValue());
			MembersCB.getSelectionModel().clearAndSelect(-1);
			window.close();
			return;
		 });
		

		VBox vb = new VBox(20); 
		vb.setPadding(new Insets(15,15,15,15));

		vb.getChildren().addAll(new Label("Select Member to view"), MembersCB, ok_button);

		vb.setAlignment(Pos.CENTER_LEFT);
		Scene scene = new Scene(vb); 
				
		window.setScene(scene);
		window.showAndWait();
		}
		else{
			confirmationDialog("Error in Displaying Members", "There are no members to display");
		}
	}
	
	public void updateMember(String inID){

		int x = Integer.parseInt(inID);
		Member aMember = cafe.findMember(x);
		Stage window = new Stage();
		window.setTitle("Member Details");
		window.getIcons().add(new Image("/res/AM.png"));


		Button update = new Button("Update Info");
		update.setMinWidth(50);
		
		TextField nameTF = new TextField(aMember.getName());
		TextField addressTF = new TextField(aMember.getAddress());
		TextField emailTF = new TextField(aMember.getEmail());
		TextField birthdayTF = new TextField(aMember.getBirthday());
		TextField phoneNumTF = new TextField(Integer.toString(aMember.getPhoneNumber()));
		
		update.setOnAction((ActionEvent e) -> {
			
			
			if( isNumeric(givenPhoneNum)== true && givenName.isEmpty()==false 
					&& givenAddress.isEmpty()==false && givenEmail.isEmpty()==false
				    && givenBirthD.isEmpty()== false && givenPhoneNum.isEmpty()==false
				    &&!isSpace(givenName)&&!isSpace(givenAddress)&&!isSpace(givenEmail)
				    &&!isSpace(givenBirthD)&&!isSpace(givenPhoneNum))
			
			if(!nameTF.getText().isEmpty()&& !isSpace(nameTF.getText()) )
				aMember.setName(nameTF.getText());
			
			if(!addressTF.getText().isEmpty()&& !isSpace(addressTF.getText()) )
				aMember.setAddress(addressTF.getText());
			
			if(!emailTF.getText().isEmpty()&& !isSpace(emailTF.getText()) )
				aMember.setEmail(emailTF.getText());
			
			if(!birthdayTF.getText().isEmpty()&& !isSpace(birthdayTF.getText()) )
				aMember.setBirthday(birthdayTF.getText());
			
			if( isNumeric(phoneNumTF.getText()) && !phoneNumTF.getText().isEmpty()&& !isSpace(phoneNumTF.getText()) )
				aMember.setPhoneNumber(Integer.parseInt(phoneNumTF.getText()));
			
			window.close();
		});
	
		nameTF.setEditable(true);
		addressTF.setEditable(true);
		emailTF.setEditable(true);
		birthdayTF.setEditable(true);
		phoneNumTF.setEditable(true);
		
		GridPane grid2 = new GridPane();
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		
		grid2.add(new Label("Name: "), 1, 0);
		grid2.add(nameTF,  2,  0);
		
		grid2.add(new Label("Address: "), 1, 2);
		grid2.add(addressTF,  2,  2);
		
		grid2.add(new Label("Email: "), 1,  4);
		grid2.add(emailTF,  2,  4);
		
		grid2.add(new Label("Birthday: "), 1, 6);
		grid2.add(birthdayTF,  2,  6);
		
		grid2.add(new Label("Phone Num: "), 1,  8);
		grid2.add(phoneNumTF, 2,  8);
		
		grid2.add(update,  1,  10);
		
		grid2.setStyle("-fx-background-color: skyblue;");
		Scene s = new Scene(grid2, 400, 50); 
		window.setMinHeight(350);
		window.setMaxWidth(350);
		window.setScene(s);
		
		window.showAndWait();
		
		
		
		
		
	}


}



