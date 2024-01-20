package AutomationPractice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojo.PetCategoryPojo;
import pojo.PetStorePojo;

import java.io.File;
import java.io.IOException;

public class SerializationTest {

    //We will be using ObjectMapper for Serialization

    @Test
    public void serializationTest() throws IOException {

        PetStorePojo petStorePojo=new PetStorePojo();//object to access variable and method
        PetCategoryPojo petCategoryPojo=new PetCategoryPojo();
        petStorePojo.setName("Rain");    //I am assigning the new Values for variables with Setter
        petStorePojo.setStatus("Complicated");
        petStorePojo.setId(52);
        petCategoryPojo.setName("MJ");
        petCategoryPojo.setId(100);
        petStorePojo.setCategory(petCategoryPojo);
        ObjectMapper objectMapper=new ObjectMapper(); //To do serialization(Java Code --> JSON OBJECT)

        File file=new File("src/test/resources/pet.json"); //location of file

        objectMapper.writeValue(file,petStorePojo);//I am writing values-->.json file
    }
}
