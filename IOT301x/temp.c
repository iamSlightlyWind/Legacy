#include <stdio.h>
#include <string.h>

int main(){
    char a[] = "[INFO][2019-10-2323:21:45.638]";
    char b[] = "[INFO][2019-10-2323:22:11.936]";
    char c[] = "[INFO][2019-10-2323:22:29.232]";
    char d[] = "[INFO][2019-10-2323:22:29.256]";
    char e[] = "[INFO][2019-10-2323:22:33.192]";
    char f[] = "[INFO][2019-10-2323:22:48.075]";
    char g[] = "[INFO][2019-10-2323:22:48.098]";

    char temp[1000];
memset(temp, 0, 255);
    printf("%s\n",  strncat(temp, a + 20,2));memset(temp, 0, 255);
    printf("%s\n",  strncat(temp, a + 23,2));memset(temp, 0, 255);
    printf("%s\n\n",strncat(temp, a + 26,3));memset(temp, 0, 255);

    printf("%s\n",  strncpy(temp, b + 20,2));memset(temp, 0, 255);
    printf("%s\n",  strncpy(temp, b + 23,2));memset(temp, 0, 255);
    printf("%s\n\n",strncpy(temp, b + 26,3));memset(temp, 0, 255);
}