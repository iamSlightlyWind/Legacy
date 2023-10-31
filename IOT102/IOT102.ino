#include <LiquidCrystal.h>
#include <SoftwareSerial.h>
#include <Servo.h>

int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

SoftwareSerial bluetooth(0, 1);

int buttonNext = 10, buttonPrev = 9;
int potentiometer = A0;

String hour[] = {"00", "00", "00"}; // 0 for current, 1 for alarm, 2 for time from current to alarm
String minute[] = {"00", "00", "00"};

int current = 0, cycle = 0;
boolean alarmSet = false;

void setup()
{
    pinMode(buttonNext, INPUT);
    digitalWrite(buttonNext, HIGH);
    pinMode(buttonPrev, INPUT);
    digitalWrite(buttonPrev, HIGH);

    lcd.begin(16, 2);
    Serial.begin(9600);
    bluetooth.begin(9600);
}

void loop()
{
    // chat between bluetooth and serial
    if (bluetooth.available())
    {
        char receivedChar = bluetooth.read();
        if (receivedChar != '.')
        {
            Serial.print(receivedChar);
        }
        else
        {
            Serial.println();
        }
    }

    process();
}

void process()
{
    getInput();
    getTime();
    getCycle();
    formatTime();
    print();

    delay(200);
}

void getInput()
{
    if (digitalRead(buttonNext) == LOW)
    {
        if (current < 4)
        {
            current++;
            delay(50);
        }
    }

    if (digitalRead(buttonPrev) == LOW)
    {
        if (current > 0)
        {
            current--;
            delay(50);
        }
    }

    if(current = 4){
        alarmSet = true;
    }
}

int porentiometerMax = 970;
int porentiometerMin = 60;

void getTime()
{
    switch (current)
    {
    case 0:
        hour[0] = String(map(analogRead(potentiometer), porentiometerMin, porentiometerMax, 0, 24));
        break;
    case 1:
        minute[0] = String(map(analogRead(potentiometer), porentiometerMin, porentiometerMax, 0, 60));
        break;
    case 2:
        hour[1] = String(map(analogRead(potentiometer), porentiometerMin, porentiometerMax, 0, 24));
        break;
    case 3:
        minute[1] = String(map(analogRead(potentiometer), porentiometerMin, porentiometerMax, 0, 60));
        break;
    }
}

void getCycle()
{
    int currentTime = hour[0].toInt() * 60 + minute[0].toInt();
    int alarmTime = hour[1].toInt() * 60 + minute[1].toInt();
    int toAlarmTime = 0;

    if (currentTime > alarmTime)
    {
        toAlarmTime = 1440 - currentTime + alarmTime;
    }

    if (currentTime < alarmTime)
    {
        toAlarmTime = alarmTime - currentTime;
    }

    hour[2] = String(toAlarmTime / 60);
    minute[2] = String(toAlarmTime % 60);

    cycle = toAlarmTime / 90;
}

void formatTime()
{
    for (int i = 0; i <= 2; i++)
    {
        if (hour[i].length() < 2)
        {
            hour[i] = "0" + String(hour[i]);
        }
    }

    for (int i = 0; i <= 2; i++)
    {
        if (minute[i].length() < 2)
        {
            minute[i] = "0" + String(minute[i]);
        }
    }
}

bool isBlinkOn = false;
long lastBlink = 0;
long startBlink = 0;
long blinkedFor = 0;
int startLocation[] = {0, 3, 6, 9};

void blink()
{
    if (current < 4)
    {
        int currentTime = millis();
        if (currentTime - lastBlink > 1000)
        {
            isBlinkOn = true;
            blinkedFor = 0;
            startBlink = millis();
            lastBlink = millis() + 5000000; // dummy value so it doesnt get triggered again
        }

        if (isBlinkOn)
        {
            blinkedFor = millis() - startBlink;
            if (blinkedFor < 300)
            {
                lcd.setCursor(startLocation[current], 1);
                lcd.print("  ");
            }
            else
            {
                lastBlink = millis();
                isBlinkOn = false;
            }
        }
    }
}

void print()
{
    lcd.clear();

    lcd.setCursor(0, 0);
    lcd.print(digitalRead(buttonPrev));

    lcd.setCursor(1, 0);
    lcd.print(digitalRead(buttonNext));

    lcd.setCursor(3, 0);
    lcd.print(current);

    lcd.setCursor(5, 0);
    lcd.print(String(analogRead(potentiometer)));

    lcd.setCursor(9, 0);
    lcd.print(String(hour[2]) + ":" + String(minute[2]));

    lcd.setCursor(15, 0);
    lcd.print(String(cycle));

    lcd.setCursor(0, 1);
    lcd.print(String(hour[0]) + ":" + String(minute[0]));

    lcd.setCursor(6, 1);
    lcd.print(String(hour[1]) + ":" + String(minute[1]));

    blink();
}