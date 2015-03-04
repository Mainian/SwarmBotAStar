import java.util.ArrayList;
import java.util.Collections;

public class AStar 
{
	private AStarMap map;
	private ArrayList<Node> closedList;
	private ArrayList<Node> shortestPath;
	private ArrayList<Node> openList;
	
	public ArrayList<Node> GetClosedList(){return closedList;}
	public ArrayList<Node> GetOpenList(){return openList;}
	public ArrayList<Node> GetShortestPath(){return shortestPath;}
	
	public AStar(AStarMap map)
	{
		this.map = map;
		closedList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
		shortestPath = new ArrayList<Node>();
	}
	
	public boolean calcShortestPath(int startX, int startY, int goalX, int goalY)
	{
		map.SetStartLocation(startX, startY);
		map.SetGoalLocation(goalX, goalY);
		
		if(map.GetGoalNode().IsObstacle())
		{
			return false;
		}
		
		map.GetStartNode();
		closedList.clear();
		openList.clear();
		shortestPath.clear();
		AddNodeToOpenList(map.GetStartNode());
		
		//while we haven't reached the goal yet
		while(openList.size() != 0)
		{
			Node current = openList.get(0);
			openList.remove(current);
			closedList.add(current);
			
			// check to see if we are at our goal
			if(current.equals(map.GetGoalNode()))
			{
				shortestPath = rebuildPath(current);
				return true;
			}
			
			double currG = map.CalculateG(current);
			for(Node neighbor : map.GetNeighborList(current))
			{	
				if(closedList.contains(neighbor))
					continue;
				
				//PERFORM HOOK HERE TO DETECT OBSTACLE
				
				if(!neighbor.IsObstacle())
				{

					// calculate how long the path is if we choose this neighbor as the next step in the path
					double neighborDistanceFromStart = currG + map.GetDistanceBetweenNodes(current, neighbor);
					//double neighborDistanceFromStart = map.GetDistanceBetweenNodes(map.GetStartNode(), current)+ map.GetDistanceBetweenNodes(current, neighbor);
					
					if(!openList.contains(neighbor))
					{
						neighbor.SetParent(current);
						AddNodeToOpenList(neighbor);
					}
					else if(neighborDistanceFromStart < currG)
						neighbor.SetParent(current);
				}
			}
		}
		return false;
	}
	
	private void AddNodeToOpenList(Node node)
	{
		openList.add(node);
		Collections.sort(openList, map);
	}
	
	private ArrayList<Node> rebuildPath(Node node)
	{
		ArrayList<Node> path = new ArrayList<Node>();
		while(!(node.GetParent() == null))
		{
			path.add(0,node);
			node = node.GetParent();
		}
		path.add(0,node);
		return path;
	}
}
