public class Fresher extends Candidate {
    protected int gradYear;
    protected int gradRank;
    protected String gradUni;

    public Fresher(int yearOfGrad, int rankOfGrad, String uni){
        gradYear = yearOfGrad;
        gradRank = rankOfGrad;
        gradUni = uni;
    }
}