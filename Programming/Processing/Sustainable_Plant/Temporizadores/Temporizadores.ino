const int LEDPinForPrune = 13; // the number of the LED pin
const int LEDPinForFertilizer = 12;

bool advicePrune = false; //
bool adviceFertilizer = false;

long previousMillisForPrune = 0; // will store last time updated
long previousMillisForFertilizer = 0; // will store last time updated

long intervalAdvicePruneOn = 10000;           // medio segundo  ON
long intervalAdvicePruneOff = 4000;         // cinco segundos OFF
long intervalAdviceFertilizerOn = 2000;
long intervalAdviceFertilizerOff = 2000;

void setup() {
  Serial.begin(9600);
  
  pinMode(LEDPinForPrune, OUTPUT);
  pinMode(LEDPinForFertilizer, OUTPUT);
}


void loop(){
  checkForPrune();
  checkForFertilizer();
}


void checkForPrune(){
  unsigned long currentMillisForPrune = millis();
  
  if (advicePrune == false) {
    if(currentMillisForPrune - previousMillisForPrune > intervalAdvicePruneOff) {
      Serial.println("Podar!!!");
      previousMillisForPrune = currentMillisForPrune; 
      advicePrune = true;
    }
  } 
  else {
    if(currentMillisForPrune - previousMillisForPrune > intervalAdvicePruneOn) {
      Serial.println("Ya NO podar!!!");
      previousMillisForPrune = currentMillisForPrune;
      advicePrune = false;
    }
  }

  digitalWrite(LEDPinForPrune, advicePrune);
}


void checkForFertilizer(){
  unsigned long currentMillisForFertilizer = millis();

  if (adviceFertilizer == false) {
    if(currentMillisForFertilizer - previousMillisForFertilizer > intervalAdviceFertilizerOff) {
      Serial.println("Abono!!!");
      previousMillisForFertilizer = currentMillisForFertilizer;
      adviceFertilizer = true;
    }
  } 
  else {
    if(currentMillisForFertilizer - previousMillisForFertilizer > intervalAdviceFertilizerOn) {
      Serial.println("Ya NO abono!!!");
      previousMillisForFertilizer = currentMillisForFertilizer;   
      adviceFertilizer = false;
    }
  }

  digitalWrite(LEDPinForFertilizer, adviceFertilizer); 
}
