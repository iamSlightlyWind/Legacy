#include <stdio.h>

int check[9] = {0,0,0,0,0,0,0,0};
char checkChar[9];
int count = 9;
int a,b,c,d,x,eX = 1;

void printBoard(){
    system("cls");
    //printf("%d | %d | %d\n%d | %d | %d\n%d | %d | %d\n\n\n",check[1],check[2],check[3]
    //                                                       ,check[4],check[5],check[6]
    //                                                       ,check[7],check[8],check[9]);

    printf(" %c | %c | %c \n-----------\n %c | %c | %c\n-----------\n %c | %c | %c\n",checkChar[1],checkChar[2],checkChar[3]
                                                       ,checkChar[4],checkChar[5],checkChar[6]
                                                       ,checkChar[7],checkChar[8],checkChar[9]);
}

void merge(){
    for(int i = 0; i < 10; i++){
        switch(check[i]){
            case 0: checkChar[i] = ' '; break;
            case 1: checkChar[i] = 'X'; break;
            case 2: checkChar[i] = 'O'; break;
        }
    }
}

void botCheck(){
    x = rand() % 10;
    while(check[x] != 0 || x <= 0){
        x = rand() % 10;
    }
    check[x] = 2;
    count--;
    merge();checkWin();
}

void userCheck(){
    printf("tick a box (box number 1-9): ");scanf("%d",&x);
    if (check[x] == 0){
        check[x] = 1;
        count--;
        merge();checkWin();
        botCheck();
    }else {printf("box #%d has already been ticked\n",x); sleep(1);}
    printBoard();
}

void checkWin(){
    if(check[1] != 0){a = (check[1] == check[2] && check[2] == check[3]) || (check[1] == check[4] && check[4] == check[7]);} else a = 0;
    if(check[9] != 0){b = (check[9] == check[6] && check[6] == check[3]) || (check[9] == check[8] && check[8] == check[7]);} else b = 0;
    if(check[5] != 0){c = (check[5] == check[2] && check[5] == check[8]) || (check[5] == check[4] && check[5] == check[6]);} else c = 0;
    if(check[5] != 0){d = (check[5] == check[7] && check[5] == check[3]) || (check[5] == check[1] && check[5] == check[9]);} else d = 0;
    
    if(a == 1){
        switch(check[1]){
            case 1: printf("\n\nPlayer Won");eX = 0;break;
            case 2: printf("\n\nBot Won");eX = 0;break;
        }
    }else if(b == 1){
        switch(check[9]){
            case 1: printf("\n\nPlayer Won");eX = 0;break;
            case 2: printf("\n\nBot Won");eX = 0;break;
        }
    }else if(c == 1){
        switch(check[5]){
            case 1: printf("\n\nPlayer Won");eX = 0;break;
            case 2: printf("\n\nBot Won");eX = 0;break;
        }
    }else if(d == 1){
        switch(check[5]){
            case 1: printf("\n\nPlayer Won");eX = 0;break;
            case 2: printf("\n\nBot Won");eX = 0;break;
        }
    }
}

int main(){
    srand(time(0));
    printBoard();
    
    while(count > 1){ //count > 0 was problematic, unfixable therefore extended code was written, might look excessive
        userCheck();
        if(eX == 0){
            return 0;
        }
    }

    for(;;){
        printBoard();
        printf("tick a box (box number 1-9): ");scanf("%d",&x);
        if (check[x] == 0){
            check[x] = 1;
            merge();
            printBoard();
            break;
        }else {printf("box #%d has already been ticked\n",x); sleep(1);continue;}
    }
    checkWin();
}