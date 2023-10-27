#include <LiquidCrystal.h>
int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

int servo = 8;
int buttonNext = 9, buttonPrev = 10;

void setup()
{
    pinMode(servo, OUTPUT);
    pinMode(buttonNext, INPUT);
    pinMode(buttonPrev, INPUT);

    lcd.begin(16, 2);
    Serial.begin(9600);
}

void loop()
{
    lcd.setCursor(0, 0);
    lcd.print(digitalRead(buttonNext));
    lcd.setCursor(0, 1);
    lcd.print(digitalRead(buttonPrev));
    delay(100);
}