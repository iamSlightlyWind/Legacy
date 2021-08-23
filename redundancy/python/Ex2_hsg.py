import math

# input
so_bo = int(input("so bo: "))
data = []
for i in range (so_bo) :
    b = input(": ").split(",")
    data.append(b)

# check so cap co the lam canh tam giac
do_dai = []
for i in data:
    do_dai.append(i[0])
do_dai.sort()

data2 = []
if len (do_dai) > 2:
    for i in do_dai:
        for j in range(1,len(do_dai)):
            data3 = []
            canh_1 = int(do_dai[0])
            canh_2 = int(do_dai[j])
            stuff = math.sqrt(canh_1**2 + canh_2**2)
            for a in do_dai:
                a = int(a)
                if a > canh_2 and a < stuff:
                    data3.append(canh_1)
                    data3.append(canh_2)
                    data3.append(a)
                    data2.append(data3)
        do_dai.remove(i)

# tinh dap an
data4 = 1
key = []
for i in data2:
    for j in i:
        for a in data:
            if int(j) == int(a[0]):
                data4 *= int(a[1])
                key.append(data4)
                data4 = 1
for i in key:
    data4 *= i
print(data4)
