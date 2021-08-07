#include <stdio.h>
#include <string.h>

int option;
int studentCount = 0;

struct student{
    char name[50];
    char addr[50];
    int age;
    float gpa;
};

struct student myStudents[1000];

void addStudent(){
    printf("\n\nHoc sinh #%d",studentCount);
    printf("\nHo ten hoc sinh: ");      scanf("%s",myStudents[studentCount].name);
    printf("Dia chi hoc sinh: ");       scanf("%s",myStudents[studentCount].addr);
    printf("Tuoi hoc sinh: ");          scanf("%d",&myStudents[studentCount].age);
    printf("Diem GPA hoc sinh: ");      scanf("%f",&myStudents[studentCount++].gpa);
    system("cls");
}

void studentInfo(int id){
    printf("\nHoc sinh #%d",id);
    printf("\nHo ten hoc sinh: %s",myStudents[id].name);
    printf("\nDia chi hoc sinh: %s",myStudents[id].addr);
    printf("\nTuoi hoc sinh: %d",myStudents[id].age);
    printf("\nDiem GPA hoc sinh: %0.2f\n\n\n",myStudents[id].gpa);
}

void studentSearch(){
    char studentName[50];
    int studentID = -1;
    printf("\n\nTen hoc sinh can tim: "); scanf("%s",studentName);
    for(int i = 0; i < studentCount; i++){
        if(studentName[0] == myStudents[i].name[0]){
            for(int x = 0; x < strlen(myStudents[i].name);x++){
                if(studentName[x] != myStudents[i].name[x]){
                    break;
                }
                studentID = i;
            }
        }
    }

    if(studentID > -1){
        printf("\nStudent found:");
        studentInfo(studentID);
    }else printf("\nStudent not found!");
}

void menu(){
    printf("1. Nhap thong tin hoc sinh\n");
    printf("2. Tim kiem hoc sinh\n");
    printf("3. Thoat\n");
    printf("Hay lua chon: ");
    scanf("%d",&option);

    switch(option){
        case 1: addStudent(); break;
        case 2: studentSearch(); break;
        case 3: return 0;
        default: break;
    }

    menu();
}

int main(){
    system("cls");
    menu();
}