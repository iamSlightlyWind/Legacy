#include <stdio.h>

int isPartOf(int x, int sampleArr[], int size) {
    for (int i = 0; i < size; i++) {
        if (x == sampleArr[i]) {
            return 1;
        }
    }
    return 0;
}

int isSubset(int sampleArr[], int size, int sub[], int subSize) {
    if (size < subSize) return 0;

    for (int i = 0, count = 0; i < subSize; i++) {
        if (isPartOf(sub[i], sampleArr, size)) {
            count++;
        }
        if (count == subSize) return 1;
    }
    return 0;
}

int main() {
    int sampleArr[] = {1, 0, 2, 9, 3, 8, 4, 7, 5, 6};
    int sub[] = {2, 4, 6, 8, 8};
    int n = 55;
    printf("%d", isPartOf(n, sampleArr, sizeof(sampleArr) / sizeof(sampleArr[0])));
    printf("%d", isSubset(sampleArr, sizeof(sampleArr) / sizeof(sampleArr[0]), sub, sizeof(sub) / sizeof(sub[0])));
}
