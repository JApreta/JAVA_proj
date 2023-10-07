

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Hw01_judithantonio {

	
	public static void main(String[] args) throws IOException ,FileNotFoundException{
		
		 State initialState=new State();
		 State finalState=new State();
		 int Acapacity=0, Bcapacity=0;
		String str= new String();
		
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		
		File myFile=new File(args[0]);
		@SuppressWarnings("resource")
		Scanner inpt= new Scanner(myFile);
		int j=0;
		
		while(inpt.hasNext()){
			
			
			j++;
			str=inpt.nextLine();
			int value = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			switch(j){
			case 1:
				Acapacity=value;
				initialState.getGallonA().setCapacity(value);
				finalState.getGallonA().setCapacity(value);
				break;
			case 2:
				initialState.getGallonB().setCapacity(value);
				finalState.getGallonB().setCapacity(value);
				Bcapacity=value;
				break;
			case 3:
				
				
				String num= str.substring(str.indexOf(":")+2);
				
				initialState.getGallonA().setContent(Integer.parseInt(num.substring(0,1)));
				initialState.getGallonB().setContent(Integer.parseInt(num.substring(2)));
				
				break;
				
			case 4:
				String num2= str.substring(str.indexOf(":")+2);
				if(num2.charAt(0)=='-'){
					finalState.getGallonA().setContent(Integer.parseInt(num2.substring(0,2)));
					finalState.getGallonB().setContent(Integer.parseInt(num2.substring(3)));
				}
				else{
				finalState.getGallonA().setContent(Integer.parseInt(num2.substring(0,1)));
				finalState.getGallonB().setContent(Integer.parseInt(num2.substring(2)));
				}
				break;	
			default:
				break;
			}
				
			
		}
		initialState.appRule=("Starting out with  "+initialState.getGallonA().getCapacity()+"-gallon jug and a  "
		+initialState.getGallonB().getCapacity()+"-gallon jug\t\t\t-- state:("+initialState.getGallonA().getContent()
		+","+initialState.getGallonA().getContent()+")");
		
		System.out.println(">Strategy A:");
		writer.println(">Strategy A:");
		StrategyA tdv=new StrategyA(initialState,finalState,Acapacity,Bcapacity);
		tdv.printTree(writer);
		writer.println("");
		writer.println(">Strategy B:");
		System.out.println("\n>Strategy B:");
		StrategyB td=new StrategyB(initialState,finalState,Acapacity,Bcapacity);
		td.printTree(writer);
		
		writer.close();
		

	}

}
