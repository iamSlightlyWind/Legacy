#include <stdio.h>

void debug() {}

int countDiv3(int arr[], int size) {
    int count = 0;
    for (int i = 0; i < size; i++) {
        if (arr[i] % 3 == 0) {
            arr[count++] = arr[i];
        }
    }

    return count;
}

int main() {
    int sampleArr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 14, 16, 18, 20, 22, 24};

    printf("%d", countDiv3(sampleArr, sizeof(sampleArr) / sizeof(sampleArr[0])));
}