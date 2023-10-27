#include <LiquidCrystal.h>
int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

int servo = 8;
int buttonNext = 9, buttonPrev = 10;
int potentiometer = A0;

String hour = "0", minute = "0";

int current = 0;

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
    process();

    //delay(100);
}

void process()
{
    getInput();
    updateTime();
    print();
}

int blinkDuration = 100;
int printDuration = 300;

void blink(){
    if(current == 0){
        delay(printDuration);
        lcd.setCursor(0, 1);
        lcd.print("  ");
        delay(blinkDuration);
    }

    if(current == 1){
        delay(printDuration);
        lcd.setCursor(3, 0);
        lcd.print("  ");
        delay(blinkDuration);
    }
}

void print()
{
    lcd.clear();

    lcd.setCursor(0, 0);
    lcd.print(digitalRead(buttonNext));

    lcd.setCursor(1, 0);
    lcd.print(digitalRead(buttonPrev));

    lcd.setCursor(3, 0);
    lcd.print(current);

    lcd.setCursor(0, 1);
    lcd.print(String(hour) + ":" + String(minute));

    blink();
}

void getInput()
{
    if (digitalRead(buttonNext) == HIGH)
    {
        if (current == 0)
        {
            current++;
        }
    }
    if (digitalRead(buttonPrev) == HIGH)
    {
        if (current == 1)
        {
            current--;
        }
    }
}

void updateTime()
{
    hour = format(updateValue("hour"));
    minute = format(updateValue("minute"));
}

String format(String value)
{
    if (value.length() < 2)
    {
        return "0" + String(value);
    }
    else
        return String(value);
}

String updateValue(String choice)
{
    double value = analogRead(potentiometer);

    if (choice == "hour")
    {
        if (current == 0)
        {
            return String(int(value / 1024 * 24));
        }
        else
            return hour;
    }

    if (choice == "minute")
    {
        if (current == 1)
        {
            return String(int(value / 1024 * 60));
        }
        else
            return minute;
    }
}