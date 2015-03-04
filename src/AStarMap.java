import java.util.ArrayList;
import java.util.Comparator;

public class AStarMap implements Comparator<Node>
{
	private final double MIN_COST = 1.0;
	private final double ALPHA = 0.5;
	
	private int width,height,startX=0,startY=0,goalX=0,goalY=0;
	private ArrayList<ArrayList<Node>> map;
	
	public AStarMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		createMap();
	}
	
	private void createMap()
	{
		Node node;
		map = new ArrayList<ArrayList<Node>>();
		for(int x : new Range(width))
		{
			map.add(new ArrayList<Node>());
			for(int y: new Range(height))
			{
				node = new Node(x,y);
				map.get(x).add(node);
			}
		}
	}
	
	public Node GetNode(int x, int y) 
	{ 
		if(x>=0 && x<width && y>=0 && y<height)
			return map.get(x).get(y); 
		else 
			return null;
	}
	
	public int GetWidth() { return width; }
	public int GetHeight() { return height; }
	public Node GetStartNode() { return map.get(startX).get(startY); }
	public Node GetGoalNode() { return map.get(goalX).get(goalY); }
	
	public void SetObstacle(int x, int y, boolean isObstacle) 
	{ 
		if(x>=0 && x<width && y>=0 && y<height)
			map.get(x).get(y).SetIsObstacle(isObstacle); 
	}
	
	public void SetNodeGPSLocation(int x, int y, float latitude, float longitude, float altitude)
	{
		if(x>=0 && x<width && y>=0 && y<height){
			map.get(x).get(y).SetAltitude(altitude);
			map.get(x).get(y).SetLatitude(latitude);
			map.get(x).get(y).SetLongitude(longitude);
		}
	}
	
	public void SetStartLocation(int x, int y)
	{
		if(x>=0 && x<width && y>=0 && y<height){
			map.get(startX).get(startY).SetIsStart(false);
			map.get(x).get(y).SetIsStart(true);
			startX=x;
			startY=y;
		}
	}
	
	public void SetGoalLocation(int x, int y)
	{
		if(x>=0 && x<width && y>=0 && y<height){
			map.get(goalX).get(goalY).SetIsGoal(false);
			map.get(x).get(y).SetIsGoal(true);
			goalX=x;
			goalY=y;
		}
	}
	
	public double GetDistanceBetweenNodes(Node node1, Node node2)
	{
		return Math.sqrt(Math.pow(node2.GetX()- node1.GetX(),2)
				+ Math.pow(node2.GetY()-node1.GetY(),2));
	}
	
	public double CalculateH(Node node)
	{
		if(node.GetH() > 0)
			return node.GetH();
		else
		{
			double h = 0;
			h = 5.0*(double)(MIN_COST + Math.abs((double)node.GetX()-(double)GetGoalNode().GetX())+
					Math.abs((double)node.GetY() - (double)GetGoalNode().GetY()));
			node.SetH(h);
			return h;
		}
	}
	
	public double CalculateG(Node node)
	{
		if(node.GetG() > 0)
			return node.GetG();
		else
		{
			double g = 0;
			if(node.GetParent() != null)
			g = 1.0 + ALPHA + (CalculateG(node.GetParent()) - 1.0);
			node.SetG(g);
			return g;
		}
	}
	
	public double CalculateF(Node node)
	{
		return CalculateG(node) + CalculateH(node);
	}
	
    public ArrayList<Node> GetNeighborList(Node node) 
    {
        ArrayList<Node> neighborList = new ArrayList<Node>();
        
        int yVal = node.GetY();
        int xVal = node.GetX();
        
        for(int x = xVal-1; x <= xVal+1; x++)
        	for(int y = yVal-1; y <= yVal+1; y++)
        		if(x>=0 && y>=0 && x<width && y<height)
        			if(!node.equals(GetNode(x,y)))
        				neighborList.add(GetNode(x,y));
        
        return neighborList;
    }
    
    public ArrayList<Node> GetNeighborListNoDiagonals(Node node)
    {
        ArrayList<Node> neighborList = new ArrayList<Node>();
        
        int yVal = node.GetY();
        int xVal = node.GetX();
        
        for(int x = xVal-1; x <= xVal+1; x++)
        	for(int y = yVal-1; y <= yVal+1; y++)
        		if(x>=0 && y>=0 && x<width && y<height)
        			if(xVal==x ^ yVal==y)
        				neighborList.add(GetNode(x,y));
        
        return neighborList;
    }
	
	public void Reset()
	{
		startX = 0;
		startY = 0;
		goalX = 0;
		goalY = 0;
		createMap();
	}

	@Override
	public int compare(Node node0, Node node1) {
		double node0TotalDistanceFromGoal = CalculateF(node0);
		double node1TotalDistanceFromGoal = CalculateF(node1);
		
		//node0 > node1 return positive
		if(node0TotalDistanceFromGoal > node1TotalDistanceFromGoal)
			return 1;
		//node0 < node1 return negative	
		else if (node0TotalDistanceFromGoal < node1TotalDistanceFromGoal) 
			return -1;
		//node0 == node1 return 0
		else
			return 0;

	}
}
