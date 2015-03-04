import java.util.ArrayList;

public class PathFinder 
{
	AStarMap map;
	
	public ArrayList<Node> getWayPoints(AStarMap map, int xStart, int yStart, int xGoal, int yGoal)
	{
		this.map = map;
		AStar aStar = new AStar(map);
		aStar.calcShortestPath(xStart, yStart, xGoal, yGoal);
		return aStar.GetShortestPath();
	}
	
	public ArrayList<Node> getAllVisitedNodes(AStarMap map, int xStart, int yStart, int xGoal, int yGoal)
	{
		this.map = map;
		AStar aStar = new AStar(map);
		aStar.calcShortestPath(xStart, yStart, xGoal, yGoal);
		return aStar.GetClosedList();
	}
}
