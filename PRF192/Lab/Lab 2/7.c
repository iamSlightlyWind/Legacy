#include <stdio.h>
int main(void) {
    int a = 123, b = -123, c = 12345;
    printf("\n1. %2d", c);
    printf("\n2. %10.2d", c);
    printf("\n3. %-10.2d", c);

    printf("\n");

    printf("\n1. %-7d", a);
    printf("\n2. %07.2d", a);
    printf("\n3. %07d", a);
    printf("\n4. %+0-9.4d", a);
    printf("\n5. %+09.4d", a);
    printf("\n6. %+07d", a);
    printf("\n7. %+07.4d", a);
    printf("\n8. %+-07.4d", a);

    printf("\n");

    printf("\n1.%-08d", b);
    printf("\n2.%-08.2d", b);
    printf("\n3.%-8.4d", b);
}