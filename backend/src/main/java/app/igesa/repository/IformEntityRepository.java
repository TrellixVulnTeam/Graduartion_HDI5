package app.igesa.repository;
import app.igesa.entity.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/**
 * @author Wassim Hachaani
 */
public interface IformEntityRepository  extends JpaRepository<FormEntity,Long> {
    Optional<FormEntity> findFirstByEntrepriseId(Long id);
    Optional<FormEntity> findByEntrepriseId(Long id);

}
