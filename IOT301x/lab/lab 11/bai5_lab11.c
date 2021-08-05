#include <stdio.h>

int main(){
    int a[6] = {5,6,7,8,9,10}, b[3] = {1,2,3};
    // @ts-ignore
    int *aa = &a, *bb = &b;

    int temp[6], *tt = temp;
    for(int i = 0; i < sizeof(a)/sizeof(a[0]);i++){
        if(i < sizeof(a)/sizeof(a[0])){*(tt+i) = *(aa+i);}
        if(i < sizeof(b)/sizeof(b[0])){*(aa+i) = *(bb+i);}
        if(i < sizeof(b)/sizeof(b[0])){*(bb+i) = *(tt+i);}
    }

    for(int i = 0;i < 6; i++){printf("%d\n",a[i]);}
}