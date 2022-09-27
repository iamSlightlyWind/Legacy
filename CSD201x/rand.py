#!/usr/bin/python3
import random
import sys

arraySize = int (sys.argv[1])

numbers = random.sample(range(1, 10000000), arraySize)

with open('PreUnsortedInts', 'w') as f:
    for i in numbers:
        f.write(f"{i}\n")

numbers.sort()

with open('PreSortedInts', 'w') as f:
    for i in numbers:
        f.write(f"{i}\n")

with open('ArraySize', 'w') as f:
    f.write(f"{arraySize}\n")