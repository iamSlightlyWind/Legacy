.include "beta.uasm"
BR(Load)

a:
		LONG(7)
		LONG(2)
		LONG(5)
		
/////////////////////////////////////
swapFS:		
		ADD(r31,r1,r0)
		ADD(r31,r2,r1)
		ADD(r31,r0,r2)
		BR(performCheckAll)
/////////////////////////////////////
swapST:		
		ADD(r31,r2,r0)
		ADD(r31,r3,r2)
		ADD(r31,r0,r3)
		BR(performCheckAll)
/////////////////////////////////////
performCheckAll:
		CMPLE(r1,r2,r0)
		BEQ(r0,swapFS,r31)
		CMPLE(r2,r3,r0)
		BEQ(r0,swapST,r31)
		HALT()

Load:
		LD(a,r1)
		LD(a+4,r2)
		LD(a+8,r3)
		BR(performCheckAll)

HALT()