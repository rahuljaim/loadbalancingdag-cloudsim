package cloudsim.network;


public class TopologicalNode {
	
	
	private int nodeID=0;
	
	
	private String nodeName = null;
	

	private int worldX = 0;
	private int worldY = 0;
	
	
	public TopologicalNode(int nodeID){
		//lets initialize all private class attributes
		this.nodeID = nodeID;
		this.nodeName = new Integer(nodeID).toString();
	}

	public TopologicalNode(int nodeID, int x, int y){
		//lets initialize all private class attributes
		this.nodeID = nodeID;
		this.nodeName = new Integer(nodeID).toString();
		this.worldX = x;
		this.worldY = y;
	}

		public TopologicalNode(int nodeID, String nodeName, int x, int y){
		
		this.nodeID = nodeID;
		this.nodeName = nodeName;
		this.worldX = x;
		this.worldY = y;
	}

           public int getNodeID(){
		return nodeID;
	}
	
	
	public String getNodeLabel(){
		return this.nodeName;
	}
	
	
	public int getCoordinateX(){
		return worldX;
	}
	

	public int getCoordinateY(){
		return worldY;
	}

}
