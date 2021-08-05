#include <stdio.h>
#include <string.h>

int main(){
    char a[100] = "Well Guess What Bitch?";
    char *aa = a;
    for(int i = 0; i < strlen(a); i++){
        printf("%c\n",*(aa+i));
    }
}