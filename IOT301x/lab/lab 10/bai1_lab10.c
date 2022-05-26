#include <stdio.h>

int main(){
    int a = 10; int *aa = &a;
    float b = 11; float *bb = &b;
    char c = 'g'; char *cc = &c;

    printf("%p = %d\n",aa,*aa);
    printf("%p = %f\n",bb,*bb);
    printf("%p = %c\n",cc,*cc);
}