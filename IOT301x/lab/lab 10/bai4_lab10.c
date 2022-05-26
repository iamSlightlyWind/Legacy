#include <stdio.h>

int main(){
    int x,y, *a = &x, *b = &y;
    scanf("%d %d",a,b);

    printf("%d + %d = %d",*a,*b,*a + *b);
}