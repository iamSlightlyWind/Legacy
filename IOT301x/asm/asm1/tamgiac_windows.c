#include <stdio.h>
#include <math.h>
#include <stdbool.h>
#define PI 3.14159265

bool isTriangle = false;
float area;
float gc[6];//goc canh
float dc[3];//duong cao
float tt[3];//trung tuyen
float center[2];//tam tam giac

void tam_tamgiac(float coord[]){
    center[0] = (coord[0] + coord[2] + coord[4])/3;//trong tam = (a+b+c)/3
    center[1] = (coord[1] + coord[3] + coord[5])/3;//

    printf("\nToa do trong tam: [%0.2f ,%0.2f]",center[0],center[1]);
}

void duongcao_tamgiac(){
    float p = (gc[0] + gc[1] + gc[2])/2;                        //heron's formula
    float preset = 2*sqrt(p*(p-gc[0])*(p-gc[1])*(p-gc[2]));     //
                                                                //
    dc[0] = preset/gc[1];                                       //
    dc[1] = preset/gc[2];                                       //
    dc[2] = preset/gc[0];                                       //

    printf("\nDo dai duong cao tu dinh A: %0.2f",dc[0]);
    printf("\nDo dai duong cao tu dinh B: %0.2f",dc[1]);
    printf("\nDo dai duong cao tu dinh C: %0.2f",dc[2]);
}

void trungtuyen_tamgiac(float coord[]){
    float cmc[2] = {(coord[0]+coord[2])/2,(coord[1]+coord[3])/2};//
    float cma[2] = {(coord[2]+coord[4])/2,(coord[3]+coord[5])/2};//middle of 2 points: (ax+bx)/2, (ay+by)/2
    float cmb[2] = {(coord[0]+coord[4])/2,(coord[1]+coord[5])/2};//

    tt[0] = sqrt(pow((coord[0] - cma[0]),2) + pow((coord[1] - cma[1]),2));//
    tt[1] = sqrt(pow((coord[2] - cmb[0]),2) + pow((coord[3] - cmb[1]),2));//formula: sqrt((bx - ax)^2 + (by-ay)^2)
    tt[2] = sqrt(pow((coord[4] - cmc[0]),2) + pow((coord[5] - cmc[1]),2));//

    printf("\nDo dai trung tuyen tu dinh A: %0.2f",tt[0]);
    printf("\nDo dai trung tuyen tu dinh B: %0.2f",tt[1]);
    printf("\nDo dai trung tuyen tu dinh C: %0.2f",tt[2]);
}

void dientich_tamgiac(){
    printf("%0.2f",area);
}

bool kiemtra_tamgiac(float coords[]){// if area != 0 triangle is valid
    area = (coords[0]*(coords[3]-coords[5]) + coords[2]*(coords[5]-coords[1]) + coords[4]*(coords[1]-coords[3]))/2;                                                                                  
    if (area < 0) area = -area;

    if (area > 0){
        printf("\nToa do 3 diem nhap vao tao thanh mot tam giac.\n");
        return true;
    }else{
        printf("\nToa do 3 diem nhap vao khong tao thanh mot tam giac.\n");sleep(1);system("clear");
        return false;
    }                                                           
}

void goccanh_tamgiac(float coords[]){

    gc[0] = sqrt(pow((coords[2] - coords[0]),2) + pow((coords[3] - coords[1]),2));//
    gc[1] = sqrt(pow((coords[4] - coords[2]),2) + pow((coords[5] - coords[3]),2));//formula: sqrt((bx - ax)^2 + (by-ay)^2)
    gc[2] = sqrt(pow((coords[4] - coords[0]),2) + pow((coords[5] - coords[1]),2));//

    gc[3] = acos((gc[2]*gc[2] + gc[0]*gc[0] - gc[1]*gc[1]) / (2*gc[2]*gc[0]))*180/PI;//
    gc[4] = acos((gc[0]*gc[0] + gc[1]*gc[1] - gc[2]*gc[2]) / (2*gc[0]*gc[1]))*180/PI;//Law of Cosine
    gc[5] = acos((gc[1]*gc[1] + gc[2]*gc[2] - gc[0]*gc[0]) / (2*gc[1]*gc[2]))*180/PI;//

    printf("\n   Chieu dai canh AB: %.2f",gc[0]);
    printf("\n   Chieu dai canh BC: %.2f",gc[1]);
    printf("\n   Chieu dai canh AC: %.2f",gc[2]);
    printf("\n   Goc A: %0.2f",gc[3]);
    printf("\n   Goc B: %0.2f",gc[4]);
    printf("\n   Goc C: %0.2f",gc[5]);
}

