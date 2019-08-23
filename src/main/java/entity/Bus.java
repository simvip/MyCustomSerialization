package entity;

import lombok.Data;

import java.util.List;

/**
 * Class Bus
 *
 * @author create by ivanslusar
 * 8/22/19
 * @project CustomSerialization
 */
@Data
public class Bus {
    int totalFare;
    String direction;
    Passenger driver;
    List<Passenger> passengers;
}
