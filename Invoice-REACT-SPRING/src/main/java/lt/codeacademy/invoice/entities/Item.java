package lt.codeacademy.invoice.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String pavadinimas;
	private String kodas;
	private String aprasymas;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.DETACH)
	@JoinColumn(name = "itemgroup_id", nullable = false)
	private ItemGroup itemGroupId;
	
	
	private String statusas;
	private double bazineKaina;
	
}
