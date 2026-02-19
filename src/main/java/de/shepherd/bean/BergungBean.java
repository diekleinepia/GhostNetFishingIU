package de.shepherd.bean;

import de.shepherd.service.NetzService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class BergungBean {

    @Inject
    private NetzService netzService;

    private Long netzId;
    private String name;
    private String telefon;

    public String eintragen() {

        boolean hasError = false;

        if (name == null || name.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("form:name",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte trage deinen Namen ein.", null));
            hasError = true;
        }

        if (telefon == null || telefon.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("form:telefon",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte trage deine Telefonnummer ein.", null));
            hasError = true;
        }

        if (hasError) return null;

        boolean ok = netzService.eintragen(netzId, name.trim(), telefon.trim());
        if (!ok) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Eintragen nicht möglich (Status oder Zuordnung)."));
            return null;
        }

        return "offeneNetze.xhtml?faces-redirect=true";
    }

    public Long getNetzId() { return netzId; }
    public void setNetzId(Long netzId) { this.netzId = netzId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}