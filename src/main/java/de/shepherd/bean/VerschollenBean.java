package de.shepherd.bean;

import de.shepherd.service.NetzService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class VerschollenBean {

    @Inject
    private NetzService netzService;

    private Long netzId;
    private String name;
    private String telefonnummer;

    public String melden() {

        boolean hasError = false;

        if (name == null || name.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("form:name",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte trage deinen Namen ein.", null));
            hasError = true;
        }

        if (telefonnummer == null || telefonnummer.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("form:telefonnummer",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte trage deine Telefonnummer ein.", null));
            hasError = true;
        }

        if (hasError) return null;

        netzService.meldeVerschollen(netzId);
        return "offeneNetze.xhtml?faces-redirect=true";
    }

    public Long getNetzId() { return netzId; }
    public void setNetzId(Long netzId) { this.netzId = netzId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelefonnummer() { return telefonnummer; }
    public void setTelefonnummer(String telefonnummer) { this.telefonnummer = telefonnummer; }
}