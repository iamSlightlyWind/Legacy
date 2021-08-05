#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define LEN(arr) ((int) (sizeof (arr) / sizeof (arr)[0]))
#define FILENAME "log.txt"
#define MAXLENGTH 5000

char myString[1000][MAXLENGTH];

int main(){
    FILE *fp = NULL;
    fp = fopen(FILENAME,"r");
    
    for(int i = 0;;i++){
    fgets(myString[i],MAXLENGTH,fp);
    if (myString[i][0] == NULL)break;
    }

    printf("%s\n",myString[0]);
    printf("%s\n",myString[1]);
    printf("%s\n",myString[2]);
    printf("%s\n",myString[3]);
    printf("%s\n",myString[4]);
}