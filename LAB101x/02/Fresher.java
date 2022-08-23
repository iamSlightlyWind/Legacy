public class Fresher extends Candidate {
    public Fresher(boolean isExperienced, int ID) {
        super(isExperienced, ID);
    }
    
    protected String gradYear, gradRank, gradUni;

    public void setFresher(String yearOfGrad, String rankOfGrad, String uni) {
        gradYear = yearOfGrad;
        gradRank = rankOfGrad;
        gradUni = uni;
    }
    
}