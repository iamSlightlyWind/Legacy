package fa.training.entities;

import fa.training.annotation.DatabaseColumn;
import fa.training.annotation.DatabaseTable;
import fa.training.annotation.IDColumn;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(name = "vehicle")
public class Vehicle {

    @IDColumn
    @DatabaseColumn(name = "id")
    private Long id;

    @DatabaseColumn(name = "vehicle_name")
    @NonNull
    private String name;

    @DatabaseColumn(name = "type")
    @NonNull
    private String type;
}
