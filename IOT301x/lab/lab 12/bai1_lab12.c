#include <stdio.h>

struct employee{
    char name[50];
    int id;
    int salary;
};

int main(){
    struct employee newEmployee;
    printf("Employee name: ");  scanf("%s",newEmployee.name);
    printf("Employee ID: ");    scanf("%d",&newEmployee.id);
    printf("Employee salary: ");scanf("%d",&newEmployee.salary);

    printf("\n\nEmployee name: %s\nEmployee ID: %d\nEmployee salary: %d",newEmployee.name,newEmployee.id,newEmployee.salary);
}