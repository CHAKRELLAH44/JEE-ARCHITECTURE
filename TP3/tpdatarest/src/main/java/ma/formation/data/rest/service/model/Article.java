package ma.formation.data.rest.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double price;
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
}
