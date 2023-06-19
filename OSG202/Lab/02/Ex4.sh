touch test.c test.cpp test.java demo.c lab.cpp
ls

while true
do
    echo "What do you want to know? (file type): "
    read type
    case $type in
        c)
            echo "Files: $(ls | grep .c\$)"
            ;;
        cpp)
            echo "Files: $(ls | grep .cpp\$)"
            ;;
        java)
            echo "Files: $(ls | grep .java\$)"
            ;;
        *)
            echo "Bye bye."
            break
            ;;
    esac
done