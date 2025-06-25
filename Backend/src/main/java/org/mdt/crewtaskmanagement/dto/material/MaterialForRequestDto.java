package org.mdt.crewtaskmanagement.dto.material;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MaterialForRequestDto {
    private long id;
    private String name;
    private String type;
    private long price;
    private String condition;
    private String receivedDate;
    private String lifeTime;
    private long quantity;

    public MaterialForRequestDto(long id, String name, String type, long price, String condition, LocalDate receivedDate, String lifeTime, long quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.condition = condition;
        this.receivedDate =  receivedDate.toString();
        this.lifeTime = lifeTime;
        this.quantity = quantity;
    }
}
