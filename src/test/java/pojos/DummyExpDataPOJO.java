package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyExpDataPOJO {


    private String status;
    private DummyDataPOJO data;
    private String message;
}
