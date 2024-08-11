package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

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

    private IOUService iouService;

    public IOUController(IOUService iouService){
        this.iouService=iouService;
    }
    @GetMapping(produces = "application/json" )
    public List<IOU> getAllIOUs(){
        return iouService.getAllIOUs();
        
    }

    @PostMapping(produces = "application/json")
    public IOU createIou(@RequestBody IOU iou){
        IOU savedIou =iouService.createIOU(iou);
        return  savedIou;
    }
    
    @PutMapping(value="/{id}", produces = "application/json")
    public IOU updateIou(@PathVariable UUID id, @RequestBody IOU iou) {
       return iouService.updateIOU(id,iou);
    }

    @DeleteMapping(value="/{id}",produces ="application/json")
    public IOU deleteIou(@PathVariable UUID id) {
       return iouService.deleteIOU(id);
    }

}
