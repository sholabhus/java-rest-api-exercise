package com.cbfacademy.restapiexercise.ious;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ious")
public class IOUController {

    @Autowired
    private IOUService iouService;

    public IOUController(IOUService iouService){
        this.iouService=iouService;
    }

    // getAllIOUS
    @GetMapping(produces = "application/json" )
  public ResponseEntity<List<IOU>> getAllIOUs(){
        List<IOU>ious=iouService.getAllIOUs();
        return ResponseEntity.ok(ious);
        
    }

    //getIOU
    @GetMapping(value="/{id}", produces = "application/json" )
    public ResponseEntity <IOU> getIOU(@PathVariable UUID id){
            IOU iou=iouService.findIOUById(id);
            if(iou !=null){
            return ResponseEntity.ok(iou); //return 200 ok with iou
        }else{
            return ResponseEntity.notFound().build(); //return 404 not found
        
    }
}

    
//create
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



    //update
    @PutMapping(value="/{id}", produces = "application/json")
    public IOU updateIou(@PathVariable UUID id, @RequestBody IOU iou) {
       return iouService.updateIOU(id,iou);
    }

    //delete
   @DeleteMapping(value="/{id}",produces ="application/json")
  public ResponseEntity <Void> deleteIou(@PathVariable UUID id) {
   try {
    iouService.deleteIOU(id);
      
    return ResponseEntity.status(HttpStatus.OK).build();
    } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

   }
  }
}
