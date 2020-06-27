/*
 *  Copyright 2019.
 *  Alejadnro Maruri.
 *  ale_maruri14@hotmail.com
 *  eamaruri@estud.usfq.edu.ec
 */
package myagent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


/*Creates a memory for the agent. A memory of the map and latest states*/
public class AgentMap {
    private int count = 0; // Count movements
    private boolean isStuck = false;
    
    /*Represents the map of agent*/
    private final HashMap<Integer, Map<Integer, Node>> nodeMap = new HashMap<>();
    
    /*Contains the latest actions of the agent*/
    public static Queue<Node> lastFourActions = new ArrayDeque<>();
    
    /*Returning a node*/
    public Node getNode(int x, int y) {
    	return getNode(x, y, true);   
    }
	
    private Node getNode(int x, int y, boolean create) {
	Map<Integer, Node> yMap;
	if(nodeMap.containsKey(x)){
            yMap = nodeMap.get(x);
	} 
	else {
            yMap = new HashMap<>();
            nodeMap.put(x, yMap);
	}
		
	if(yMap.containsKey(y)) {
            return yMap.get(y);
	} 
        else if(create) {
            Node newNode = new Node(this, x, y);
            yMap.put(y, newNode);
            return newNode;
	} 
        else {
            return null;
	}
    }
    
    /*Methods to know the state of the map*/
    public boolean isComplete() {return count > 36;}	
    public boolean isStuck(){return this.isStuck;}
    public void addCount(){this.count++;}
    
    /*Storing the latest actions*/
    public void addToQueue(Node node){
        if(lastFourActions.size() <= 7) // We have 8 Nodes
            lastFourActions.add(node);
        else{
            lastFourActions.poll();
            lastFourActions.add(node);
        }
    }
 
    
    /*Evaluates the current stat of the agent and its map*/
    public void evaluate(Node currentNode){
        int repetition = 0;
        int walls = 0;
        
        ArrayList<Node> entorno = new ArrayList<>();
        entorno.add(currentNode.geteNode());
        entorno.add(currentNode.getnNode());
        entorno.add(currentNode.getsNode());
        entorno.add(currentNode.getwNode());
        
        /*Count the quantity of walls around the node*/
        for(int i = 0; i < entorno.size(); i++)
            if(entorno.get(i).isWall())
                walls++;
        
        // System.out.println("Walls around " + walls);
        if(walls == 3){
            System.out.println("STUCK");
            currentNode.setStucked();
            for(int i = 0;i < entorno.size(); i++) {
                if(!entorno.get(i).isWall()){
                    entorno.get(i).setVisited(false);
                }                           
            }
        }     
        
        /*Checks if node is stuck (3 walls around)*/
        for(int i = 0; i < entorno.size(); i++)
            if(entorno.get(i).getStucked() && currentNode.getStucked())
               this.isStuck = true;
        
        
        /*Checking the las actions of agent*/
        for(Node n : lastFourActions){              
            if(n.getX() == currentNode.getX() && n.getY() == currentNode.getY()){
                repetition++;
               
                if(repetition == 8 && walls <= 2){
                    System.out.println("I don't know where to go!");
                    this.isStuck = true;
                }
            }
        }
    }
}

// Types of nodes
enum NodeType {Blank, Free, Wall}

/*Private class that represents a place in the map of the agent*/
class Node{
    private final AgentMap nodeMap;
    
    private int x = 0;
    private int y = 0;
    
    private boolean dirty = false;
    private boolean visited = false;
    private boolean stuk=false;
    
    private NodeType type = NodeType.Blank;
    
    private Node nNode;
    private Node sNode;
    private Node wNode;
    private Node eNode;
	
    public Node(AgentMap nodeMap, int x, int y) {
    	this.nodeMap = nodeMap;
    	this.x = x;
	this.y = y;
    }
	
    public int getX(){return x;}
    public int getY(){return y;}
    
    public boolean isDirty() {return dirty;}
    public void setDirty(boolean dirty) {this.dirty = dirty;}
    public boolean isVisited(){return visited;}
    
    public void setStucked(){this.stuk=true;}
    
    public boolean getStucked(){return this.stuk;}

    public void setVisited(boolean visited) {this.visited = visited;}
	
    public boolean isFree() {return this.getType() == NodeType.Free;}
	
    public boolean isBlank() {return this.getType() == NodeType.Blank;}

    public boolean isWall() {return this.getType() == NodeType.Wall;}
	
    
    /*Getting the around Nodes of the current node*/
    
    public Node getnNode() {
	if(nNode == null) {
            nNode = this.nodeMap.getNode(this.x, this.y + 1);
            nNode.sNode = this;
	}
	return nNode;
    }
	
    public Node getsNode() {
	if(sNode == null) {
            sNode = this.nodeMap.getNode(this.x, this.y - 1);
            sNode.nNode = this;
	}
	return sNode;
    }
	
    public Node getwNode() {
    	if(wNode == null) {
            wNode = this.nodeMap.getNode(this.x - 1, this.y);
            wNode.eNode = this;
	}
	return wNode;
    }
	
    public Node geteNode() {
    	if(eNode == null) {
            eNode = this.nodeMap.getNode(this.x + 1, this.y);
            eNode.wNode = this;
	}
	return eNode;
    }
	
    /*Returns a node given an Orientation*/
    public Node getNode(Orientation direction) {
    	switch (direction) {
            case NORTH:
		return this.getnNode();
            case SOUTH:
		return this.getsNode();
            case EAST:
		return this.geteNode();
            case WEST:
		return this.getwNode();
	}
	throw new IllegalStateException("Unknown direction " + direction);
    }

    public NodeType getType() {
	return type;
    }

    public void setType(NodeType type) {
    	this.type = type;
    }	
}
    