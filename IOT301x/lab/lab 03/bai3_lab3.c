#include <stdio.h>

int main(){
    enum Company { GOOGLE, FACEBOOK, XEROX, YAHOO, EBAY, MICROSOFT };
    enum Company google = GOOGLE;
    printf("google's value: %d\n", google);
    google=FACEBOOK;
    printf("google's value: %d\n", google);
}