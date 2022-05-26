#include <stdio.h>
#include <math.h>

int gcd(int a, int b){
    int i;
    for(i = a; i >= 1; i--){ if(a%i == 0 && b%i == 0) break;}
    return i;
}

float  ownAbs(float a){if(a < 0) return -a; else return a;}
//float ownSqrt(float a){return sqrt(a);}

int main(){
    int choice;
    printf("1. greatest common divisor\n2. absolute\n3. square root\nchoice: ");
    scanf("%d",&choice);
    int a,b;float c;

    switch(choice){
        case 1: printf("input 2 int(s):\n");scanf("%d %d",&a,&b);
                if(a < b){b = gcd(a, b);}else b = gcd(b, a);
                printf("greatest common divisor: %d",b);
                break;

        case 2: printf("input a float: "); scanf("%f",&c);
                printf("%f",ownAbs(c));
                break;

        case 3: printf("input a number: "); scanf("%f",&c);
                while(c < 0){
                    printf("negative number is not suitable to get square root, another number: "); scanf("%f",&c);
                }
                printf("%lf",sqrt(c));
    }
    
}