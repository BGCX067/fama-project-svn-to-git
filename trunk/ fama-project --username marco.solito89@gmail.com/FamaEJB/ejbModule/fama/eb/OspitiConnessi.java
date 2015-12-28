package fama.eb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="OspitiConnessi")
public class OspitiConnessi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idTemp;
	
	public long getIdTemp() {
		return idTemp;
	}
}
