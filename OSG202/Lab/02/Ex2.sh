echo "Enter 3 numbers: "
read a b c
echo "Sort ascending: "
echo "$a $b $c" | tr " " "\n" | sort -n | tr "\n" ", "