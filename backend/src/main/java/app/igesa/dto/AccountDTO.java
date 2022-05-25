package app.igesa.dto;
import app.igesa.entity.Account;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Groupe;
import app.igesa.entity.Role;
import app.igesa.enumerations.AccountStatus;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 *
 *  @author Tarchoun Abir
 *
 */
    @Data
    @Builder
   public class AccountDTO {
        private Long id;
        private String username;
        @Email
        private String email;
        private String password;
        private String matchingPassword;
        private String fiscaleCode ;
        private AccountStatus accountStatus;
        protected Date lastModifiedDate;
        protected Date createdDate;
        private Long groupId;
       // private Long entrepriseId;
        private Set<Role> roles = new HashSet<>();

        public static app.igesa.dto.AccountDTO fromEntity(Account account) {
            app.igesa.dto.AccountDTO.AccountDTOBuilder adto = app.igesa.dto.AccountDTO.builder()
                    .id(account.getId())
                    .fiscaleCode(account.getFiscaleCode())
                    .username(account.getUsername())
                    .email(account.getEmail())
                    .accountStatus(account.getAccountStatus())
                    .matchingPassword(account.getMatchingPassword())
                    .password(account.getPassword())
                    .lastModifiedDate(account.getLastModifiedDate())
                    //.groupId(account.getGroupe().getId())
                    //.entrepriseId(account.getEntreprise().getId())
                    .roles(account.getRoles())
                    .createdDate(account.getCreatedDate());
            // .build();
           /* if(account.getEntreprise()!=null){
                adto.entrepriseId(account.getEntreprise().getId());
            }
            */
            if(account.getGroupe()!=null){

                adto.groupId(account.getGroupe().getId());
            }
            return adto.build();
        }

        public static Account toEntity(app.igesa.dto.AccountDTO dto) {

            Account account = new Account();
            account.setId(dto.getId());
            account.setAccountStatus(dto.getAccountStatus());
            account.setUsername(dto.getUsername());
            account.setEmail(dto.getEmail());
            account.setPassword(dto.getPassword());
            account.setFiscaleCode(dto.getFiscaleCode());
            account.setMatchingPassword(dto.getMatchingPassword());
            account.setCreatedDate(dto.getCreatedDate());
            account.setLastModifiedDate(dto.getLastModifiedDate());
            account.setRoles(dto.getRoles());
            //===================> Groupe
            Groupe groupe = new Groupe();
            groupe.setId(dto.getGroupId());
            account.setGroupe(groupe);
            //===================> entreprise
            Entreprise entreprise = new Entreprise();
            //entreprise.setId(dto.getEntrepriseId());
            //account.setEntreprise(entreprise);
            return account;
        }


    }
