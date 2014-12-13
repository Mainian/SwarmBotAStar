import java.util.ArrayList;

public class Path 
{
	private ArrayList<Node> waypoints = new ArrayList<Node>();
	public int GetLength() { return waypoints.size(); }
	public Node GetWayPointNode(int index){return waypoints.get(index);}	
	public void AppendWayPoint(Node node){ waypoints.add(node);}
	public void PrependWayPoint(Node node) { waypoints.add(0, node); }
	
	public boolean contains(int x, int y)
	{
		for(Node node : waypoints)
			if(node.GetX()==x && node.GetY()==y)
				return true;
		return false;
	}
}
