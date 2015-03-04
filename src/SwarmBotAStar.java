import java.util.ArrayList;

public class SwarmBotAStar {

	public static AStarMap map;
	public static int width = 10;
	public static int height = 10;
	
	public static void main(String[] args) 
	{
		PathFinder findMeAPath = new PathFinder();
		ArrayList<Node> path = new ArrayList<Node>();
		
		System.out.println("Square Obstacle:");
		CreateMapForTesting();
		CreateSquareObstacle();
		PrintMap();
		path = findMeAPath.getWayPoints(map,9,0,0,9);
		//for(Node n : path)
		//	System.out.println(n.GetX() + " " + n.GetY());
		PrintPath(path);
		CreateMapForTesting();
		CreateSquareObstacle();
		path = findMeAPath.getAllVisitedNodes(map,9,0,0,9);
		PrintPath(path);
		
		
		System.out.println("Line Obstacle:");
		CreateMapForTesting();
		CreateLineObstacle();
		PrintMap();
		findMeAPath = new PathFinder();
		path = findMeAPath.getWayPoints(map,0,0,0,5);
		PrintPath(path);
		
		CreateMapForTesting();
		CreateLineObstacle();
		findMeAPath = new PathFinder();
		path = findMeAPath.getAllVisitedNodes(map, 0, 0, 0, 5);
		PrintPath(path);
	}
	
	public static void CreateMapFromGPSLocation()
	{

	}
	
	private static void CreateMapForTesting()
	{
		map = new AStarMap(width, height);
	}
	
	public static void CreateSquareObstacle()
	{		
		//Line on row 4
		map.SetObstacle(3, 2, true);
		map.SetObstacle(3, 3, true);
		map.SetObstacle(3, 4, true);
		map.SetObstacle(3, 5, true);
		
		//Line on column 2
		//map.SetObstacle(4, 2, true);
		//map.SetObstacle(5, 2, true);
		
		//Line on column 5
		map.SetObstacle(4, 5, true);
		map.SetObstacle(5, 5, true);
		
		//Line on row 6
		//map.SetObstacle(6, 2, true);
		//map.SetObstacle(6, 3, true);
		//map.SetObstacle(6, 4, true);
		//map.SetObstacle(6, 5, true);
	}
	
	public static void CreateLineObstacle()
	{
		for(int i : new Range(9))
			map.SetObstacle(i, 4, true);
	}
	
	public static void PrintMap()
	{
		System.out.println();
		for(int x : new Range(map.GetWidth())) {
			System.out.println();
			for(int y : new Range(map.GetHeight()))
				if(map.GetNode(x, y).IsObstacle())
					System.out.print("X");
				else
					System.out.print("O");
		}
		System.out.println();	
	}
	
	public static void PrintPath(ArrayList<Node> path)
	{
		System.out.println();
		for(int x : new Range(map.GetWidth())) {
			System.out.println();
			for(int y : new Range(map.GetHeight()))
				if(path.contains(map.GetNode(x, y)))
					System.out.print("*");
				else if(map.GetNode(x, y).IsObstacle())
					System.out.print("X");
				else
					System.out.print("O");
		}
		System.out.println();	
	}
}
