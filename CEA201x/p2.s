.include "beta.uasm"
BR(Start)

A:
	LONG(3)
	LONG(1)
	LONG(2)
	LONG(3)
	LONG(4)
	LONG(5)
	LONG(6)
	LONG(7)
	LONG(8)
	LONG(9)
	LONG(10)
	LONG(11)
	
Start:
	i=1 		//define i
	i=i*4
	LD(A+i,r1)
	LD(A+i+4,r2)
	
	ADD(r31,r1,r0)
	ADD(r31,r2,r1)
	ADD(r31,r0,r2)
	
	ST(r1,A+i,r31)
	ST(r2,A+i+4,r31)

HALT()