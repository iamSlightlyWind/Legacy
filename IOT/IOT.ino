int led10 = 10;

void setup()
{
  // set pin 12 to led
  pinMode(led10, OUTPUT);
}

void loop()
{
  //loop blink every 100ms
  digitalWrite(led10, HIGH);
  delay(100);
  digitalWrite(led10, LOW);
  delay(100);
}