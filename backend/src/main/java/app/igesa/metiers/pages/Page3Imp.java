package app.igesa.metiers.pages;
import app.igesa.dto.Page2DTO;
import app.igesa.dto.Page3DTO;
import app.igesa.entity.pages.Page2;
import app.igesa.entity.pages.Page3;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ientreprise;
import app.igesa.metiers.Ipage3;
import app.igesa.repository.pages.Page3Repository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * Wassim Hachaani
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class Page3Imp implements Ipage3 {

    private static final Logger log = LoggerFactory.getLogger(Page3Imp.class);


    @Autowired
    Page3Repository page3Repository;
    @Autowired
    Ientreprise ientrepriseService;


    @Override
    public Page3DTO save(Page3DTO p) {
            Page3 pages= new Page3();
            if (p.getId()!=null){
                pages = page3Repository.findById(p.getId()).orElseThrow(IllegalAccessError::new);
            }
            pages.setEntreprise(ientrepriseService.getCurrentEnterprise());
            pages.setTitle(p.getTitle());
            pages.setHtmlContent(p.getHtmlContent());
            Page3 saved = page3Repository.save(Page3DTO.toEntity((p)));
            return Page3DTO.fromEntity(saved);

        }

        @Override
        public Collection<Page3DTO> view() {
            log.debug("<====================HTTP by current entreprise =====================>");
            return page3Repository.findFirstByEntrepriseId(ientrepriseService.getCurrentEnterprise().getId()).stream()
                    .map(Page3DTO::fromEntity)
                    .collect(Collectors.toList());
        }


    @Override
    public Page3DTO findById(Long id) {
        log.debug("<======================== HTTP GET BY ID {}==========================>", id);
        if (id == null) {
            log.error(" Id is NULL .. !!");
            return null;
        }
     return (Page3DTO) page3Repository.findFirstByEntrepriseId(ientrepriseService.getCurrentEnterprise().getId()).stream().map(Page3DTO::fromEntity);

    }

    @Override
    public void delete(Long id) {
        log.debug("<========================== HTTP DELETE BY ID {} =======================>", id);
        if (id == null) {
            log.error("ID IS NULL ");
            return;
        }
       page3Repository.deleteById(id);
}

}


