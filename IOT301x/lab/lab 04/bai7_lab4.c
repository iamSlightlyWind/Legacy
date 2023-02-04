#include <stdio.h>

int main(){
    float a,b,c,d;
    printf("float#1: "); scanf("%f",&a);
    printf("float#2: "); scanf("%f",&b);
    printf("float#3: "); scanf("%f",&c);
    printf("float#4: "); scanf("%f",&d);
    
    printf("(%f + %f + %f + %f) / 4 = %lf",a,b,c,d,(a+b+c+d)/4);
}