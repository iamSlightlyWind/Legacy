#include <LiquidCrystal.h>
#include <Servo.h>

int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

Servo servo;

int buttonNext = 9, buttonPrev = 10;
int potentiometer = A0;

String currentHour = "00", currentMinute = "00";
String alarmHour = "00", alarmMinute = "00";

int current = 0;

void setup()
{
    pinMode(buttonNext, INPUT);
    pinMode(buttonPrev, INPUT);

    servo.attach(8);

    lcd.begin(16, 2);
    Serial.begin(9600);
    delay(100);
}

void loop()
{
    process();
}

void process()
{
    getInput();
    updateTime();
    spinServo();
    print();
}

void spinServo()
{
    servo.write(map(analogRead(potentiometer), 0, 1023, 180, 0));
}

void blink()
{
    int startLocation[] = {0, 3, 6, 9};

    delay(300);
    lcd.setCursor(startLocation[current], 1);
    lcd.print("  ");
    delay(100);
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

    lcd.setCursor(6, 0);
    lcd.print(String(analogRead(potentiometer)));

    lcd.setCursor(0, 1);
    lcd.print(String(currentHour) + ":" + String(currentMinute));

    lcd.setCursor(6, 1);
    lcd.print(String(alarmHour) + ":" + String(alarmMinute));

    blink();
}

void getInput()
{
    if (digitalRead(buttonNext) == HIGH)
    {
        if (current < 3)
        {
            current++;
        }
    }

    if (digitalRead(buttonPrev) == HIGH)
    {
        if (current > 0)
        {
            current--;
        }
    }
}

void updateTime()
{
    if (current == 0)
    {
        currentHour = format(updateValue("hour"));
    }

    if (current == 1)
    {
        currentMinute = format(updateValue("minute"));
    }

    if (current == 2)
    {
        alarmHour = format(updateValue("hour"));
    }

    if (current == 3)
    {
        alarmMinute = format(updateValue("minute"));
    }
}

String format(String value)
{
    if (value.length() < 2)
    {
        return "0" + String(value);
    }

    return String(value);
}

String updateValue(String choice)
{
    double value = analogRead(potentiometer);

    if (choice == "hour")
        return String(map(analogRead(potentiometer), 0, 1023, 0, 24));

    if (choice == "minute")
        return String(map(analogRead(potentiometer), 0, 1023, 0, 60));
}