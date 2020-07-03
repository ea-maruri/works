int pinBocina = 13;
int frq = 100;

void setup() {
  // put your setup code here, to run once:
  pinMode(A0, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  //analogWrite(pinBocina, frq);
  tone(pinBocina, frq);
  delay(1500);
  noTone(pinBocina);
  delay(1000);
}
