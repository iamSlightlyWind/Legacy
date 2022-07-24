#include <stdio.h>
#include <string.h>
#include <ctype.h>

static int libSize = 500;

char *strrep(char *original, char *lib[])
{

    for (int j = 0; j < strlen(original); j++)
    {
        if (original[j] == ' ')
        {
            for (int i = 0; i < 1000; i++)
            {
                if (lib[i] == NULL)
                    break;

                static char buffer[4096];
                char *p;

                char replace[strlen(lib[i]) + 1];
                strcpy(replace, lib[i]);

                char rep[strlen(lib[i]) + 1];
                strcpy(rep, lib[i]);
                rep[0] = toupper(rep[0]);
                for (int x = 1; x < strlen(rep); x++)
                {
                    rep[x] = *"*";
                }

                if (!(p = strstr(original, replace))) // if replace in original
                    return original;

                strncpy(buffer, original, p - original); // Copy characters from original to replace
                buffer[p - original] = '\0';

                sprintf(buffer + (p - original), "%s%s", rep, p + strlen(replace));
                strcpy(original, buffer);
            }
        }
    }
    return original;
}

void censorString(char *lib[])
{
    //nhập string mới
    //example
    char myString[] = "What the hell, shut the fuck up, don't do that shit, you fucking cunt";
    printf("%s\n", strrep(myString, lib));
}

void addToLib(char *lib[])
{
    printf("Nhap tu can them vao tu dien: ");
    char temp[10];
    scanf("%s", temp);

    for (int i = 0; i < libSize; i++)
    {
        if (lib[i] == NULL)
        {
            lib[i] = temp;
            break;
        }
    }
}

void printLib(char *lib[])
{
    for (int i = 0; i < libSize; i++)
    {
        if (lib[i] == NULL)
            break;

        printf("\n%d. %s", i, lib[i]);
    }
}

void printMenu()
{
    printf("\n----------MENU----------\n");
    printf("1. Them tu nong, tu nhay cam, vao tu dien cac tu nhay cam\n");
    printf("2. Hien thi danh sach tu nong, tu nhay cam hien tai\n");
    printf("3. Nhap chuoi va hien thi chuoi sau khi che cac tu nhay cam\n");
    printf("0. Thoat chuong trinh.\n\n");
    printf("Nhap lua chon cua ban: ");
}

int main()
{

    // Initialize library
    char *lib[libSize];
    for (int i = 0; i < libSize; i++)
    {
        lib[i] = NULL;
    }
    ///

    int choice;
    char temp[10];

    for (;;)
    {
        printMenu();
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            addToLib(lib);
            break;
        case 2:
            printLib(lib);
            break;
        case 3:
            censorString(lib);
            break;
        case 0:
            return 0;
        }
    }
}