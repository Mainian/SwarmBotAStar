import java.util.ArrayList;
import java.util.Comparator;

public class AStarMap implements Comparator<Node>
{
	
	private final double MIN_COST = 1.0;
	private final double ALPHA = 0.5;
	
	private int width,height,startX=0,startY=0,goalX=0,goalY=0;
	private ArrayList<ArrayList<Node>> map;
	private int[][] obstacleMap = {{0}};
	
	public AStarMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		createMap();
	}
	
	void createMap()
	{
		Node node;
		map = new ArrayList<ArrayList<Node>>();
		for(int x : new Range(width))
		{
			map.add(new ArrayList<Node>());
			for(int y: new Range(height))
			{
				node = new Node(x,y);
					if(obstacleMap[y][x] == 1)
						node.SetIsObstacle(true);
					map.get(x).add(node);
			}
		}
	}
	
	public Node GetNode(int x, int y) 
	{ 
		return map.get(x).get(y); 
	}
	public int GetWidth() { return width; }
	public int GetHeight() { return height; }
	public Node GetStartNode() { return map.get(startX).get(startY); }
	public Node GetGoalNode() { return map.get(goalX).get(goalY); }
	
	public void setObstacle(int x, int y, boolean isObstacle) 
	{ 
		map.get(x).get(y).SetIsObstacle(isObstacle); 
	}
	
	public void setStartLocation(int x, int y)
	{
		map.get(startX).get(startY).SetIsStart(false);
		map.get(x).get(y).SetIsStart(true);
		startX=x;
		startY=y;
	}
	
	public void setGoalLocation(int x, int y)
	{
		map.get(goalX).get(goalY).SetIsGoal(false);
		map.get(x).get(y).SetIsGoal(true);
		goalX=x;
		goalY=y;
	}
	
	public double GetDistanceBetweenNodes(Node node1, Node node2)
	{
		return Math.sqrt(Math.pow(node2.GetX()- node1.GetX(),2)
				+ Math.pow(node2.GetY()-node1.GetY(),2));
	}
	
	public double CalculateH(Node node)
	{
		double h = 0;
		h = (double)(MIN_COST + Math.abs((double)node.GetX()-(double)GetGoalNode().GetX())+
				Math.abs((double)node.GetY() - (double)GetGoalNode().GetY()));
		
		return h;
	}
	
	public double CalculateG(Node node)
	{
		double g = 0;
		g = 1.0 + ALPHA + (node.GetG() - 1.0);
		
		return g;
	}
	
    public ArrayList<Node> GetNeighborList(Node node) 
    {
        ArrayList<Node> neighborList = new ArrayList<Node>();
        
        int yVal = node.GetY();
        int xVal = node.GetX();
        
        for(int x = xVal-1; x <= xVal+1; x++)
        	for(int y = yVal-1; y <= yVal+1; y++)
        		if(x>=0 && y>=0 && x<width && y<height)
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
		double node0TotalDistanceFromGoal = CalculateG(node0)+CalculateH(node0)+
				+GetDistanceBetweenNodes(node0,GetStartNode());
		
		double node1TotalDistanceFromGoal = CalculateG(node1)+CalculateH(node1)+
				+GetDistanceBetweenNodes(node1,GetStartNode());
		
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
