.include "beta.uasm"
BR(Start)

A:
	LONG(9)
	LONG(3)
	LONG(8)
	LONG(2)
	LONG(4)
	LONG(6)
	LONG(7)
	LONG(5)

performCheckTwo:
	CMPLE(i,i+1,r0)
	BEQ(r0,swap,r31)
	HALT()
	
swap:
	ADD(r31,i,r0)
	ADD(r31,i+1,i)
	ADD(r31,r0,i+1)
	i=i+1
	BR(performCheckTwo)

Start:
	LD(A,r1)
	LD(A+4,r2)
	LD(A+8,r3)
	LD(A+12,r4)
	LD(A+16,r5)
	LD(A+20,r6)
	LD(A+24,r7)
	LD(A+28,r8)
	i=1
	BR(performCheckTwo)
	
HALT()