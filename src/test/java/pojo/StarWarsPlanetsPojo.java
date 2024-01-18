package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsPlanetsPojo {
    private String name;
    private String climate;
    private String terrain;
}
