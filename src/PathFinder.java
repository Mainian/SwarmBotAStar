import java.util.ArrayList;

public class PathFinder 
{
	AStarMap map;
	
	public ArrayList<Node> getWayPoints(AStarMap map)
	{
		this.map = map;
		
		AStar aStar = new AStar(map);
		int xStart = map.GetStartNode().GetX();
		int yStart = map.GetStartNode().GetY();
		int xGoal = map.GetGoalNode().GetX();
		int yGoal = map.GetGoalNode().GetY();
		
		ArrayList<Node> shortestPath = aStar.calcShortestPath(xStart, yStart, xGoal, yGoal);
		ArrayList<Node> wayPoints = calculateWayPoints(shortestPath);
		
		return wayPoints;
	}
	
	private ArrayList<Node> calculateWayPoints(ArrayList<Node> shortestPath)
	{
		ArrayList<Node> waypoints = new ArrayList<Node>();
		shortestPath.add(0, map.GetStartNode());
		shortestPath.add(map.GetGoalNode());
		
		Node node1 = shortestPath.get(0);
		int n1 = 0;
		Node node2 = shortestPath.get(1);
		int n2 = 1;
		
		while(!node2.equalis(shortestPath.get(shortestPath.size()-1)))
		{
			if(lineClear(node1, node2))
			{
				n2++;
				node2 = shortestPath.get(n2);
			}
			else
			{
				n1 = n2-1;
				node1 = shortestPath.get(n1);
				waypoints.add(node1);
				n2++;
				node2 = shortestPath.get(n2);
			}
		}
		waypoints.add(node2);
		
		return waypoints;
	}
	
	private boolean lineClear(Node node1, Node node2)
	{
		ArrayList<Node> nodesOnLine = new ArrayList<Node>();
		for(Node n : nodesOnLine)
			if(map.GetNode(n.GetX(), n.GetY()).GetIsObstacle())
				return false;
		return true;
	}
}
