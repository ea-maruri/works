/*Para leer archivo de texto en SD*/
#include <SD.h> 

File myFile; //Objeto tipo File
String archivo = "archivo.txt"; //El nombre del archivo que manejaremos

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.print("Iniciando SD ... ");
  if(!SD.begin(4)){
    Serial.println("No se pudo inicializar SD");
    return;  
  }
  Serial.println("Inicializacion exitosa");

  myFile = SD.open(archivo);
  if(myFile){
    File secondFile = SD.open("secondFile.txt");
    Serial.print(archivo + ":");
    while(myFile.available()){
      secondFile.write(myFile.read());
      //Serial.write(myFile.read());
    }
    myFile.close();
  }
  else{
    Serial.print("Error al abrir el archivo: " + archivo);
  }
}

void loop() {
  // put your main code here, to run repeatedly:

}
