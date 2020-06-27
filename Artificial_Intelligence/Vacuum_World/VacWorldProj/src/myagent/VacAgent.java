package myagent;

import java.util.ArrayList;
import java.util.List;
import vacworld.GoForward;
import vacworld.ShutOff;
import vacworld.SuckDirt;
import vacworld.TurnLeft;
import vacworld.TurnRight;
import vacworld.VacPercept;
import agent.Action;
import agent.Agent;
import agent.Percept;


enum Direction {LEFT, RIGHT}

// Indicates where is the agent seeing for (in AgentMap)
enum Orientation {
    NORTH, EAST, SOUTH, WEST;

    /*Returns the ne orientation according the rotation*/
    public Orientation rotateLeft() {
	switch (this) {
            case NORTH:
		return WEST;
            case EAST:
		return NORTH;
            case SOUTH:
		return EAST;
            case WEST:
		return SOUTH;
	}
	throw new IllegalStateException();
    }
	
    public Orientation rotateRight() {
    	switch (this) {
            case NORTH:
                return EAST;
            case EAST:
		return SOUTH;
            case SOUTH:
		return WEST;
            case WEST:
		return NORTH;
	}
	throw new IllegalStateException();
    }
}


public class VacAgent extends Agent {    
    private final String ID = "1";
    private VacPercept currentPercept;
    private Node currentNode;
    private Orientation facing;
    
    private AgentMap nodeMap = new AgentMap();
	
    public VacAgent() {
	this(new AgentMap());
    }
	
    public VacAgent(AgentMap nodeMap) {
	this.nodeMap = nodeMap;
		
        /*Initializing the current node for the AgentMap*/
        currentNode = nodeMap.getNode(0, 0);
	currentNode.setType(NodeType.Free);
	currentNode.setVisited(true);
	facing = Orientation.NORTH;
    }
	
    
    @Override
    public void see(Percept p) {
    	this.currentPercept = (VacPercept)p;
    }

    @Override
    public Action selectAction() {
	Node facingNode = currentNode.getNode(facing); // The next node
        
        /*If there is an obstacle. Puts a type to the facing node*/
	if(currentPercept.seeObstacle()) facingNode.setType(NodeType.Wall);
        else facingNode.setType(NodeType.Free);
	
        // If the node is dirty
	if(currentPercept.seeDirt()) currentNode.setDirty(true);
	
        // Select an action according the current situation in AgentMap
	Action action = determineAction(currentNode);

        /*Updates the node according the determinated action*/
        if(action instanceof SuckDirt)
            currentNode.setDirty(false);
        else if(action instanceof GoForward) {
            currentNode.setVisited(true);
            currentNode = currentNode.getNode(facing);
            
            if(!currentNode.isFree())
                throw new IllegalStateException("__");
	} 
        else if(action instanceof TurnLeft)
            facing = facing.rotateLeft();
        else if(action instanceof TurnRight)
            facing = facing.rotateRight();
	
        // A queue that contains the latest nodes
        nodeMap.addToQueue(currentNode); 
        
        // Evaluates the current node and situation to take a decision
        nodeMap.evaluate(currentNode);
        
	return action;
    }

