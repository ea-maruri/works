// Alejandro Maruri


#include<iostream>
#include<iomanip>
#include<ctime>
#include<cstdlib>

using namespace std;


// Prototype functions
void printEmptyTable(int[8][8], int[], int[], const int); 
void printTable(int[8][8], const int, const int, int[], int[], const int, int);
void printFinal(int [8][8], int[], int[], const int); // Prints final result, if problem is solved.
void EmptyTable(int[8][8]); 

// Search coord "x" or "y" where queens are allocated. Returns coordenate.
int FirstCoord(int[8][8], const int, const int, int); 
int SecondCoord(int[8][8], const int, const int, int); 

bool putQueens(int[8][8], const int, const int, int[], int[], const int, int); // Finds an empty space to put a queen. Return true if all is OK
bool CountQueens(int[8][8]); // Advice if the 8 queens were allocated.
int Counter(int[8][8]); // Count quantity of allocated queens

int Test(int[8][8]); // Count available spaces. Count "77" in table.


int main(){	
	const int x = 8, y = 8; // Sizes
	int centinel = 0, start = 1;
	
	int Table[8][8] = {};
	int X[x] = {0,1,2,3,4,5,6,7}, Y[y] = {0,1,2,3,4,5,6,7}, w, z, k, f, a, b, s, c, aa, bb, l, d = 0, q = 2, r, entrada;
	
	cout << setw(87) << "The 8 queens: " << endl << endl 
	<< "-Program will start to generat possible random solutions until a maximmum of 100 opportunities." << endl
	<< "-If program do not find solution, in the maximum number (100), other coordenates are asked." << endl
	<< "-Numbres 1 to 8 represents allocated queens." << endl << "-Number \"77\" represents empty spaces." << endl
	<< "-Number \"0\" represents available spaces." << endl << endl;
		
	while(f != centinel){	
		q = 2;
		k = 0;
		d = 0;
		cout << endl << "Si desea empezar ingrese \"1\", sino, ingrese \"0\": ";
		cin >> f;	
	
		if(f < 0)
			{cout << "Error!!!" << endl;
			continue;
			}
		else if(f == centinel)
			{
			cout << endl << endl << setw(91) << "Ha salido del programa!!!" << endl << endl;
			break;
			}	
		else if(f == start)
			{
			while(k == 0)
				{	if(q == 1)
						break;
					q++;
					cout << endl << endl << setw(87) << "Tablero vacio: " << endl << endl;	
					printEmptyTable(Table, Y, X, x);
					cout << "\nIngrese la coordenada \"x\" (0-7), para colocar la primera reina: ";
					cin >> w;
				
					cout << "\nIngrese la coordenada \"y\" (0-7), para colocar la primera reina: ";
					cin >> z;
					cout << endl << endl << setw(98) << "El tablero con la primera reina, \"1\": " << endl << endl;
					cout << setw(86) << endl << " Intento numero 1" << endl;
					
					while(d == 0)
					{
						if(k == 0)
						{	
							aa = 1;
							bb = 2;
							Table[z][w] = 1;
							
							int i = FirstCoord(Table, a, b, aa); 
							int j = SecondCoord(Table, a, b, aa);
							cout << "x = " << j << endl;
							cout << "y = " << i << endl;
							printTable(Table, j, i, Y, X, x, aa);
							
							srand( time ( 0 ) );
							for(int h = 0; h < 7; h++)
							{	aa++;
								do{
								a = rand() % 8;
								b = rand() % 8;
								
								s = putQueens(Table, a, b, Y, X, x, aa);
								
								}while(s == false);
								
								int f = FirstCoord(Table, a, b, aa); 
								int g = SecondCoord(Table, a, b, aa);
								cout << "x = " << g << endl;
								cout << "y = " << f << endl;
								
								printTable(Table, g, f, Y, X, x, aa);
								c = Test(Table);
								if(c == 0)
									break;
							}
							l = Counter(Table);
							k = CountQueens(Table);
							if(k == false)
								{
								cout << endl << "No se encontro una solucion. Se colocaron " << l << " Reinas" << endl; 
								cout << setw(86) << endl << endl << "Intento numero " << q  << endl;
								d = 0;
								q++;
								EmptyTable(Table);
								}
							else if (k == true)
								{q--;
								cout << endl << setw(100) << "*Se encontro una solucion en el intento numero " << q << "*" << endl << endl;
								printFinal(Table, Y, X, x);
								d = 1;
								q = 2;
								cout << endl << setw(87) << "Fin de la ejecucion" << endl << endl;
								}
						}
							if(q == 101)
							{	d = 1;
								q = 1;
								cout << endl << "No se encontro una solucion. Se colocaron " << l << " Reinas" << endl << endl << setw(90) << "Intentelo de nuevo!!!" << endl; 
								break;
							}
					}
				}
			continue;
			}
		else if(f > 1)
			{cout << "Error!!!" << endl;
			continue;
			}
	}
	cout << endl << endl << setw(88) << "Fin del programa!!!" << endl << endl;
	
	return 0;
}

