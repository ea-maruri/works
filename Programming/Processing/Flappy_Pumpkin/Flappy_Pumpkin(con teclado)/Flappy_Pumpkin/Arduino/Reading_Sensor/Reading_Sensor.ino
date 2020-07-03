const int pinPotenciometro = A0;
int potenciometer;

const int TRIG = 9, ECHO = 8;

//Variables de calculo
float distance;
float my_time;

void setup(){
  pinMode(TRIG, OUTPUT); //Activación del pin como salida: para el pulso ultrasónico
  pinMode(ECHO, INPUT); //Activación del pin como entrada: tiempo del rebote del ultrasonido
  
  Serial.begin(9600);
}

void loop(){
  digitalWrite(TRIG,LOW); 
  delayMicroseconds(20);
  digitalWrite(TRIG, HIGH); //Envío del pulso ultrasónico
  delayMicroseconds(20);

  /* Función para medir la longitud del pulso entrante. */
  my_time = pulseIn(ECHO, HIGH);
  distance = int(0.017 * my_time);
  //Serial.println(distance);
  
  if(distance <= 200){
  //potenciometer = analogRead(pinPotenciometro)/4;
  //int valueOfPotenciometer = map(potenciometer, 0, 255, 0, 700);
    int valueOfDistance = map(distance, 50, 200, 0, 700);

    Serial.println(valueOfDistance);
  //Serial.print(" ");
  //Serial.println(valueOfPotenciometer);
    
    delay(50);
  }
}
