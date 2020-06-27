/*
 *  Copyright 2019.
 *  Alejandro Maruri
 *  eamaruri@estud.usfq.edu.ec
 */

__________________________________________________________________________________
The program solve instances of 8-puzzle. 
Uses the A* informed search algorithm for all three cases using the heuristic:   -
	-The Manhattan distance							 -
__________________________________________________________________________________


______________________________________________________________________________________________
The Implemented algorithm 							 
function UNIFORM-COST-SEARCH(problem) returns a solution, or failure
	node ←a node with STATE = problem.INITIAL-STATE, PATH-COST = 0
	frontier ←a priority queue ordered by PATH-COST, with node as the only element
	explored ←an empty set
	loop do
		if EMPTY?( frontier) then return failure
		node←POP( frontier ) /* chooses the lowest-cost node in frontier */
		if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
		add node.STATE to explored
		for each action in problem.ACTIONS(node.STATE) do
			child ←CHILD-NODE(problem, node, action)
			if child .STATE is not in explored or frontier then
			frontier ←INSERT(child , frontier )
			else if child .STATE is in frontier with higher PATH-COST then
			replace that frontier node with child
________________________________________________________________________________________________


_________________________________________________________________________________________________________
The program asks to a user if he wants to create a problem.
If answer is "y", the program recieves a configuration of a 8-puzzle (validates the entries or inputs).
Then, searchs a solution using the obove algorithm. 
For each generated node, prints f(n) and the puzzle.
Finally, prints the solution as a sequence of actions and the total of expanded and generated nodes
	and the time taken in ms (milli-seconds).
If answer is "n" the program terminate.
If answer is any other key, the program asks again.
_________________________________________________________________________________________________________


______________________________________________________________________________________________
package used:
	package astar_8puzzle_f;
Classes:
	AStar_8Puzzle_F.java:
		Initializes the problem, asks for initial and goal states, 
	        and if all is ok call to algorithm of search (uniform-cost-search).
	Search_Solution:
		Implements the algorithm os uniform-cost-search to search the solution.
	State:
		Contents the configuration of a 8-puzzle as a matrix, calculates manhattan.
	Node:
		Contents a State object. This class has the method expand to expand a node.
		Implements the interface "Comparable". It compares the path-cost in order to
			put a node in the frontier (a priority queue).
_______________________________________________________________________________________________


_________________________________________________________________________________
EXAMPLE OF A PROGRAM

----------------------------------------------------------
-- Welcome to A* informed search algorithm for 8-Puzzle --
----------------------------------------------------------

Create problem? (y,n): y

Please input the Initial State
1
2
3
4
6
 
7
5
8

Please input the Goal State:
1
2
3
4
5
6
7
8
 
f(n) = manhattan + depth: 3 = 3 + 0
1	2	3	
4	6	 	
7	5	8	

f(n) = manhattan + depth: 0 = 0 + 0
1	2	3	
4	5	6	
7	8	 	

f(n) = manhattan + depth: 5 = 4 + 1
1	2	 	
4	6	3	
7	5	8	

f(n) = manhattan + depth: 3 = 2 + 1
1	2	3	
4	 	6	
7	5	8	

f(n) = manhattan + depth: 5 = 4 + 1
1	2	3	
4	6	8	
7	5	 	

f(n) = manhattan + depth: 5 = 3 + 2
1	 	3	
4	2	6	
7	5	8	

f(n) = manhattan + depth: 5 = 3 + 2
1	2	3	
 	4	6	
7	5	8	

f(n) = manhattan + depth: 3 = 1 + 2
1	2	3	
4	5	6	
7	 	8	

f(n) = manhattan + depth: 5 = 3 + 2
1	2	3	
4	6	 	
7	5	8	

f(n) = manhattan + depth: 5 = 2 + 3
1	2	3	
4	 	6	
7	5	8	

f(n) = manhattan + depth: 5 = 2 + 3
1	2	3	
4	5	6	
 	7	8	

f(n) = manhattan + depth: 3 = 0 + 3
1	2	3	
4	5	6	
7	8	 	


---SOLUTION---

Node 1 in explored set:
1	2	3	
4	6	 	
7	5	8	
f(n):3
h(n):3
g(n):0

Node 2 in explored set:
1	2	3	
4	 	6	
7	5	8	
f(n):3
h(n):2
g(n):1

Node 3 in explored set:
1	2	3	
4	5	6	
7	 	8	
f(n):3
h(n):1
g(n):2

Node 4 in explored set:
1	2	3	
4	5	6	
7	8	 	
f(n):3
h(n):0
g(n):3

*Total Nodes expanded: 3 (does not include the initial node: 4)
*Total Nodes generated: 10 (does not include the goal and initial nodes: 12)
*Time taken: 80(ms)


Create problem? (y,n): n

Program Teminated!!!

BUILD SUCCESSFUL (total time: 29 seconds)
____________________________________________________________________________________________



