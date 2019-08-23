package serialization;

import com.google.gson.reflect.TypeToken;
import entity.Bus;
import entity.Passenger;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Class CustomSerializeTest
 *
 * @author create by ivanslusar
 * 8/22/19
 * @project CustomSerialization
 */
public class CustomSerializeTest {

    @Test
    public void whenGivenNull_inSerializing_thenHaveThrow() {
        try {
            CustomSerialize.serialization(null);
        } catch (Exception e) {
            Assert.fail("Cannot deserialize current stream to object");
        }

    }

    @Test
    public void whenGivenPlainObject_afterSerializing_thenCorrect() {
        String jsonString = CustomSerialize.serialization(150);
        String expectedResult = "150";
        assertEquals(expectedResult, jsonString);
    }

    @Test
    public void whenGivenArrayObjects_afterSerializing_thenCorrect() {
        Passenger[] passengers = {
                new Passenger("Jack", "Corn", 88),
                new Passenger("Dude", "Foolish", 20)
        };
        String jsonString = CustomSerialize.serialization(passengers);
        String expectedResult = "[{\"name\":\"Jack\",\"soName\":\"Corn\",\"age\":88},{\"name\":\"Dude\",\"soName\":\"Foolish\",\"age\":20}]";
        assertEquals(expectedResult, jsonString);
    }

    @Test
    public void whenGivenCollection_afterSerializing_thenCorrect() {
        List<Passenger> passengers = Arrays.asList(
                new Passenger("Jack", "Corn", 88),
                new Passenger("Dude", "Foolish", 20)
        );
        String jsonString = CustomSerialize.serialization(passengers);
        String expectedResult = "[{\"name\":\"Jack\",\"soName\":\"Corn\",\"age\":88},{\"name\":\"Dude\",\"soName\":\"Foolish\",\"age\":20}]";
        assertEquals(expectedResult, jsonString);
    }

    @Test
    public void whenGivenPlainObject_afterSerializingAndDeserialization_thenCorrect() {
        Passenger expected = new Passenger("Jack", "Corn", 88);
        String jsonString = CustomSerialize.serialization(expected);
        Passenger recovered = CustomSerialize.deserialization(jsonString, Passenger.class);
        assertThat(recovered, is(expected));
    }

    @Test
    public void whenGivenArray_afterSerializingAndDeserialization_thenCorrect() {
        Passenger[] expected = {
                new Passenger("Jack", "Corn", 88),
                new Passenger("Dude", "Foolish", 20)
        };
        String jsonString = CustomSerialize.serialization(expected);
        Passenger[] recovered = CustomSerialize.deserialization(jsonString, Passenger[].class);
        assertTrue(Arrays.asList(recovered).contains(new Passenger("Jack", "Corn", 88)));
    }

    @Test
    public void whenGivenCollection_afterSerializingAndDeserialization_thenCorrect() {
        List<Passenger> expected = Arrays.asList(
                new Passenger("Jack", "Corn", 88),
                new Passenger("Dude", "Foolish", 20)
        );
        String jsonString = CustomSerialize.serialization(expected);
        Type listType = new TypeToken<ArrayList<Passenger>>() {
        }.getType();
        List<Passenger> recovered = CustomSerialize.deserialization(jsonString, listType);
        assertTrue(recovered.contains(new Passenger("Jack", "Corn", 88)));
    }

    @Test
    public void whenGivenComplexObject_afterSerializingAndDeserialization_thenCorrect() {

        Bus expected = new Bus();
        expected.setDirection("Nowhere");
        expected.setDriver(new Passenger("TOP", "MAN", 33));
        expected.setPassengers(
                Arrays.asList(
                        new Passenger("Jhon", "Pig", 25),
                        new Passenger("Pit", "Foolish", 25),
                        new Passenger("Met", "Heck", 25)
                )
        );

        String jsonString = CustomSerialize.serialization(expected);
        Bus recovered = CustomSerialize.deserialization(jsonString, Bus.class);
        assertThat(recovered, is(expected));
    }
}