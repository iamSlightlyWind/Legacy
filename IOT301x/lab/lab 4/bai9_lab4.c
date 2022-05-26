#include <stdio.h>

int main(){
    unsigned int employeeID, totalWorkingHours, hourlyPay;
    printf("Employee ID: "); scanf("%d",&employeeID);
    printf("Total working hours: "); scanf("%d",&totalWorkingHours);
    printf("Hourly pay: "); scanf("%d",&hourlyPay);

    printf("\n\n\nEmployee ID: %d\nTOtal working hours: %d\nHourly pay: %d\nMonthly payout: %d",employeeID,totalWorkingHours,hourlyPay,totalWorkingHours*hourlyPay);
}