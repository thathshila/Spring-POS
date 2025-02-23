package lk.ijse.spring_pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String code;
    private String name;
    private String description;
    private String image;
    private int quantity;
    private double price;
    private Date date;
}
