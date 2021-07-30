#include <stdio.h>

int main(){

///////////// Asuming every year has 365 days

    int days,years,minutesTotal;
    printf("minutes total: "); scanf("%d",&minutesTotal);
    days = minutesTotal/(60*24);
    years = days/365;
    printf("songay: %d\nsonam: %d\n",days,years);
}