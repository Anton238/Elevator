import com.house.House;
import com.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

public class InitPassengersTest {
    @Test
    public void initBuildingTest() {
        House houseBuilder = new House();
        Validator validator = new Validator();

        houseBuilder.initHouse();
        // test that passengers created
        Assert.assertTrue(houseBuilder.getPassengers().size() > 0);
        // test that transportation not finished at the begining
        Assert.assertFalse(validator.isValid(houseBuilder));
        // lets that elevator container is empty at the begining
        Assert.assertTrue(houseBuilder.getElevatorContainer().isEmpty());
    }
}
