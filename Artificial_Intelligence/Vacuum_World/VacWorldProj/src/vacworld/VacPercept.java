package vacworld;

import agent.Percept;
import agent.Agent;

/** A percept in the vacuum cleaning world. */
public class VacPercept extends Percept{
    private final boolean dirt;
    private final boolean obstacle;
    private final boolean bump;

    private final int x;
    private final int y;
    private final int[] pair;
    
    /** Construct a vacuum world percept. If the agent is in a square that
      has dirt, then create a percept that sees dirt. If the agent is
      directly in front of and facing an obstacle, then the agent can
      see the obstacle. If the agent moved into an obstacle on the
      previous turn the agent will feel a bump.
     * @param state, a VacuumState object
     * @param agent, an Agent object
     */
    public VacPercept(VacuumState state, Agent agent) {
        super(state,agent);
        //int x, y;
	int viewX, viewY;
	int dir;

	x = state.getAgentX();
	y = state.getAgentY();
	dir = state.getAgentDir();
        
        pair = new int[2];
        pair[0] = x;
	pair[1] = y;

        // determine dirt
        dirt = state.hasDirt(x,y);

	// determine obstacle
	viewX = x + Direction.DELTA_X[dir];
	viewY = y + Direction.DELTA_Y[dir];
        obstacle = state.hasObstacle(viewX, viewY);

        // determine bump
        bump = state.bumped();
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public Integer[] getPair(){
        Integer[] p = new Integer[2];
        p[0] = x;
        p[1] = y;
        return p;
    }
    
    /**  
     * @return true if the percept reflects that the agent is over dirt.*/
    public boolean seeDirt() {
	return dirt;
    }

    /**
     * @return true if the percept reflects that the square immediately in 
        front of the agent contains an obstacle.
     */
    public boolean seeObstacle() {
	return obstacle;
    }

    /**
     * @return true if the percept reflects that the agent bumped into an 
        obstacle as a result of its most recent action.
     */
    public boolean feelBump() {
	return bump;
    }

    @Override
    public String toString() {
	StringBuffer pstring;
	pstring = new StringBuffer(5);

        if (dirt == true) pstring.append("DIRT ");
        if (obstacle == true) pstring.append("OBSTACLE ");
	if (bump == true) pstring.append("BUMP ");
        
	return pstring.toString();
    }
}
