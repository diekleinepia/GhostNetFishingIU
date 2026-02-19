package de.shepherd.bean;

import de.shepherd.entity.Geisternetz;
import de.shepherd.entity.MeldendePerson;
import de.shepherd.service.NetzService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class MeldBean {

    @Inject
    private NetzService netzService;

    private double lat;
    private double lng;
    private double groesse;
    private String name;
    private String telefon;

    private Long gespeicherteId;

    public String melden() {

        if (lat < -90 || lat > 90) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Lat muss zwischen -90 und 90 liegen."));
            return null;
        }

        if (lng < -180 || lng > 180) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Lng muss zwischen -180 und 180 liegen."));
            return null;
        }

        if (groesse <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Größe muss größer als 0 sein."));
            return null;
        }

        Geisternetz netz = new Geisternetz();
        netz.setLat(lat);
        netz.setLng(lng);
        netz.setGeschaetzteGroesse(groesse);

        MeldendePerson person = null;
        if ((name != null && !name.trim().isEmpty()) || (telefon != null && !telefon.trim().isEmpty())) {
            person = new MeldendePerson();
            person.setName(name);
            person.setTelefonnummer(telefon);
        }

        netzService.erfasseNetz(netz, person);
        gespeicherteId = netz.getId();

        return null;
    }

    public List<Geisternetz> getOffeneNetze() {
        return netzService.getOffeneNetze();
    }

    public String geborgenMelden(Long netzId) {
        netzService.meldeGeborgen(netzId);
        return "offeneNetze.xhtml?faces-redirect=true";
    }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public double getGroesse() { return groesse; }
    public void setGroesse(double groesse) { this.groesse = groesse; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public Long getGespeicherteId() { return gespeicherteId; }
}