if [ $# -eq 2 ]
then
    echo "Result 1: $1*$2 = $(($1*$2))"
    echo "Result 2: $1+$2 = $(($1+$2))"
else
    echo "You must input 2 numbers to do this operator!"
fi