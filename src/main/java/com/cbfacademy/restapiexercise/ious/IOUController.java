package com.cbfacademy.restapiexercise.ious;

import java.util.List;
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

    @GetMapping(value="/{id}", produces = "application/json" )
    public IOU getIOU(@PathVariable UUID id){
        //if (borrower == null || borrower.isEmpty()) {
           //s  IOU iou=iouService.findIOUById(id);
            //if(iou !=null){
            //return ResponseEntity.ok(iou);
        //}else{
           // return ResponseEntity.notFound().build();
        return iouService.getIOU(id);
    }

    
//create
    @PostMapping( produces = "application/json")
    public ResponseEntity <IOU> createIou(@RequestBody IOU iou){
        try {
        IOU savedIou =iouService.createIOU(iou);
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
    public ResponseEntity <IOU> deleteIou(@PathVariable UUID id) {
        iouService.deleteIOU(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