void printEmptyTable(int A[8][8], int Y[], int X[], const int x){
	cout << setw(66) << "x   ";
    
	for(int i = 0; i < x; i++)
		cout  << X[i] << setw(4); 
			
	cout << endl  << setw(60) << "y" << endl << endl;	
	
	for(int i = 0; i < 8; i++)
	{	
		cout << setw(60) << Y[i] << "   ";
		
		for(int j = 0; j < 8; j++)
			{
				A[i][j] = 77;
				cout << setw(4) << A[i][j];	
	   		}
	   	cout << endl << endl;
	}	
} 
void EmptyTable(int A[8][8])
{
	for(int i = 0; i < 8; i++)
		for(int j = 0; j < 8; j++)
			A[i][j] = 77;
} 
int FirstCoord(int C[8][8], const int x, const int y, int aa)
{	int a;
	int c = aa;

	for(int i = 0; i < 8; i++)
	{	
		for(int j = 0; j < 8; j++)
		{			
			if(C[i][j] == c)
				{a = i;
				c++;
   				}
		}
	}
	return a;
}  
int SecondCoord(int D[8][8], const int x, const int y, int bb)
{	int b;
	int d = bb;
	
	for(int i = 0; i < 8; i++)
	{	
		for(int j = 0; j < 8; j++)
		{			
			if(D[i][j] == d)
				{
				b = j;
				d++;
				}
   		}
	}
	return b;
}  
void printTable(int B[8][8], const int w, const int z, int Y[], int X[], const int x, int jj)
{	int a = z - 1, b = w - 1;
	int c = z - 2, d = w - 2;
	int e = z - 3, f = w - 3;
	int g = z - 4, h = w - 4;
	int k = z - 5, l = w - 5;
	int m = z - 6, n = w - 6;
	int o = z - 7, p = w - 7;
	int aa = z - 1, bb = w + 1;
	int cc = z - 2, dd = w + 2;
	int ee = z - 3, ff = w + 3;
	int gg = z - 4, hh = w + 4;
	int kk = z - 5, ll = w + 5;
	int mm = z - 6, nn = w + 6;
	int oo = z - 7, pp = w + 7;
	int q = z + 1, r = w + 1;
	int s = z + 1, t = w - 1;
	int zz = jj;
	
	cout << endl << endl << setw(66) << "x   ";
    
	for(int i = 0; i < x; i++)
		cout  << X[i] << setw(4); 
			
	cout << endl  << setw(60) << "y" << endl << endl;	
	
	for(int i = 0; i < 8; i++)
	{	
		cout << setw(60) << Y[i] << "   ";
			
		for(int j = 0; j < 8; j++)
		{	if(B[i][j] != 0)
				B[i][j] = B[i][j];
			if(j == w && j == z)
				B[i][j] = 0;		
			if(j == w)
				B[i][j] = 0;
			if(i == z)
				B[i][j] = 0;
			if(j == w && i == z)
				B[i][j] = zz;	
			if(j == t && i == s)
				{B[i][j] = 0;
				s++;
				t--;
			 	}
			if(j == r && i == q)
				{B[i][j] = 0;
				q++;
				r++;
				}
			if(j == p && i == o)
				B[o][p] = 0;				
			if(j == n && i == m)
				B[m][n] = 0;
			if(j == l && i == k)
				B[k][l] = 0;
			if(j == h && i == g)
				B[g][h] = 0;
			if(j == f && i == e)
				B[e][f] = 0;
			if(j == d && i == c)
				B[c][d] = 0;
			if(j == b && i == a)
				B[a][b] = 0;
			if(j == pp && i == oo)
				B[oo][pp] = 0;
			if(j == nn && i == mm)
				B[mm][nn] = 0;
			if(j == ll && i == kk)
				B[kk][ll] = 0;
			if(j == hh && i == gg)
				B[gg][hh] = 0;
			if(j == ff && i == ee)
				B[ee][ff] = 0;
			if(j == dd && i == cc)
				B[cc][dd] = 0;
			if(j == bb && i == aa)
				B[aa][bb] = 0;
		
			cout << setw(4) << B[i][j];	
   		}
   		cout << endl << endl;
	}
	zz++;
} 
bool putQueens(int C[8][8], const int a, const int b, int Y[], int X[], const int x, int aa)
{	int r = aa;

	if(C[b][a] == 0 || C[b][a] == 1 ||C[b][a] == 2 || C[b][a] == 3||C[b][a] == 4 || C[b][a] == 5 || C[b][a] == 6 || C[b][a] == 7 || C[b][a] == 8)
		return false;
	else if(C[b][a] == 77)
		{
		C[b][a] = r;
		r++;
		return true;
		}
}
int Test(int F[8][8])
{
	int c = 0;
	for(int i = 0; i < 8; i++)
	{
		for(int j = 0; j < 8; j++)
		{
			if(F[i][j] == 77)
				c++;
		}	
	}
	return c;
}
bool CountQueens(int C[8][8])
{	int k = 0;
	for(int i = 0; i < 8; i++)
	{		
		for(int j = 0; j < 8; j++)
		{			
			if(C[i][j] == 1 || C[i][j] == 2 || C[i][j] == 3 || C[i][j] == 4 || C[i][j] == 5 || C[i][j] == 6 || C[i][j] == 7 || C[i][j] == 8)	
				k++;
		}
	}
	if(k == 8)
		return true;
	else
		return false;
}
int Counter(int G[8][8])
{int k = 0;
	for(int i = 0; i < 8; i++)
	{
		for(int j = 0; j < 8; j++)
		{
			if(G[i][j] == 1 || G[i][j] == 2 || G[i][j] == 3 || G[i][j] == 4 || G[i][j] == 5 || G[i][j] == 6 || G[i][j] == 7 || G[i][j] == 8)	
				k++;
		}	
	}
	return k;
}
void printFinal(int H[8][8], int Y[], int X[], int x)
{
	cout << setw(66) << "x   ";
    
	for(int i = 0; i < x; i++)
		cout  << X[i] << setw(4); 
			
	cout << endl  << setw(60) << "y" << endl << endl;	
	
	for(int i = 0; i < 8; i++)
	{	
		cout << setw(60) << Y[i] << "   ";
		
		for(int j = 0; j < 8; j++)
			{
				cout << setw(4) << H[i][j];	
	   		}
	   	cout << endl << endl;
	}
}
