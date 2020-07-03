/*
  DHT11
  https://programarfacil.com/blog/arduino-blog/sensor-dht11-temperatura-humedad-arduino/

  Humedad
  https://www.luisllamas.es/arduino-humedad-suelo-fc-28/
  
  PROYECTO
  http://elcajondeardu.blogspot.com/2016/05/tutorial-huertos-inteligentes-dht11.html

  Relé
  https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiCnfCOgvzlAhVxoFkKHWeeAB8QjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.instructables.com%252Fid%252FM%2525C3%2525A1quina-De-Lluvia-Controlada-Con-Arduino%252F%26psig%3DAOvVaw3EjcCiqBU9Zy7V16ypJKrw%26ust%3D1574450175443660&psig=AOvVaw3EjcCiqBU9Zy7V16ypJKrw&ust=1574450175443660
  http://diwo.bq.com/utilizar-rele-arduino-zum-core/

  Chequear riego una vez al día
  Buscar baterías
*/


#include <DHT.h>
#include <DHT_U.h>

/*For Sensors*/
#define DHTSensorPin 2 // Digital Pin for DHT sensor (temperature and external humedity)
#define DHTType DHT11 // Kind of sensor
#define LightSensorPin A0 
#define InnerHumiditySensorPin A1 // More humidity less value


/*For Rele*/
#define RelePin 3


/*For LEDS*/
#define LEDPinInnerHumidity 8
#define LEDPinTemperature 9
#define LEDPinExternalHumidity 10

#define LEDPinForPrune 12
#define LEDPinForChange 13


/*Maximum values of parameters*/
#define maxValueOfInnerHumidity 750
#define minValueOfInnerHumidity 300
#define maxValueOfTemperature 25
#define maxValueOfExternalHumidity 80


/*For advices*/
bool advicePrune;
bool adviceChange;

long previousMillisForPrune = 0; // will store last time updated
long previousMillisForChange = 0; // will store last time updated

/*Intervals of time*/
long aDay = 86400000; // 8,64e+7 ms.
long intervalAdvicePruneOn = 15000; // aDay
long intervalAdvicePruneOff = 30000; // aDay*30
long intervalAdviceChangeOn = 20000; // aDay*2
long intervalAdviceChangeOff = 40000; // aDay*365
long intervalCheckWaterOn = 5000; // aDay/24
long intervalCheckWaterOff = 10000; // aDay


/*For watering*/
bool dry;
bool checkWater;
long previousMillisForCheckingWater = 0; // will store last time updated
long previousMillisForWater = 0; // will store last time updated
long intervalWaterOn = 250; // 1 seconds ON
long intervalWaterOff = 4500; // 1 seconds OFF 
 

// Instance of DHT class (temperatura and extarnal humidity sensor)
DHT dht(DHTSensorPin, DHTType); 


void setup() {
  Serial.begin(9600); // Beginning Serial monitor

  dht.begin(); // Initializing the sensor

  /*Pin mode for LEDS*/
  pinMode(LEDPinInnerHumidity, OUTPUT);
  pinMode(LEDPinTemperature, OUTPUT);
  pinMode(LEDPinExternalHumidity, OUTPUT);
  pinMode(LEDPinForPrune, OUTPUT);
  pinMode(LEDPinForChange, OUTPUT);
  pinMode(RelePin, OUTPUT);

  advicePrune = false;
  adviceChange = false;
  dry = false;
  checkWater = true;
}

void loop() {
  delay(500);

  readData(); // Reads data and calls to checkStatus and printData
  advice(); // Call to chechForPrune and checkForChange procedures
}


void readData(){
  float innerHumidity = analogRead(InnerHumiditySensorPin); /*?? hasta 1013*/
  float temperature = dht.readTemperature();  // Reading temperature in Celcius
  float externalHumidity = dht.readHumidity(); // Reading relative humidity
   
  checkStatus(innerHumidity, temperature, externalHumidity);
  printData(innerHumidity, temperature, externalHumidity);
}

