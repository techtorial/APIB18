package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String additionalneeds;
    private BookDatesPojo bookingdates;//data will come from bookdatesPojo

}