void xet_tamgiac(){

    bool myTriangle[5] = {0,0,0,0,0};//isRight, isObtuse, isEquilateral, isIsosceles, isUhhhPointed;
    int anglePair = 0;//0 = none, 1 = a,b; 2 = b,c; 3 = a,c;
    char topVertex, myVertex;

    if(gc[0] == gc[1]){//if triangle is Equilateral or just Isosceles
        if(gc[1] == gc[2]){
            myTriangle[2] = true;
        }else{myTriangle[3] = true; topVertex = 'B';}
    }

    if(gc[1] == gc[2]){//same as above but different pair of angle
        if(gc[2] == gc[0]){
            myTriangle[2] = true;
        }else{myTriangle[3] = true; topVertex = 'C';}
    }else if(gc[0] == gc[2]){myTriangle[3] = true; topVertex = 'A';}

    for(int i = 3; i < 6; i++){// if triangle is Right or Obtuse
        if(gc[i] == 90){
            myTriangle[0] = true;
            switch(i){
                case 3: myVertex = 'A';break;
                case 4: myVertex = 'B';break;
                case 5: myVertex = 'C';break;
            }
            break;
        }else if (gc[i] > 90){
            myTriangle[1] = true;
            switch(i){
                case 3: myVertex = 'A';break;
                case 4: myVertex = 'B';break;
                case 5: myVertex = 'C';break;
            }
            break;
        }
    }

    if(!myTriangle[0] && !myTriangle[1] && !myTriangle[2]){//triangle is pointed if not right, obtuse or equilateral
        myTriangle[4] = true;
    }
    
    if(myTriangle[0]){
        if(myTriangle[3]){
            printf("\n\nTam giac ABC la tam giac vuong can tai %c",topVertex);
        }else printf("\n\nTam giac ABC la tam giac vuong tai %c",myVertex);
    }

    if(myTriangle[1]){
        if(myTriangle[3]){
            printf("\n\nTam giac ABC la tam giac tu va can tai %c",topVertex);
        }else printf("\n\nTam giac ABC la tam giac tu tai %c",myVertex);
    }

    if(myTriangle[4]){
        if(myTriangle[3]){
            printf("\n\nTam giac ABC la tam giac nhon va can tai %c",topVertex);
        }else printf("\n\nTam giac ABC la tam giac nhon");
    }
}

void giaima_tamgiac(){
    float coord[6];
    
    while(!isTriangle){
        printf("\nNhap toa do diem A:\n");
        printf("Ax: ");scanf("%f",&coord[0]);
        printf("Ay: ");scanf("%f",&coord[1]);

        printf("\nNhap toa do diem B:\n");
        printf("Bx: ");scanf("%f",&coord[2]);
        printf("By: ");scanf("%f",&coord[3]);

        printf("\nNhap toa do diem C:\n");
        printf("Cx: ");scanf("%f",&coord[4]);
        printf("Cy: ");scanf("%f",&coord[5]);

        printf("\nToa do diem A da nhap: A(%.2f, %.2f)\n",coord[0],coord[1]);
        printf("Toa do diem B da nhap: B(%.2f, %.2f)\n",coord[2],coord[3]);
        printf("Toa do diem C da nhap: C(%.2f, %.2f)\n",coord[4],coord[5]);

        printf("\n---------------------------------------\n");
        
        isTriangle = kiemtra_tamgiac(coord);//loop condition change
    }
    printf("\n1. So do co ban cua tam giac:");goccanh_tamgiac(coord);
    xet_tamgiac();
    printf("\n\n2. Dien tich tam giac ABC la: ");dientich_tamgiac();
    printf("\n\n3. So do nang cao tam giac ABC: ");duongcao_tamgiac();trungtuyen_tamgiac(coord);
    printf("\n\n4. Toa do diem dac biet trong tam giac ABC:");tam_tamgiac(coord);
}

int main(){
    system("cls");
    giaima_tamgiac();
}