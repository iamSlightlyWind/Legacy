#include <stdio.h>

struct employee{
    char name[50];
    int id;
    int salary;

    struct sBirthDate {
        int day;
        int month;
    } birthDate;
};

int main(){
    struct employee newEmployee;
    printf("Employee name: ");                  fgets(newEmployee.name,30,stdin);
    printf("Employee ID: ");                    scanf("%d",&newEmployee.id);
    printf("Employee salary: ");                scanf("%d",&newEmployee.salary);
    printf("Employee birth month: ");           scanf("%d",&newEmployee.birthDate.month);
    printf("Employee birth date of month: ");   scanf("%d",&newEmployee.birthDate.day);

    printf("\n\nEmployee name: %s\nEmployee ID: %d\nEmployee salary: %d\nEmployee birth month: %d\nEmployee birth date of month: %d",newEmployee.name,newEmployee.id,newEmployee.salary,newEmployee.birthDate.month,newEmployee.birthDate.day);
}