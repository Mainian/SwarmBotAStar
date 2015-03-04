public class Node{
	private double g=0,h=0,f=0;
	private float latitude, longitude, altitude;
	private int x,y;
	private Node parent;
	private boolean isObstacle, isStart, isGoal, isPath, visited;
	
	public double GetG(){return g;}
	public double GetH(){return h;}
	public double GetF(){return f;}
	public float GetLatitude(){return latitude;}
	public float GetLongitude(){return longitude;}
	public float GetAltitude(){return altitude;}
	public void SetG(double g){this.g=g;}
	public void SetF(double f){this.f=f;}
	public void SetH(double h){this.h=h;}
	public void SetLatitude(float latitude){this.latitude = latitude;}
	public void SetLongitude(float longitude){this.longitude = longitude;}
	public void SetAltitude(float altitude){this.altitude = altitude;}
	public int GetX(){return x;}
	public int GetY(){return y;}
	public void SetX(int x){this.x=x;}
	public void SetY(int y){this.y=y;}
	public Node GetParent(){return parent;}
	public void SetParent(Node parent){this.parent=parent;}
	public boolean IsObstacle(){return isObstacle;}
	public boolean IsStart(){return isStart;}
	public boolean IsGoal(){return isGoal;}
	public boolean IsPath(){return isPath;}
	public boolean Visited(){return visited;}
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
	
	public boolean equals(Node node)
	{
		return (node.x==x) && (node.y==y);
	}
}
