import com.house.containers.ElevatorContainer;
import com.house.containers.ElevatorController;
import org.junit.Assert;
import org.junit.Test;

public class MovingLiftTest {

    @Test
    public void moveLiftTest(){
        ElevatorContainer container = new ElevatorContainer(5);
        container.setCurrentStore(1);
        ElevatorController controller = new ElevatorController();
        controller.moveToNextStore(container, 3);
        Assert.assertTrue(container.getCurrentStore() == 2);
        controller.moveToNextStore(container, 3);
        Assert.assertTrue(container.getCurrentStore() == 3);
        controller.moveToNextStore(container, 3);
        Assert.assertTrue(container.getCurrentStore() == 2);
        controller.moveToNextStore(container, 3);
        Assert.assertTrue(container.getCurrentStore() == 1);
    }
}
