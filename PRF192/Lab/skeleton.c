#include <math.h>
#include <stdio.h>
#include <stdlib.h>

// A function to count the number of binary digits of a positive integer
int count_binary_digits(int n) {
    int count = 0;
    while (n > 0) {
        count++;
        n = n / 2;
    }
    return count;
}

// A function to convert a positive integer to binary and store it in an array
void convert_to_binary(int n, int *array) {
    int i = 0;
    while (n > 0) {
        array[i] = n % 2;  // store the remainder as the binary digit
        i++;
        n = n / 2;  // divide by two to get the next digit
    }
}

// A function to print an array in reverse order
void print_array_reverse(int *array, int size) {
    for (int i = size - 1; i >= 0; i--) {
        printf("%d", array[i]);
    }
}

// A function to swap two integers
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// A function to sort an array of positions based on the values in another array
void sort_positions(int *values, int *positions, int size) {
    // use bubble sort algorithm for simplicity
    for (int i = 0; i < size - 1; i++) {
        for (int j = i + 1; j < size; j++) {
            // compare the values at the positions and swap them if needed
            if (values[positions[i]] > values[positions[j]]) {
                swap(&positions[i], &positions[j]);
            }
        }
    }
}

int main() {
    int n;  // the input number
    printf("Enter a positive integer: ");

    // read the input and check if it is valid
    if (scanf("%d", &n) != 1 || n <= 0) {
        printf("Invalid input.\n");
        return -1;
    }

    // get the number of binary digits and allocate an array of that size
    int size = count_binary_digits(n);
    int *array = malloc(size * sizeof(int));

    // convert the input to binary and store it in the array
    convert_to_binary(n, array);

    // print the result in binary format
    printf("%d in binary is: ", n);
    print_array_reverse(array, size);
    printf("\n");
    // allocate another array of positions and initialize it with 0, 1, 2, ...
    int *positions = malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        positions[i] = i;
    }

    // sort the positions based on the values in the array
    sort_positions(array, positions, size);

    // print the values in ascending order but keep their positions in the array
    printf("The values in ascending order are: ");
    for (int i = 0; i < size; i++) {
        printf("%d at position %d ", array[positions[i]], positions[i]);
    }
    printf("\n");

    // free the memory allocated for the arrays
    free(array);
    free(positions);

    return 0;
}