    // Determines an action according the situation in the AgentMap
    private Action determineAction(Node currentNode) {
        // Shut off if in AgentMap is an error
	if(nodeMap.isComplete()) return new ShutOff();
        if(nodeMap.isStuck()) return new ShutOff();
		
        // If it is dirty
	if(currentNode.isDirty()) return new SuckDirt();

        // Getting the node in front (facing)
	Node forwardNode = currentNode.getNode(facing);
        
        if(forwardNode.isFree() && !forwardNode.isVisited())
            return new GoForward();
  
        
        // Selects a new direction according the situation
        Direction turnDirection = nextDirection(currentNode);
	
        /*Turns where the new direction */
        if(turnDirection != null){
            if(turnDirection == Direction.LEFT)
		return new TurnLeft();
            if(turnDirection == Direction.RIGHT) 
		return new TurnRight();
	}
		
        
	Node node = nearFree(currentNode);
       
        if(node != null) {
            Orientation turnToDirection = null;
            
            if(node.getY() > currentNode.getY()) { 
		turnToDirection = Orientation.NORTH;
            } 
            else if(node.getX() > currentNode.getX()) { 
		turnToDirection = Orientation.EAST;
            } 
            else if(node.getX() < currentNode.getX()) {
            	turnToDirection = Orientation.WEST;
            }
			
            if(facing == turnToDirection) {
                //nodeMap.addCount();
		return new GoForward();
            } 
            else if(turnToDirection == Orientation.NORTH) {
		if (facing == Orientation.WEST) {
                    return new TurnRight();
		} 
		else if (facing == Orientation.EAST) {
                    return new TurnLeft();
		}
            } 
            else if(turnToDirection == Orientation.EAST) {
		if(facing == Orientation.NORTH) {
                    return new TurnRight();
		} 
		else if(facing == Orientation.SOUTH) {
                    return new TurnLeft();
		}
            } 
            else if(turnToDirection == Orientation.WEST) {
		if(facing == Orientation.NORTH) {
                    return new TurnLeft();
		} 
		else if(facing == Orientation.SOUTH) {
                    return new TurnRight();
		}
            }
	}

	return new TurnLeft();
    }
	
    /*Chooses a direction according the state in AgentMap*/
    private Direction nextDirection(Node currentNode) {
	Orientation leftDirection = facing.rotateLeft();
	Node leftNode = currentNode.getNode(leftDirection);

        if(leftNode.isBlank()) return Direction.LEFT;

	Orientation rightDirection = facing.rotateRight();
	Node rightNode = currentNode.getNode(rightDirection);
	
        if(rightNode.isBlank()) return Direction.RIGHT;
		
	return null;
    }

    
    /*Checks the possible nodes for the next movement. Returns the best option*/
    private Node nearFree(Node currentNode) {
        List<WeightedNode> possibleNodes = new ArrayList<>();
	
	WeightedNode forwardNode = nearFree(currentNode, facing);
	WeightedNode leftNode = nearFree(currentNode, facing.rotateLeft());
	WeightedNode rightNode = nearFree(currentNode, facing.rotateRight());
	
        /*Adding possible nodes*/
        if(forwardNode != null) possibleNodes.add(forwardNode);
        if(leftNode != null) possibleNodes.add(leftNode);
	if(rightNode != null) possibleNodes.add(rightNode);
	
	WeightedNode closest = null;
	
        //Checks if there is a nearest or closest node
        for(WeightedNode weightedNode : possibleNodes)
            if(closest == null || weightedNode.weight < closest.weight)
		closest = weightedNode;
            
        if(closest == null) {
            return null;
	} 
        else {
            return closest.node;
	}
    }
	
    
    /*Checks the best option (with the less weight)*/
    private WeightedNode nearFree(Node currentNode, Orientation facing) {
    	int weight = 1;
	Node lookAtNode = currentNode.getNode(facing);
        Node laNNode = currentNode.getnNode();
        Node laSNode = currentNode.getsNode();
        Node laWNode = currentNode.getwNode();
        Node laENode = currentNode.geteNode();
      
	for(int distance = 0; ; distance++) {			
            if(laNNode.isWall() && laSNode.isWall() && laWNode.isWall() && laENode.isWall())
                lookAtNode.isWall();
         
            if(lookAtNode.isWall())
                return null;
            else if(lookAtNode.isBlank() || !lookAtNode.isVisited()) {
                WeightedNode wNode = new WeightedNode();
		wNode.weight = weight + distance;
		wNode.node = lookAtNode;
		return wNode;
            } 
            else {
                lookAtNode = lookAtNode.getNode(facing);
            }
	}
    }
	
    private static class WeightedNode {
    	public int weight;
	public Node node;
    }
    
    @Override
    public String getId() {
        return ID;
    }
}

