#include <stdio.h>

int isAscending(int arr[], int size) {
    for (int i = 0, current; i < size - 1;) {
        current = i;

        if (arr[current] > arr[++i]) {
            return 0;
        }
    }
    return 1;
}

int main() {
    int sampleArr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 14, 16, 18, 20, 22, 24, 26};
    printf("%d", isAscending(sampleArr, sizeof(sampleArr) / sizeof(sampleArr[0])));
}
