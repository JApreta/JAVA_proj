

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;



public class StrategyA {
	private State intiSt;
	private State fnlSt;
	private ArrayList<State> visitedNodes=new ArrayList<>();
	private Stack<State> expandNodes=new Stack<>();
	Gallon A;
	Gallon B;
	
	public StrategyA(State intiSt,State fnlSt, int Acap, int Bcap){
		A=new Gallon(Acap);
		B=new Gallon(Bcap);
		A.setContent(intiSt.getGallonA().getContent());
		B.setContent(intiSt.getGallonB().getContent());
		this.intiSt=intiSt;
		this.fnlSt=fnlSt;
	}
	
	public void pourWatertoFillGallon( Gallon x, Gallon y){
		while(y.getContent()<y.getCapacity()){
			
			y.setContent(1+y.getContent());
			x.setContent(x.getContent()-1);
		}
				
	}
	
	public void pourAllWatertoGallon( Gallon x, Gallon y){
		while(x.getContent()>0){
			
			y.setContent(1+y.getContent());
			x.setContent(x.getContent()-1);
		}
				
	}
	
	
	public void fillGallon(Gallon x){
		x.setContent(x.getCapacity());
	}
	
	public int CalcDistGoal(State X){
		 int dist=0;
				if(fnlSt.getGallonB().getContent()!=-1 &&fnlSt.getGallonA().getContent()!=-1) {
					dist=(Math.abs(X.getGallonA().getContent()-fnlSt.getGallonA().getContent())+
				Math.abs(X.getGallonB().getContent()-fnlSt.getGallonB().getContent()));
					
				}
				else if(fnlSt.getGallonB().getContent()==-1)
					dist=Math.abs(X.getGallonA().getContent()-fnlSt.getGallonA().getContent());
				
				else if(fnlSt.getGallonA().getContent()==-1)
					dist=Math.abs(X.getGallonB().getContent()-fnlSt.getGallonB().getContent());
				return dist;
	}
	
	public boolean chckVisited(State current){
		for(int i=0; i<visitedNodes.size();i++){
			if(visitedNodes.get(i).getGallonA().getContent()== current.getGallonA().getContent()&&
					visitedNodes.get(i).getGallonB().getContent()== current.getGallonB().getContent())
				
				return true;
		}
		return false;
	}
	
	public boolean compareInitState(State currentSt){
		if(intiSt.getGallonA().getContent()== currentSt.getGallonA().getContent()&&
				intiSt.getGallonB().getContent()== currentSt.getGallonB().getContent() )
		return false;
		else
			return true;
		
	}
	
	public void checkAndAdd(State newNode, State current){
		
		if(chckVisited(newNode)==false){
			
			visitedNodes.add(newNode);
			expandNodes.push(newNode);
				
			}	
	}
	
