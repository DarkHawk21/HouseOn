int led1 = 2;
int led2 = 3;
int led3 = 4;
int vent = 7;
int puerta1 = 6;
int estado = 0;

void setup()
{
  Serial.begin(9600);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode (led3, OUTPUT);
  pinMode (vent, OUTPUT);
  pinMode (puerta1,OUTPUT);
}

void loop()
{
  if( Serial.available()>0)
  {
    estado = Serial.read();
  }
  switch( estado)
  {
    case 'a':
    digitalWrite(led1, HIGH);
    break;
    case 'b':
    digitalWrite(led1, LOW);
    break;
    
    case 'c':
    digitalWrite(led2, HIGH);
    break;
    case 'd':
    digitalWrite(led2, LOW);
    break;
    
    case 'e':
    digitalWrite(led3, HIGH);
    break;
    case 'f':
    digitalWrite(led3, LOW);
    break;
    
    case 'g':
    digitalWrite(vent, HIGH);
    break;
    case 'h':
    digitalWrite(vent, LOW);
    break;
    
    case 'i':
    digitalWrite(puerta1, HIGH);
    break;
    case 'j':
    digitalWrite(puerta1, LOW);
    break;
  }
}
