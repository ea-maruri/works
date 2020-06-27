Proyecto realizado por Alejandro Maruri 136515

Realizado en JAVA

README OF THE PROJECT AStar for 8Puzzle. READ CAREFULLY.  

/*
 *  Alejandro Maruri
 *  eamaruri@estud.usfq.edu.ec
 *  30/09/2019
 */

_______________________________________________________________________________________________________
The program solve instances of 8-puzzle. 							      -
Uses the A* informed search algorithm for every case using the heuristic:       		      -
	-1) The Manhattan distance.								      -
	-2) A learned heuristic (creating a number of random configurations and making a regression)  -
_______________________________________________________________________________________________________


---------DANIEL, use this to see the example that uses the given states in the project's specification--------
_______________________________________________________________________________________________________
For an example (get 3 configurations and solutions):
	- Insert the number of random configurations you want to use (e.g: 100)
	- Use option "e" to see a example and see results***. 
	- Choose the heuristic you want to use (1,2).
	- Finally, choose if you want to see the expanded nodes (the search of the solution).
________________________________________________________________________________________________________


*************
**IMPORTANT**
*************
______________________________________________________________________________________________________________________________
How the program works:
	1) Asks to for the quantity of random configurations (minimum 3).
		Then:
			-Creates random configurations. Discards those with odd number of inversions.
			-Search a solution for each one using the Manhattan heuristic.
			-Creates arrays to contain X and Y for REGRESSION.
			-Tries to solve B = (X^T * X)^-1 * X^T * Y.
				-Can cause an error, because the determinant of "X^T * X" is zero.
					That means it is not an inverse matrix.
					If it happens, you must repeat the first step until it works
			-If all is OK, then obtains the B coeficients (b0, b1, b2).
			-So, when we use the "learned heuristic" which is h(n)=b0+b1X1+b2X2.
			
	2) The program asks to user if he wants to create a problem (y/n/e).
		-If answer is "n", then the program terminate.
		-If answer is not "n" nor "y" the program asks again.
		****-If answer is "e" you can use a fixed example***
			-You need to enter which heuristic you want to use.
			-You need to enter if you you wnat to see the solution (explored set).
		-If answer is "y", then the program asks the heuristic that user wants to use (1/2).
			-1 is for Manhattan heuristic.
			-2 is for learned heuristic.
		-According to the chosen option, the program searchs a solution.

	3) Program asks for the initial and goal states (value, one by one to complete a 3x3 matrix).
		-*It is important to use a single space (" ") instead of "0" (zero)*.
		-The program send the problem (initial and goal states) to SearchSolution class.

	4) SearchSolition class uses the chosen heuristic to solve the problem.
		-The method "uniform_cost_search" starts using "A* informed search algorithm".
		-When the algorithm finds a solution prints the sequence of steps to find the solution.
		-Finally prints the solution and some statistics: Expanded nodes, Generated nodes, number of moves, time taken.
		-Goes to the 3) step.

	NOTES:
		- If for any reason it is delayed, it is better to close the program and create a better heuristic***.
		- Many times, the result of (X^T*X) has a determinant of zero, and do not have an inverse matirx.
______________________________________________________________________________________________________________________________________


______________________________________________________________________________________________
The implemented algorithm is:
						 
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

Using three methods in the SearchSolution class: 
	-initSolution: Recieves a problem and the heuristic that will be used. 
		Also initialize the data structures.
	-loopDo: Implements all the loop do of the algorithm.
		Uses a method called "verifyChildrenNodes" that implements the "for each".
	-printSolution: Prints the sequence of actions taken by the algorithm to reach the goal.
		Prints the statistics too.
________________________________________________________________________________________________


__________________________________________________________________________________________________________
package used:
	package astar_8puzzle_f;
Classes:
	AStar_8Puzzle_F:
		-Asks many things to user, in order to responds him:
			-Create random configurations.
			-Create a problem: initial and goal states.
			-Which heuristic to use.
			-Use the example. It is important only for see results.
		-Call methods of SearchSolution and GenerateRandomConfig classes.
	SearchSolution:
		-Implements the algorithm "uniform-cost-search" to search the solution for the problem.
		-Prints the solution and statistics.
	GenerateRandomConfig:
		-Generates random configurations using a method of Fisher_Yates_Array_Shuffling class
		-Collect the values for the X and Y (as arrays) for te regression (learned heuristic).
		-Do not uses the random congurations which number of inversions is an odd number.
		-Calculates B = (X^T * X)^-1 * X^T * Y and stores the B coeficients.
	Fisher_Yates_Array_Shuffling:
		-Has a method to generate a random congifuration given an array.
		-Has a method to count the number of inversions of the random configuration created.
	State:
		-Contents the configuration of a 8-puzzle as a matrix.
		-Calculates Manhattan with an initial and goal state.
		-Calculates the displaces tiles according the initial and goal states.
		-Calculates the pairs of tiles that not are adjacents.
	Node:
		-Contents a State object as an attribute.
		-Has the method "expand" to expand a node. Getting all the possible children of a node.
		-Implements the interface "Comparable". 
			It uses f(n) of each node (state) to put nodes in the frontier (a priority queue).
___________________________________________________________________________________________________________


_______________________________________________________________________________
EXAMPLE OF A PROGRAM (Using the Example):
Create problem or Use Example? (y,n,e): e
Making Example
Which heuristic do you want to use? (1,2): 1
Using Manhattan
Print Solution?(y/n)
n

Statistics
*Total Nodes expanded: 3 (does not include the initial node: 4)
*Total Nodes generated: 10 (does not include the goal and initial nodes: 12)
*Number of moves made: 3
*Time taken: 1(ms)

Statistics
*Total Nodes expanded: 8 (does not include the initial node: 9)
*Total Nodes generated: 22 (does not include the goal and initial nodes: 24)
*Number of moves made: 8
*Time taken: 0(ms)

Statistics
*Total Nodes expanded: 13 (does not include the initial node: 14)
*Total Nodes generated: 34 (does not include the goal and initial nodes: 36)
*Number of moves made: 10
*Time taken: 1(ms)

Create problem or Use Example? (y,n,e): e
Making Example
Which heuristic do you want to use? (1,2): 1
Using Manhattan
Print Solution?(y/n)
y

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

Statistics
*Total Nodes expanded: 3 (does not include the initial node: 4)
*Total Nodes generated: 10 (does not include the goal and initial nodes: 12)
*Number of moves made: 3
*Time taken: 71(ms)

...
...
...
_______________________________________________________________________________


_________________________________________________________________________________
EXAMPLE OF A PROGRAM (WITHOUT CREATING RANDOM CONFIGURATIONS):


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



