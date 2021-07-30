.include "beta.uasm"
BR(Load)

A:
	LONG(0)
	LONG(0)
	LONG(0)
	LONG(0)
	LONG(0)
	LONG(3)
	LONG(4)

Load:
	LD(A+20,r1)
	LD(A+24,r2)
	
	ADD(r31,r1,r0)
	ADD(r31,r2,r1)
	ADD(r31,r0,r2)
	
	ST(r1,A+20,r31)
	ST(r2,A+24,r31)

HALT()