import java.util.ArrayList;
import java.util.Collections;

public class AStar 
{
	private AStarMap map;
	private ArrayList<Node> closedList;
	private ArrayList<Node> openList;
	
	public AStar(AStarMap map)
	{
		this.map = map;
		closedList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
	}
	
	public ArrayList<Node> calcShortestPath(int startX, int startY, int goalX, int goalY)
	{
		map.setStartLocation(startX, startY);
		map.setGoalLocation(goalX, goalY);
		
		if(map.GetGoalNode().GetIsObstacle())
		{
			return null;
		}
		
		map.GetStartNode();
		closedList.clear();
		openList.clear();
		AddNodeToOpenList(map.GetStartNode());
		
		//while we haven't reached the goal yet
		while(openList.size() != 0)
		{
			Node current = openList.get(0);
			
			// check to see if we are at our goal
			if(current.equalis(map.GetGoalNode()))
				return rebuildPath(current);
			
			openList.remove(current);
			closedList.add(current);
			
			for(Node neighbor : map.GetNeighborList(current))
			{
				if(closedList.contains(neighbor))
					continue;
				
				if(!neighbor.GetIsObstacle())
				{
					// calculate how long the path is if we choose this neighbor as the next step in the path
					double neighborDistanceFromStart = map.GetDistanceBetweenNodes(map.GetStartNode(), current)
						+ map.GetDistanceBetweenNodes(current, neighbor);
					
					if(!openList.contains(neighbor))
					{
						openList.add(neighbor);
						neighbor.SetParent(current);
					}
					else if(neighborDistanceFromStart < map.GetDistanceBetweenNodes(current, map.GetStartNode()))
						neighbor.SetParent(current);
				}
			}
		}
		return null;
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
		return path;
	}
}
