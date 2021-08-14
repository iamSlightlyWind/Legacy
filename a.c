#include <stdio.h>
#include <string.h>

int main(){
    char a[5] = "abc";
    char b[5];
    strncpy(b,"abcdef",3);
    printf("%s",a);
}