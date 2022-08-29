package ai.azati.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

/**
 * This class represents response from /history endpoint
 */
@Data
@Getter
@Setter
@ToString
public class StateHistoryInfo {

    private String stateName;

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

    Map<Date, Long> dates;
}
