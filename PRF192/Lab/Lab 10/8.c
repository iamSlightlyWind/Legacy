#include <stdio.h>

void compare(int n, int arr[], int size, int choice) {
    int larger[size], lCount = 0, smaller[size], sCount = 0;

    for (int i = 0; i < size; i++) {
        if (arr[i] <= n) {
            smaller[sCount++] = arr[i];
        } else
            larger[lCount++] = arr[i];
    }

    switch (choice) {
        case 0:
            printf("Less than or equal to %d: ", n);
            for (int i = 0; i < sCount; i++) {
                if (i != 0) {
                    printf(", ");
                }
                printf("%d", smaller[i]);
            }
            printf("\nGreater than %d: ", n);
            for (int i = 0; i < lCount; i++) {
                if (i != 0) {
                    printf(", ");
                }
                printf("%d", larger[i]);
            }
            return;

        case 1:
            int count = 0;
            for (int i = 0; i < sCount; i++) {
                arr[count++] = smaller[i];
            }
            for (int i = 0; i < lCount; i++) {
                arr[count++] = larger[i];
            }
    }
}

void partition(int arr[], int size, int pivot) {
    compare(pivot, arr, size, 1);
}

int main() {
    int arr[] = {11, 99, 22, 88, 33, 77, 44, 66, 55};
    int pivot = 34, size = sizeof(arr) / sizeof(arr[0]);
    compare(pivot, arr, size, 0);
    partition(arr, size, pivot);
    return 0;
}
