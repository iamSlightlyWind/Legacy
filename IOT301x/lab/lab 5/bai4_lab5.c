#include <stdio.h>

#define PAYRATE 12.00
#define TAXRATE_300 0.15 
#define TAXRATE_150 0.2
#define TAXRATE_REST 0.25
#define OVERTIME 40

int main(){
    int hours;
    double grossPay = 0, taxes = 0, netPay = 0;

    printf("hours worked: "); scanf("%d", &hours);

    if (hours <= 40) {grossPay = hours * PAYRATE;}
    else {
        grossPay = 40 * PAYRATE;
        double overtimePay = (hours - 40) * (PAYRATE * 1.5);
        grossPay += overtimePay;
    }

    if (grossPay <= 300) {taxes = grossPay * TAXRATE_300;}
    else if(grossPay > 300 && grossPay <= 450){
        taxes = 300 * TAXRATE_300;
        taxes += (grossPay - 300) * TAXRATE_150;
    }else if(grossPay > 300 && grossPay <= 450){
        taxes = 300 * TAXRATE_300;
        taxes += 150 * TAXRATE_150;
        taxes += (grossPay - 450) * TAXRATE_150;
    }

    netPay = grossPay - taxes;

    printf("Your gross pay this week is %.2f\n",grossPay);
    printf("Your taxes this week is %.2f\n",taxes);
    printf("Your net pay this week is: %.2f\n",netPay);
}