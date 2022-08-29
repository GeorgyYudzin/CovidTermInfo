package ai.azati.service;

import ai.azati.response.StateCasesInfo;
import ai.azati.response.StateHistoryInfo;
import ai.azati.response.StateVaccinesInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InfoServiceTest {


    private StateCasesInfo getCasesInfo() {

        StateCasesInfo casesInfo = new StateCasesInfo();

        casesInfo.setStateName("All");
        casesInfo.setConfirmed(123456L);
        casesInfo.setRecovered(123456L);
        casesInfo.setDeaths(123456L);
        casesInfo.setCountry("France");
        casesInfo.setPopulation(123456L);
        casesInfo.setAreaInSquareKilometers(123456L);
        casesInfo.setLifeExpectancy("test");
        casesInfo.setContinent("Europe");
        casesInfo.setAbbreviation("FR");
        casesInfo.setLocation("test");
        casesInfo.setIsoCode(123L);
        casesInfo.setCapital("Paris");
        casesInfo.setLatitude("test");
        casesInfo.setLongitude("test");
        casesInfo.setUpdated(LocalDateTime.now());

        return casesInfo;
    }

    private StateHistoryInfo getHistoryInfo() {
        StateHistoryInfo stateHistoryInfo = new StateHistoryInfo();

        stateHistoryInfo.setStateName("All");
        stateHistoryInfo.setCountry("France");
        stateHistoryInfo.setPopulation(123456L);
        stateHistoryInfo.setAreaInSquareKilometers(123456L);
        stateHistoryInfo.setLifeExpectancy("test");
        stateHistoryInfo.setContinent("Europe");
        stateHistoryInfo.setAbbreviation("FR");
        stateHistoryInfo.setLocation("test");
        stateHistoryInfo.setIsoCode(123L);
        stateHistoryInfo.setCapital("Paris");

        HashMap<Date, Long> dates = new HashMap<>();
        dates.put(new Date(), 123455L);
        stateHistoryInfo.setDates(dates);

        return stateHistoryInfo;
    }

    private StateVaccinesInfo getVaccinesInfo() {
        StateVaccinesInfo stateVaccinesInfo = new StateVaccinesInfo();

        stateVaccinesInfo.setAdministered(100000L);
        stateVaccinesInfo.setPeopleVaccinated(100000L);
        stateVaccinesInfo.setPeoplePartiallyVaccinated(100000L);

        stateVaccinesInfo.setStateName("All");
        stateVaccinesInfo.setCountry("France");
        stateVaccinesInfo.setPopulation(123456L);
        stateVaccinesInfo.setAreaInSquareKilometers(123456L);
        stateVaccinesInfo.setLifeExpectancy("test");
        stateVaccinesInfo.setContinent("Europe");
        stateVaccinesInfo.setAbbreviation("FR");
        stateVaccinesInfo.setLocation("test");
        stateVaccinesInfo.setIsoCode(123L);
        stateVaccinesInfo.setCapital("Paris");
        stateVaccinesInfo.setLatitude("test");
        stateVaccinesInfo.setLongitude("test");
        stateVaccinesInfo.setUpdated(LocalDateTime.now());

        return stateVaccinesInfo;
    }

    @Test
    void computeCasesInformation() {
        StateCasesInfo stateCasesInfo = getCasesInfo();
        StateHistoryInfo stateHistoryInfo = getHistoryInfo();
        StateVaccinesInfo stateVaccinesInfo = getVaccinesInfo();

        InfoService.CasesInformation result = InfoService.computeCasesInformation(stateCasesInfo, stateVaccinesInfo, stateHistoryInfo);

        assertEquals(result.getConfirmed(), 123456L);
        assertEquals(result.getDeaths(), 123456L);
        assertEquals(result.getRecovered(), 123456L);
        assertEquals(result.getNewConfirmedCases(), 1);
        assertEquals(result.getVaccinatedPercent(), (double) 100000L / 123456L * 100);
    }
}