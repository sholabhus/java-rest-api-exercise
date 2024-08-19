package com.cbfacademy.restapiexercise.ious;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {

    
 List<IOU> findByBorrower(String borrower);
 

 //native @Query annotation 
 @Query(value="SELECT  * FROM ious WHERE amount > (SELECT AVG(amount) FROM ious )", nativeQuery=true )
    List<IOU> findHighValueIOUs();

//return IOUs that are below or equal to the average value. JPQL
    @Query(value="SELECT i FROM IOU i WHERE i.amount <= (SELECT Avg(i2.amount)FROM IOU i2)")
    List<IOU> findLowValueIOUs();
 
}
