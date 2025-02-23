package lk.ijse.spring_pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    private String code;
    private String name;
    private String description;
    private String image;
    private int quantity;
    private double price;
    private Date date;
}
