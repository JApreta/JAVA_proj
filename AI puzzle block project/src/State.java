

import java.util.ArrayList;

public class State {

private Gallon A;
private Gallon B;
protected ArrayList<State> nodes=new ArrayList<>();
protected boolean visited;
protected boolean expanded;
protected int distGoal=0;
protected String appRule="";

  public State(Gallon A, Gallon B){
	  this.A=A;
	  this.B=B;
  }
  
  public State(){
	  this.A= new Gallon();
	  this.B=new Gallon();
  }
  
  public State( State X){
	  this.A= X.getGallonA();
	  this.B=X.getGallonA();
  }
  
  public Gallon getGallonA(){
	  return this.A;
	  
  }
  
  public Gallon getGallonB(){
	  return this.B;
	  
  }
  public void setGallonA(Gallon A){
	  this.A=A;
  }
  public void setGallonB(Gallon B){
	  this.B=B;
  }
  

}
