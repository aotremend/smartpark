package ro.tremend.smartpark.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tremend.smartpark.model.ParkingSpot;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Andrei Olteanu on 22-Sep-17.
 */
@Repository
public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Long> {

    @Override
    ParkingSpot findOne(Long aLong);

    @Query("SELECT ps FROM ParkingSpot ps where ps.state = 'available'")
    List<ParkingSpot> findAvailable();

    @Override
    Iterable<ParkingSpot> findAll();

    CrudRepository save(CrudRepository entity);
}
