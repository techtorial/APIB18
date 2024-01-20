package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetStorePojo {

    private int id;
    private String name;
    private List<String> photoUrls;
    private String status;
    private PetCategoryPojo category;
    private List<PetStoreTagsPojo> tags;
}
