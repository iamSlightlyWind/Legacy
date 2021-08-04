#include <stdio.h>

int main(){
    int x,y, *a = &x, *b = &y;
    scanf("%d %d",a,b);

    int t = *a;
    *a = *b;
    *b = t;

    printf("a = %d\nb = %d",*a,*b);
}