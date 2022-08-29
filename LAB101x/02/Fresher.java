public class Fresher extends Candidate {

    protected String gradYear, gradRank, gradUni;

    public void setFresher(String candYearOfGrad, String candYankOfGrad, String candUni) {
        gradYear = candYearOfGrad;
        gradRank = candYankOfGrad;
        gradUni = candUni;
        setType("1");
    }

}