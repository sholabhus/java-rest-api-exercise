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

}
