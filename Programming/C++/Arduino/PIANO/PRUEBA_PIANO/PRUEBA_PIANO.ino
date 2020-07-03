int pinBocina = A0; //Pin que se asigna a la bocina

String inicio = "public class Test{/n/tpublic static void main(String[] args){";
const int numBotones = 12;
int valorLeido[numBotones];
int valorAnterior[numBotones];

/**************NOTAS**************/
int fre_DO = 261, fre_DO_SOST = 277, fre_RE = 277, fre_RE_SOST = 311, fre_MI = 330, fre_FA = 350, fre_FA_SOST = 370, fre_SOL = 392;
int fre_SOL_SOST = 415, fre_LA = 440, fre_LA_SOST = 466, fre_SI = 493;
int notas[12] = {fre_SI, fre_LA_SOST, fre_LA, fre_SOL_SOST, fre_SOL, fre_FA_SOST, fre_FA, fre_MI, fre_RE_SOST, fre_RE, fre_DO_SOST, fre_DO}; //Arreglo con notas

long int eax, ebx, ecx, edx;
int counter;


int indicador = 0;
int pos = 0 ;
int numBinario[4];
String activeRegister;

void tocarTono(int pinDeBocina, int notaATocar){
  tone(pinDeBocina, notas[notaATocar]);
  delay(150);
  noTone(pinDeBocina);    
}

void printRegistros(){
  Serial.print("EAX="); Serial.print(eax); Serial.print(", EBX="); Serial.print(ebx); 
  Serial.print(", ECX="); Serial.print(ecx); Serial.print(", EDX="); Serial.print(edx);
  Serial.println("----------------------");
}

long int myPow( long int num, long int pot);

void setup(){
  Serial.begin(9600);
  /*Inicia pines*/
  for(int i = 2; i < numBotones; i++){
    pinMode(i, INPUT_PULLUP);       // Recorremos cada pin y lo ponemos como entrada
    valorAnterior[i]=1;        // Inicializamos el valor anterior a 1 (no pulsado)
  }
//  pinMode(A2, INPUT_PULLUP);

}

void loop(){
  
  for(int i = 0; i < numBotones; i++){
    valorLeido[i] = digitalRead(i+2); 

    if(valorLeido[i] == 0 && valorLeido[i] != valorAnterior[i] ){

      // para que suene siempre y cuando no este ingresando un numero
      if( indicador <= 0 ){
        Serial.print("Tecla "); Serial.print(i);
        tocarTono(pinBocina,i);
      }

      // ingreso un numero para los registros
      if( indicador > 0 && valorLeido[i] != valorAnterior[i] ){

        if(valorLeido[11] == 0 && indicador >0 && valorLeido[i] != valorAnterior[i]){
          numBinario[pos] = 0;
          pos++;
          tocarTono(pinBocina,11);
          indicador ++;
          
        }
        if(valorLeido[10] == 0 && indicador >0 && valorLeido[i] != valorAnterior[i]){
          numBinario[pos] = 1;
          pos++;
          tocarTono(pinBocina,10);
          indicador ++;
          
        }
        if(indicador ==5 )
        {
          // reset 
          indicador =0;
          pos = 0;
          
          if( activeRegister == "EBX"){
            ebx = numBinario[0]*8 + numBinario[1]*4 + numBinario[2]*2 + numBinario[3];
  
            Serial.println ("\nEBX = ");
            Serial.println(ebx);
          }
          else if( activeRegister == "ECX"){
            ecx = numBinario[0]*8 + numBinario[1]*4 + numBinario[2]*2 + numBinario[3];
  
            Serial.println ("\nECX = ");
            Serial.println(ecx);
          }

          activeRegister = "";

          break; // probamos
          
        }
      }

      
      
  
      if(i == 0 && indicador <=0){ //Indica impresion a archivo
        Serial.println(" SI: Archivo"); Serial.println("----------");
        ebx = 0, ecx = 0;
        printRegistros();
      }
      if(i == 1 && indicador <=0){ //Indica print
        Serial.println(" LA#: Imprime registros"); Serial.println("----------");
        printRegistros();
      }
      if(i == 2 && indicador <=0){ //Indica ECX
        Serial.println(" soy LA: ECX "); Serial.println("----------");
        indicador++; // clave para ingresar numeros 
        activeRegister = "ECX";
        Serial.println("ingrese numero en ECX"); Serial.println("----------");
      }
      if(i == 3 && indicador <=0){ //Da un valor a EBX
        Serial.println("   Soy SOL#: EBX"); Serial.println("----------");
        indicador++; // clave para ingresar numeros 
        activeRegister = "EBX";
        Serial.println("ingrese numero en EBX"); Serial.println("----------");
      }
      if(i == 4 && indicador <=0){ //Indica reseteo de registros
        Serial.println("   Soy SOL: RESET Registros"); Serial.println("----------");
        eax = 0; ebx = 0; ecx = 0; edx = 0;
        
      }
      if(i == 5 && indicador <=0){ //Indica la raiz cuadrada del acumulador
        Serial.println("   Soy FA#: sqrt EAX"); Serial.println("----------");
        edx = sqrt(eax);
        eax = edx;
        
      }
      if(i == 6 && indicador <=0 ){ //Indica Division truncada
        Serial.println("   Soy FA: Division"); Serial.println("----------");
        edx = ebx / ecx;
        eax += edx;
        
        
      }
      if(i == 7 && indicador <=0){ //Indica multiplicacion
        Serial.println("   Soy MI: Multiplicacion"); Serial.println("----------");
        edx = ebx * ecx;
        eax += edx;
        
        
      }
      if(i == 8 && indicador <=0){ //Indica resta
        Serial.println("   Soy RE#: Resta"); Serial.println("----------");
        edx = ebx + ecx;
        eax -= edx;
        
      }
      if(i == 9 && indicador <=0){ //Indica suma
        Serial.println("   Soy RE: Suma"); Serial.println("----------");
        edx = ebx + ecx;
        eax += edx;
        
      }
      if(i == 10 && indicador <=0){ //Indica Raiz cuadrada de la suma de los dos registros
        Serial.println("   Soy DO#: sqrt dos registros"); Serial.println("----------");
        edx = sqrt ( ebx + ecx );
        eax += edx;
      }
      if(i == 11 && indicador <=0){ //Indica Potenciacion
        Serial.println("   Soy DO: potenciacion"); Serial.println("----------");
        edx = myPow(ebx, ecx);
        Serial.print("EBX VALE!: ");
        Serial.println(ebx);
        Serial.print("ECX VALE!: ");
        Serial.println(ecx);
        Serial.print("EDX VALE!: ");
        Serial.println(edx);
        eax += edx;
      }
      if(i == 12 && indicador <=0){ //Indica Proceso inverso
        //String inicio = String("public class Test{\n\tpublic static void main(String[] args){");
        Serial.println(inicio); Serial.println("----------");
      }
      
    }
    else if((valorLeido[i] == 1) && (valorLeido[i] != valorAnterior[i])){
      //Serial.print("Ya no el boton: ");Serial.println(i);Serial.print("----------");
    }
    
    valorAnterior[i] = valorLeido[i];
  }
}

// implementacion funciones
long int myPow( long int num, long int pot){
  long int res = 1;
  for( int i = 0; i < pot; i++){
    res *= num;
  }

  return res;
}
