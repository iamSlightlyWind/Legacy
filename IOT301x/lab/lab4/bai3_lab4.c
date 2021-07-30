#include <stdio.h>

int main(){

///////////// Asuming every year has 365 days

    int days,weeks,years,daysTotal;
    printf("days total: "); scanf("%d",&daysTotal);
    years = daysTotal / 365;
    weeks = (daysTotal - years*365)/7;
    days = daysTotal - years*365 - weeks*7;
    printf("sonam: %d\nsotuan: %d\nsongay: %d",years,weeks,days);
}