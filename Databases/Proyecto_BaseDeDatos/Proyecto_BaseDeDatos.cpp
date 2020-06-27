#include<iostream>

using namespace std;
using std::cin;
class cPersona{
	protected:
		string nombre, apellido, direccion, numero;
		
	public:
		cPersona(){}
		cPersona(string pNombre, string pApellido, string pDireccion, string pNumero){
			nombre = pNombre;
			apellido = pApellido;
			direccion = pDireccion;
			numero = pNumero;
		}
		
		void setNombre(string pNombre){nombre = pNombre;}
		void setApellido(string pApellido){apellido = pApellido;}
		void setDireccion(string pDireccion){direccion = pDireccion;}
		void setNumero(string pNumero){numero = pNumero;}
		
		string getNombre(){return nombre;}
		string getApellido(){return apellido;}
		string getDireccion(){return direccion;}
		string getNumero(){return numero;}
};

class cComida{
	protected:
		string nombreC;
		float valorUnitario, valorDocena, valorCentena;
		int codigoId;
	public:
		cComida(){}
		cComida(string pNombre, float pValorUnitario, float pValorDocena, float pValorCentena, int pCodigo){
			nombreC = pNombre;
			valorUnitario = pValorUnitario;
			valorDocena = pValorDocena;
			valorCentena = pValorCentena;
			codigoId = pCodigo;
		}
		
		void setNombre(string pNombre){nombreC = pNombre;}
		void setValorUnitario(float pValorUnitario){valorUnitario = pValorUnitario;}
		void setValorDocena(float pValorDocena){valorDocena = pValorDocena;}
		void setValorCentena(float pValorCentena){valorCentena = pValorCentena;}
		void setCodigo(int pCodigo){codigoId = pCodigo;}
		
		string getNombre(){return nombreC;}
		float getValorUnitario(){return valorUnitario;}
		float getValorDocena(){return valorDocena;}
		float getValorCentena(){return valorCentena;}
		int getCodigo(){return codigoId;}
	
};

class cCantidad{ //Necesario controlar la cantidad de personas
	protected:
		cPersona Cantidad[2]; //Prototipo
		cComida CantidadC[2]; //Prototipo
	public:
		cCantidadPersonas();
		
		void setPersonasAt(int pIndex, cPersona pPersona){Cantidad[pIndex] = pPersona;}
		void setComidasAt(int pIdx, cComida pComida){CantidadC[pIdx] = pComida;}
		
		cPersona getPersona(int pIndex){return Cantidad[pIndex];}	
		cComida getComida(int pIdx){return CantidadC[pIdx];}
};

int main(){
	string nombre, apellido, direccion, numero;
	cCantidad cantidadP;
	cCantidad cantidadC;
	
	for(int i = 0; i < 2; i++){ //Manera Dinámica de entrada de datos de las personas
		cPersona PersonaA; //Creación de objeto
		cout << "Nombre: ";
		cin >> nombre;
		PersonaA.setNombre(nombre);
		cout << "\nApellido: ";
		cin >> apellido;
		PersonaA.setApellido(apellido);
		cout << "\nDireccion: ";
		cin >> direccion;
		PersonaA.setDireccion(direccion);
		cout << "\nNumero: ";
		cin >> numero;
		PersonaA.setNumero(numero);		
		cantidadP.setPersonasAt(i, PersonaA); //Almacenar Objeto
	}
	
	string nombreC;
	float valorUnitario, valorDocena, valorCentena;
	int codigoId;
		
	for(int i = 0; i < 2; i++){ //Manera Dinámica de entrada de datos de comida
		cComida ComidaA; //Creación de objeto
		cout << "Nombre: ";
		cin >> nombreC;
		ComidaA.setNombre(nombreC);
		cout << "\nValorUnitario: ";
		cin >> valorUnitario;
		ComidaA.setValorUnitario(valorUnitario);
		cout << "\nValorDocena: ";
		cin >> valorDocena;
		ComidaA.setValorDocena(valorDocena);
		cout << "\nValorCentena: ";
		cin >> valorCentena;
		ComidaA.setValorCentena(valorCentena);
		cout << "\nCodigo: ";
		cin >> codigoId;
		ComidaA.setCodigo(codigoId);
		cantidadC.setComidasAt(i, ComidaA);	
	}
	
	for(int i = 0; i < 2; i++){
		cout << endl << endl << endl;
		cout << "Nombre: " << cantidadP.getPersona(i).getNombre() << "\nApellido: " << cantidadP.getPersona(i).getApellido() 
		<< "\nDireccion: " << cantidadP.getPersona(i).getDireccion() << "\nNumero: " << cantidadP.getPersona(i).getNumero();
	}
	for(int i = 0; i < 2; i++){
		cout << endl << endl << "Nombre: " << cantidadC.getComida(i).getNombre() << "\nValorUnitario: " << cantidadC.getComida(i).getValorUnitario() 
		<< "\nValorDocena: " << cantidadC.getComida(i).getValorDocena() << "\nValorCentena: " << cantidadC.getComida(i).getValorCentena() 
		<< "\nCodigo: ";
		cout << cantidadC.getComida(i).setNombre(nombreC);
	}
	
	return 0;
}
