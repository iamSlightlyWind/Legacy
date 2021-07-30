#include <stdio.h>

int main(){
    int a[101], b;
    int temp;

    printf("sort:\n1. smallest to largest\n2. largest to smallest\nchoice: "); scanf("%d",&b);printf("\n\n");

    for(int i= 0; i < sizeof(a)/sizeof(a[0]);i++){
        a[i] = rand() % 200;
        printf("%d: %d\n",i,a[i]);
    }

    if(!b){
        for(int x = 0; x > sizeof(a)/sizeof(a[0]);x++){
            for (int i = 0; i < sizeof(a)/sizeof(a[0]); i++){
                if(a[i] > a[i+1]){
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    //printf("a[i] = %d; a[i+1] = %d\na[i] = %d; a[i+1] = %d\n\n",a[i+1],a[i],a[i],a[i+1]);
                }   //else printf("a[i] = %d; a[i+1] = %d\na[i] = %d; a[i+1] = %d\n\n",a[i],a[i+1],a[i],a[i+1]);
            }
        }
    }else{
        for(int x = 0; x < sizeof(a)/sizeof(a[0]);x++){
            for (int i = 0; i < sizeof(a)/sizeof(a[0]); i++){
                if(a[i] > a[i+1]){
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    //printf("a[i] = %d; a[i+1] = %d\na[i] = %d; a[i+1] = %d\n\n",a[i+1],a[i],a[i],a[i+1]);
                }   //else printf("a[i] = %d; a[i+1] = %d\na[i] = %d; a[i+1] = %d\n\n",a[i],a[i+1],a[i],a[i+1]);
            }
        }
    }

    for(int i= 0; i < sizeof(a)/sizeof(a[0]);i++){
        printf("\n%d: %d",i,a[i]);
    }
}
