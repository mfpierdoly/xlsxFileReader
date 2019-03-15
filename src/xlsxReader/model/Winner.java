package xlsxReader.model;

public class Winner {
    String LpNumber;
    String NameSurname;
    String ForAchivement;
    String AtTournament;
    String TournamentPlace;
    String CompetitionYear;

    public Winner(String lpNumber, String nameSurname, String forAchivement, String atTournament, String tournamentPlace, String competitionYear) {
        this.LpNumber = lpNumber;
        this.NameSurname = nameSurname;
        this.ForAchivement = forAchivement;
        this.AtTournament = atTournament;
        this.TournamentPlace = tournamentPlace;
        this.CompetitionYear = competitionYear;
    }

    public String getLpNumber() {
        return LpNumber;
    }

    public void setLpNumber(String lpNumber) {
        LpNumber = lpNumber;
    }

    public String getNameSurname() {
        return NameSurname;
    }

    public void setNameSurname(String nameSurname) {
        NameSurname = nameSurname;
    }

    public String getForAchivement() {
        return ForAchivement;
    }

    public void setForAchivement(String forAchivement) {
        ForAchivement = forAchivement;
    }

    public String getAtTournament() {
        return AtTournament;
    }

    public void setAtTournament(String atTournament) {
        AtTournament = atTournament;
    }

    public String getTournamentPlace() {
        return TournamentPlace;
    }

    public void setTournamentPlace(String tournamentPlace) {
        TournamentPlace = tournamentPlace;
    }

    public String getCompetitionYear() {
        return CompetitionYear;
    }

    public void setCompetitionYear(String competitionYear) {
        CompetitionYear = competitionYear;
    }
}
