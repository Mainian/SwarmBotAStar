public class Node{
	private double g,h,f, latitude, longitude;
	private int x,y;
	private Node parent;
	private boolean isObstacle, isStart, isGoal, isPath, visited;
	
	public double GetG(){return g;}
	public double GetH(){return h;}
	public double GetF(){return f;}
	public double GetLatitude(){return latitude;}
	public double GetLongitude(){return longitude;}
	public void SetG(double g){this.g=g;}
	public void SetF(double f){this.f=f;}
	public void SetH(double h){this.h=h;}
	public void SetLatitude(double latitude){this.latitude = latitude;}
	public void SetLongitude(double longitude){this.longitude = longitude;}
	public int GetX(){return x;}
	public int GetY(){return y;}
	public void SetX(int x){this.x=x;}
	public void SetY(int y){this.y=y;}
	public Node GetParent(){return parent;}
	public void SetParent(Node parent){this.parent=parent;}
	public boolean GetIsObstacle(){return isObstacle;}
	public boolean GetIsStart(){return isStart;}
	public boolean GetIsGoal(){return isGoal;}
	public boolean GetIsPath(){return isPath;}
	public boolean GetVisit(){return visited;}
	public void SetIsObstacle(boolean isObstacle){this.isObstacle=isObstacle;}
	public void SetIsStart(boolean isStart){this.isStart=isStart;}
	public void SetIsGoal(boolean isGoal){this.isGoal=isGoal;}
	public void SetIsPath(boolean isPath){this.isPath=isPath;}
	public void SetVisited(boolean visited){this.visited=visited;}
	
	public Node(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.visited=false;
		this.isObstacle=false;
		this.isStart=false;
		this.isGoal=false;
	}
	
	public boolean equalis(Node node)
	{
		return (node.x==x) && (node.y==y);
	}
}
