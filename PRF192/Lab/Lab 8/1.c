#include <stdio.h>

int main() {
    int myInt = 0;
    int *myInt_ptr = &myInt;

    while (*myInt_ptr >= 0) {
        printf("Enter a non-negative int: ");
        scanf("%d", myInt_ptr);
    }
}