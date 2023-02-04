#include <stdio.h>

int main(){
    const int myNumber = 4;
    int yourNumber;
    printf("enter your single digit number of choice: "); scanf("%d",&yourNumber);

    while(yourNumber != myNumber){
        printf("\nyour number is not matched to mine");
        printf("\nenter your single digit number of choice: "); scanf("%d",&yourNumber);
    }

    printf("\n\nYour number is matched to mine");
}