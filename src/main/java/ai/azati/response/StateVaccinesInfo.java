package ai.azati.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * This class represents response from /vaccines endpoint
 */
@Data
@Getter
@Setter
@ToString
public class StateVaccinesInfo {

    private String stateName;

    private Long administered;
    @SerializedName("people_vaccinated")
    private Long peopleVaccinated;
    @SerializedName("people_partially_vaccinated")
    private Long peoplePartiallyVaccinated;
    private String country;
    private Long population;
    @SerializedName("sq_km_area")
    private Long areaInSquareKilometers;
    @SerializedName("life_expectancy")
    private String lifeExpectancy;
    @SerializedName("elevation_in_meters")
    private Long elevationInMeters;
    private String continent;
    private String abbreviation;
    private String location;
    @SerializedName("iso")
    private Long isoCode;
    @SerializedName("capital_city")
    private String capital;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("long")
    private String longitude;
    private LocalDateTime updated;
}
