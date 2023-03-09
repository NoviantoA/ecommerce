package novianto.anggoro.ecommerce.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image_model")
@Data
@NoArgsConstructor
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Column(length = 50000000)
    private byte[] picByte;

    public ImageModel(String name, String type, byte[] pibByte) {
        this.name = name;
        this.type = type;
        this.picByte = pibByte;
    }
}
