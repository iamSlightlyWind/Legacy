#include <stdio.h>

typedef struct{
    int feet;
    int inch;
}sDistance;

void sumDistance(sDistance md1, sDistance md2){
    sDistance dSum = {md1.feet + md2.feet, md1.inch + md2.inch};
    printf("sum of distance: %d inches, %d feet",dSum.feet,dSum.inch);

}

int main(){
    sDistance d1 = {13,15};   
    sDistance d2 = {12,16};
    sumDistance(d1,d2);
}