void checkStatus(float iH, float t, float eH){
  /*Conditions for Watering*/
  unsigned long currentMillisForCheckingWater = millis();

  if (checkWater == false) {
    if(currentMillisForCheckingWater - previousMillisForCheckingWater > intervalCheckWaterOff) {
      Serial.println("We do not check");
      previousMillisForCheckingWater = currentMillisForCheckingWater; 
      checkWater = true;
    }
  } 
  else {
    if(currentMillisForCheckingWater - previousMillisForCheckingWater > intervalCheckWaterOn) {
      Serial.println("We check HUMIDITY!!!!");
      if(iH <= minValueOfInnerHumidity){
        Serial.println("I drown!\t");
        digitalWrite(LEDPinInnerHumidity, LOW);
        checkWater = false;
        return;
      }
      if((iH > minValueOfInnerHumidity) && (iH < maxValueOfInnerHumidity)){
        Serial.println("I'm OK!!\t");
        digitalWrite(LEDPinInnerHumidity, LOW);
      }
      if(iH >= maxValueOfInnerHumidity){
        Serial.println("I need water!\t");
        digitalWrite(LEDPinInnerHumidity, HIGH);
        
        water();
      }
      else digitalWrite(LEDPinInnerHumidity, LOW);

      previousMillisForCheckingWater = currentMillisForCheckingWater;
      checkWater = false;
    }
  }

  /*Comparign data with maximum values*/
  if(t >= maxValueOfTemperature) digitalWrite(LEDPinTemperature, HIGH);
  else digitalWrite(LEDPinTemperature, LOW);

  if(eH >= maxValueOfExternalHumidity) digitalWrite(LEDPinExternalHumidity, HIGH);
  else digitalWrite(LEDPinExternalHumidity, LOW);
}


/*Prints in serial monitor the recollected data*/
void printData(float iH, float t, float eH){
  
  // Proving if there is not an error
  if (isnan(iH) ||isnan(t) || isnan(eH)) {
    Serial.println("Error in some obtained data");
    return;
  }

  Serial.print("Inner Humidity: "); Serial.print(iH);
  Serial.print("\t\tTemperature: ");  Serial.print(t); Serial.print(" *C ");
  Serial.print("\t\tExternal Humidity: ");  Serial.print(eH); Serial.println(" %");
}


void advice(){
  /*Advisors*/
  checkForPrune();
  checkForChange();
}


void checkForPrune(){
  unsigned long currentMillisForPrune = millis();
  
  if (advicePrune == false) {
    if(currentMillisForPrune - previousMillisForPrune > intervalAdvicePruneOff) {
      Serial.println("Prune!!!");
      previousMillisForPrune = currentMillisForPrune; 
      advicePrune = true;
    }
  } 
  else {
    if(currentMillisForPrune - previousMillisForPrune > intervalAdvicePruneOn) {
      Serial.println("Do NOT prune!!!");
      previousMillisForPrune = currentMillisForPrune;
      advicePrune = false;
    }
  }

  digitalWrite(LEDPinForPrune, advicePrune);
}


void checkForChange(){
  unsigned long currentMillisForChange = millis();

  if (adviceChange == false) {
    if(currentMillisForChange - previousMillisForChange > intervalAdviceChangeOff) {
      Serial.println("Change!!!");
      previousMillisForChange = currentMillisForChange;
      adviceChange = true;
    }
  } 
  else {
    if(currentMillisForChange - previousMillisForChange > intervalAdviceChangeOn) {
      Serial.println("Do NOT change!!!");
      previousMillisForChange = currentMillisForChange;
      adviceChange = false;
    }
  }

  digitalWrite(LEDPinForChange, adviceChange); 
}


void water(){
  unsigned long currentMillisForWater = millis();

  if (dry == true) {
      if(currentMillisForWater - previousMillisForWater > intervalWaterOff) {
        Serial.println("Do NOT Watering!!!");
        previousMillisForWater = currentMillisForWater; 
        dry = false;
      }
  } 
  else {
    if(currentMillisForWater - previousMillisForWater > intervalWaterOn) {
      Serial.println("Watering!!!");
      previousMillisForWater = currentMillisForWater;
      dry = true;
    }
  }
  
  digitalWrite(RelePin, dry);
}
  
