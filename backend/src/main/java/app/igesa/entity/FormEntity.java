package app.igesa.entity;
import app.igesa.enumerations.ContactStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

/**
 *
 * @author Tarchoun Abir
 *
 **/

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Contact")
public class FormEntity  extends Auditable{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String name ;
	private String companyname;
	private String mobile ;
	private String fax ;
	@Email
	private String email ;
	private String adresse ;
	private String nationality ;
	private String referent ;

	/**
	 *  status Contact
	 */
	private ContactStatus contactstatus ;

	/**
	 * Product used
	 */
	@Setter
	@Getter
	@ManyToOne
	private Product softwareused ;

	/**
	 * ENTERPRISE
	 *
	 */
	@Setter
	@Getter
	@ManyToOne
	private Entreprise entreprise;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		FormEntity that = (FormEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
