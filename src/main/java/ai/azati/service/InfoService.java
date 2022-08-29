package ai.azati.service;

import ai.azati.client.CovidInfoClient;
import ai.azati.enums.Status;
import ai.azati.exception.RequestException;
import ai.azati.response.StateCasesInfo;
import ai.azati.response.StateHistoryInfo;
import ai.azati.response.StateVaccinesInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * This class is main, it collects responses and computes whole info about country.
 */
@Slf4j
public class InfoService {

    private final CovidInfoClient covidInfoClient = new CovidInfoClient();

    private static final String PRINT_FORMAT =
            "Confirmed: %d%n" +
                    "Recovered: %d%n" +
                    "Deaths: %d%n" +
                    "Vaccination level: %.2f%n" +
                    "New confirmed cases: %d";

    @Builder
    @Getter
    public static class CasesInformation {
        private Long confirmed;
        private Long recovered;
        private Long deaths;
        private Double vaccinatedPercent;
        private Long newConfirmedCases;
    }

    /**
     * This method finds the latest number of confirmed responses from history response
     * @param history -- response from /history endpoint
     * @return  the latest number of confirmed responses
     */
    private static Long findLatestConfirmedCases(StateHistoryInfo history) {
        Date latestDate = null;
        Long cases = null;
        for (Map.Entry<Date, Long> entry : history.getDates().entrySet()) {
            if (latestDate == null) {
                latestDate = entry.getKey();
                cases = entry.getValue();
                continue;
            }
            if (latestDate.before(entry.getKey())) {
                latestDate = entry.getKey();
                cases = entry.getValue();
            }
        }
        return cases;
    }

    public static CasesInformation computeCasesInformation(StateCasesInfo casesInCountry,
                                                       StateVaccinesInfo vaccinesInCountry,
                                                       StateHistoryInfo stateHistoryInfo) {

        double vaccinatedPercent = (double) vaccinesInCountry.getPeopleVaccinated() / vaccinesInCountry.getPopulation() * 100;
        long latestConfirmedCases = findLatestConfirmedCases(stateHistoryInfo);
        long newConfirmedCases = casesInCountry.getConfirmed() - latestConfirmedCases;

        return CasesInformation.builder()
                .confirmed(casesInCountry.getConfirmed())
                .recovered(casesInCountry.getRecovered())
                .deaths(casesInCountry.getDeaths())
                .vaccinatedPercent(vaccinatedPercent)
                .newConfirmedCases(newConfirmedCases)
                .build();
    }

    /**
     * This method does requests to API and constructs information about
     * specified country.
     * @param country -- country to get info about
     * @return information about country
     * @throws RequestException if error occurred
     */
    private CasesInformation getInfoAboutCountry(String country) throws RequestException {
        StateCasesInfo casesInCountry = covidInfoClient.getCases(country).stream()
                .filter(x -> x.getStateName().equals("All"))
                .findFirst()
                .get();

        StateVaccinesInfo vaccinesInCountry = covidInfoClient.getVaccinesInfo(country).stream()
                .filter(x -> x.getStateName().equals("All"))
                .findFirst()
                .get();

        StateHistoryInfo stateHistoryInfo = covidInfoClient.getHistory(Status.CONFIRMED, country).stream()
                .filter(x -> x.getStateName().equals("All"))
                .findFirst()
                .get();

        return computeCasesInformation(casesInCountry, vaccinesInCountry, stateHistoryInfo);
    }

    /**
     * This method returns information about country as a string or error message.
     *
     * @param country -- country to get information about.
     * @return information about country as a string or error message.
     */
    public String stringInfoAboutCountry(String country) {
        CasesInformation information;
        try {
            information = getInfoAboutCountry(country);
        } catch (RequestException e) {
            log.error("While requesting data from API: ", e);
            return "Failed to request information from API, please try again later.";
        }

        return String.format(PRINT_FORMAT,
                information.confirmed,
                information.recovered,
                information.deaths,
                information.vaccinatedPercent,
                information.newConfirmedCases);
    }
}