	public void rules(State current, int rule){
		
		
		switch(rule){
		case 1:
		//rule 1
			//rule 1
			if(current.getGallonA().getContent()<current.getGallonA().getCapacity()){//if A is not full
				Gallon agallon=new Gallon(A.getCapacity(),A.getCapacity());//creates atemp full gallon
				State newNode=new State(agallon,current.getGallonB());//creates a node for expantion with the info (Afull,Bsame)
				newNode.appRule=("Fill the  "+newNode.getGallonA().getCapacity()+"-gallon jug\t\t\t\t\t\t\t-- state:("+newNode.getGallonA().getContent()
								+","+newNode.getGallonB().getContent()+")");
				
				checkAndAdd(newNode, current);
				current.nodes.add(newNode);//expand current node
			}
		break;
		//rule 2
		case 2:
			if(current.getGallonB().getContent()<current.getGallonB().getCapacity()){//if B is not full
				Gallon agallon=new Gallon(B.getCapacity(),B.getCapacity());//creates atemp full gallon
				State newNode=new State(current.getGallonA(),agallon);//creates a node for expantion with the info (Asame,Bfull)
				checkAndAdd(newNode, current);
				newNode.appRule=("Fill the  "+newNode.getGallonB().getCapacity()
						+"-gallon jug\t\t\t\t\t\t\t-- state:("
						+newNode.getGallonA().getContent()
						+","+newNode.getGallonB().getContent()+")");
				current.nodes.add(newNode);//expand current node
			}
		break;
		case 3:
		//rule 5
			if(current.getGallonA().getContent()>0){//if A has some water
				Gallon agallon=new Gallon(A.getCapacity());//creates atemp empty gallon
				State newNode=new State(agallon,current.getGallonB());//creates a node for expantion with the info (Aempty,Bsame)
				newNode.appRule=("Empty the  "+newNode.getGallonA().getCapacity()+"-gallon jug\t\t\t\t\t\t-- state:("+newNode.getGallonA().getContent()
						+","+newNode.getGallonB().getContent()+")");
				checkAndAdd(newNode, current);

			}
		break;
		case 4:
		//rule 6
			if(current.getGallonB().getContent()>0){//if B has some water
				Gallon bgallon=new Gallon(B.getCapacity());//creates a temporary empty gallon
				State newNode=new State(current.getGallonA(),bgallon);//creates a node for expantion with the info (ASame,Bempty)
				newNode.appRule=("Empty the  "+newNode.getGallonB().getCapacity()
						+"-gallon jug\t\t\t\t\t\t-- state:("+newNode.getGallonA().getContent()
						+","+newNode.getGallonB().getContent()+")");
				checkAndAdd(newNode, current);
				
				current.nodes.add(newNode);//expand current node
			}
			break;
		case 5:
		//rule 7
			if(current.getGallonA().getContent()+current.getGallonB().getContent()>=A.getCapacity()
			&& current.getGallonB().getContent()>0	){//if A.cont+ B.cont>= A.capacity and B is not empty
		Gallon a=new Gallon(A.getCapacity(),current.getGallonA().getContent());//creates a temporary gallon with same content as A
		Gallon b=new Gallon(B.getCapacity(),current.getGallonB().getContent());//creates a temporary gallon with same content as B
		pourWatertoFillGallon( b, a);//pour b into a
		State newNode=new State(a,b);//creates a node for expantion with the info (a,b)
		newNode.appRule=("Pour water from the  "+newNode.getGallonB().getCapacity()
				+"-gallon jug into the "+newNode.getGallonA().getCapacity() 
				+"-gallon jug\t\t-- state:("+newNode.getGallonA().getContent()
				+","+newNode.getGallonB().getContent()+")");
		checkAndAdd(newNode, current);
		
	}
			break;
			
		
		case 6:
			//rule 8
			if(current.getGallonA().getContent()+current.getGallonB().getContent()>=B.getCapacity()
					&& current.getGallonA().getContent()>0	){//if A.cont+ B.cont>= A.capacity and A is not empty
				Gallon a=new Gallon(A.getCapacity(),current.getGallonA().getContent());//creates a temporary gallon with same content as A
				Gallon b=new Gallon(B.getCapacity(),current.getGallonB().getContent());//creates a temporary gallon with same content as B
				pourWatertoFillGallon( a, b);//pour a into b
				State newNode=new State(a,b);//creates a node for expantion with the info (a,b)
				newNode.appRule=("Pour water from the  "+newNode.getGallonA().getCapacity()
						+"-gallon jug into the "+newNode.getGallonB().getCapacity() 
						+"-gallon jug\t\t-- state:("+newNode.getGallonA().getContent()
						+","+newNode.getGallonB().getContent()+")");
				if(newNode.equals(current)== false)
				checkAndAdd(newNode, current);			
			}
			
			
			
			break;
		case 7:
		
			//rule 9
			if(current.getGallonA().getContent()+current.getGallonB().getContent()<=A.getCapacity()
					&& current.getGallonB().getContent()>0	){//if A.cont+ B.cont>= A.capacity and B is not empty
				Gallon a=new Gallon(A.getCapacity(),current.getGallonA().getContent());//creates a temporary gallon with same content as A
				Gallon b=new Gallon(B.getCapacity(),current.getGallonB().getContent());//creates a temporary gallon with same content as B
				pourAllWatertoGallon( b, a);//pour b into a
				State newNode=new State(a,b);//creates a node for expantion with the info (a,b)
				newNode.appRule=("Pour water from the  "+newNode.getGallonB().getCapacity()
						+"-gallon jug into the "+newNode.getGallonA().getCapacity() 
						+"-gallon jug\t\t-- state:("+newNode.getGallonA().getContent()+","+newNode.getGallonB().getContent()+")");
				
				checkAndAdd(newNode, current);
				
			}
			
			break;
		case 8:
			//rule 10
			if(current.getGallonA().getContent()+current.getGallonB().getContent()<=B.getCapacity()
					&& current.getGallonA().getContent()>0	){//if A.cont+ B.cont>= A.capacity and A is not empty
				Gallon a=new Gallon(A.getCapacity(),current.getGallonA().getContent());//creates a temporary gallon with same content as A
				Gallon b=new Gallon(B.getCapacity(),current.getGallonB().getContent());//creates a temporary gallon with same content as B
				pourAllWatertoGallon( a, b);//pour a into b
				State newNode=new State(a,b);//creates a node for expansion with the info (a,b)
				newNode.appRule=("Pour water from the  "+newNode.getGallonA().getCapacity()
						+"-gallon jug into the "+newNode.getGallonB().getCapacity() 
						+"-gallon jug\t\t-- state:("+newNode.getGallonA().getContent()
						+","+newNode.getGallonB().getContent()+")");
				
				checkAndAdd(newNode, current);
				
				
				current.nodes.add(newNode);//expand current node
			}
			break;
			default:
				break;
		}
			
	}
	
	public boolean checkGoalState(State currState){
		if(currState.getGallonA().getContent()== fnlSt.getGallonA().getContent()
				&& this.fnlSt.getGallonB().getContent()==-1)
			return true;
		
		else if(currState.getGallonB().getContent()== fnlSt.getGallonB().getContent()
				&& this.fnlSt.getGallonA().getContent()==-1)
			return true;
		
		else if(currState.getGallonB().getContent()== fnlSt.getGallonB().getContent()
				&&currState.getGallonA().getContent()== this.fnlSt.getGallonA().getContent())
			return true;
		
		else return false;
		
		
	}
	
	public void printTree(PrintWriter writer){
		
		Random rand= new Random();
		
		int rule=0;
		
		if(visitedNodes.isEmpty() && expandNodes.isEmpty()){
			
		
			if(!checkGoalState(intiSt)){//check if final state== initial/ if not
			
				this.visitedNodes.add(intiSt);//add it to the stack of visited nodes
				expandNodes.push(intiSt);
			
				int i=0;
			
				while(!checkGoalState(expandNodes.peek()) && i<250 ){
					rule=rand.nextInt(8)+1;
					rules(expandNodes.peek(),rule);		
					i++;
				}
			}	
			for(int i=0; i<visitedNodes.size();i++){
				writer.println(">"+visitedNodes.get(i).appRule);
				System.out.println(">"+visitedNodes.get(i).appRule);}

							
		}
	}
	
}
