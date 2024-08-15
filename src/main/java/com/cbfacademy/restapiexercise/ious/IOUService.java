package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class IOUService {

    @Autowired
    private IOURepository iouRepository;

    public IOUService(IOURepository iouRepository){
        this.iouRepository =iouRepository;
    }

    public List<IOU> getAllIOUs(){
        return this.iouRepository.findAll();
        
        }
    
    public IOU getIOU(UUID id) throws NoSuchElementException {
        return iouRepository.findById(id)
                             .orElseThrow(() -> new NoSuchElementException("IOU not found with id: " + id));
    }
    

    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
        if (iou == null) {
            throw new IllegalArgumentException("IOU cannot be null");
        }

        try {
            IOU savedIou = iouRepository.save(iou);
            return savedIou;
        } catch (OptimisticLockingFailureException e) {
            // Log the exception if needed
            // For example: logger.error("Optimistic locking failure", e);
            throw e; // Re-throw the exception to be handled by the caller
        } catch (Exception e) {
            // Handle unexpected exceptions
            // For example: logger.error("An unexpected error occurred", e);
            throw new RuntimeException("An unexpected error occurred while saving the IOU", e);
        }
    }


   public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
   IOU existiingIou =getIOU(id);
    return iouRepository.save(existiingIou);
   }

   public IOU deleteIOU(UUID id){
    Optional<IOU> optionalIou =iouRepository.findById(id);
    if(optionalIou.isPresent()) {
        IOU iou=optionalIou.get();
        iouRepository.deleteById(id);
        return iou;
    } else {
        throw new NoSuchElementException( "No Record Found");
    }
}

}

   


  





