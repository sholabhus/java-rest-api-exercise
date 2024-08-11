package com.cbfacademy.restapiexercise.ious;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name ="ious")
public class IOU  {
@Id
@GeneratedValue(strategy=GenerationType.UUID)

@Column(name ="Id")
private UUID id;
@Column(name ="Borrower")
private String borrower;
@Column(name ="Lender")
private String lender;
@Column(name ="Amount")
private BigDecimal amount;
@Column(name="DateTime")
private Instant dateTime;


public IOU() {
}

public IOU(String borrower,String lender,BigDecimal amount,Instant dateTime){
this.borrower =borrower;
this.lender =lender;
this.amount=amount;
this.dateTime =dateTime;

}

//Getter and Setters
public UUID getId(){
    return this.id;
}
public String getBorrower(){
    return this.borrower;

}

public void setBorrower(String borrower){
    this.borrower=borrower;
}

public String getLender(){
    return this.lender;

}

public void setLender(String borrower){
    this.borrower=borrower;
}

    public BigDecimal getAmount(){
        return this.amount;
    
    }
    
    public void setAmount(BigDecimal amount){
        this.amount=amount;
    }

    public Instant getDateTime(){
        return this.dateTime;
    
    }
    
    public void setDateTime(Instant dateTime){
        this.dateTime=dateTime;
    }
}
    
    

