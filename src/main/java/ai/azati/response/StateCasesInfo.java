package ai.azati.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * This class represents the response from /cases endpoint
 */
@Data
@Getter
@Setter
@ToString
public class StateCasesInfo {

    private String stateName;

    private Long confirmed;
    private Long recovered;
    private Long deaths;
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
