package com.cbfacademy.restapiexercise.ious;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ious")
public class IOUController {

    @Autowired
    private IOUService iouService;

    public IOUController(IOUService iouService){
        this.iouService=iouService;
    }

    // getAllIOUs
    @GetMapping(produces = "application/json" )
  public ResponseEntity<List<IOU>> getAllIOUs(@RequestParam(required = false) String borrower){
        if (borrower == null || borrower.isEmpty()) {
         List<IOU>ious=iouService.getAllIOUs();// call service method to getAllIous
            return ResponseEntity.ok(ious); //return 200 ok with iou
        } else {
            List<IOU> ious=iouService.getIOUsByBorrower(borrower); //call service method to filter by browser 
                return ResponseEntity.ok(ious);
        }
           // return ResponseEntity.ok().build();
           //  return ResponseEntity.ok(ious); //return 200 ok with iou

            }
        
    //getIOU
    @GetMapping(value="/{id}", produces = "application/json" )
    public ResponseEntity <IOU> getIOU(@PathVariable UUID id){
           try{
             IOU iou=iouService.getIOU(id);
             if(iou !=null){
            return ResponseEntity.ok(iou); //return 200 ok with iou
        }else{
            return ResponseEntity.notFound().build(); //return 404 not found
        } 
} catch (NoSuchElementException e) {
    return ResponseEntity.notFound().build(); // Handle case where IOU is not found
}

    }

  
//createIOU
    @PostMapping( produces = "application/json")
    public ResponseEntity <IOU> createIou(@RequestBody IOU iou){
        
       try {
        IOU savedIou =iouService.createIOU(iou);
        if(savedIou== null || iou.getId() == null){
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedIou);
   }catch (IllegalArgumentException exception){
       return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
   }catch(OptimisticLockingFailureException exception) {
     return  ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    }


    //updateIOU
    @PutMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<IOU> updateIou(@PathVariable UUID id, @RequestBody IOU iou) {
        try{
        IOU updatedIou =iouService.updateIOU(id,iou);
          return ResponseEntity.ok(updatedIou); //return 200 ok with iou
        }catch (NoSuchElementException e) {
         return ResponseEntity.notFound().build();
       
    }
}

    //deleteIO
   @DeleteMapping(value="/{id}",produces ="application/json")
  public ResponseEntity <Void> deleteIou(@PathVariable UUID id) {
   try {
    iouService.deleteIOU(id);
   return ResponseEntity.ok().build(); //return 200 ok with iou     
    } catch (NoSuchElementException e) {
       return ResponseEntity.notFound().build();
        
   }
  }

  @GetMapping(value="/high",produces = "application/json")
 public ResponseEntity <List<IOU>> getHighValueIOUS(){
    List<IOU> ious=iouService.getHighValueIOUs();
    return ResponseEntity.ok(ious);

 }

    
}

