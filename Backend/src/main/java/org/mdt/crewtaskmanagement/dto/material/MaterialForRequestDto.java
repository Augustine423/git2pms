package org.mdt.crewtaskmanagement.dto.material;

import lombok.*;

@Data
public class MaterialForRequestDto {
    private long id;
    private String name;
    private String type;
    private long price;
    private String condition;
    private String receivedDate; // Keep as string if formatting later
    private long quantity;       // was int → changed to long

    public MaterialForRequestDto(long id, String name, String type, long price, String condition, java.time.LocalDate receivedDate, long quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.condition = condition;
        this.receivedDate = receivedDate != null ? receivedDate.toString() : null; // convert to String
        this.quantity = quantity;
    }
}
