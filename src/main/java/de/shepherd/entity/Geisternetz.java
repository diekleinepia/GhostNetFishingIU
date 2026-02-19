package de.shepherd.entity;

import jakarta.persistence.*;

@Entity
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;
    private Double lng;

    private Double geschaetzteGroesse;

    @Enumerated(EnumType.STRING)
    private Status status = Status.GEMELDET;

    @ManyToOne
    private BergendePerson bergendePerson;

    @ManyToOne
    private MeldendePerson meldendePerson;

    public Long getId() { return id; }

    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }

    public Double getLng() { return lng; }
    public void setLng(Double lng) { this.lng = lng; }

    public Double getGeschaetzteGroesse() { return geschaetzteGroesse; }
    public void setGeschaetzteGroesse(Double geschaetzteGroesse) { this.geschaetzteGroesse = geschaetzteGroesse; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public BergendePerson getBergendePerson() { return bergendePerson; }
    public void setBergendePerson(BergendePerson bergendePerson) { this.bergendePerson = bergendePerson; }

    public MeldendePerson getMeldendePerson() { return meldendePerson; }
    public void setMeldendePerson(MeldendePerson meldendePerson) { this.meldendePerson = meldendePerson; }

    public boolean eintragen(BergendePerson person) {
        if (person == null) return false;
        if (status != Status.GEMELDET) return false;
        if (bergendePerson != null) return false;

        this.bergendePerson = person;
        this.status = Status.BERGUNG_BEVORSTEHEND;
        return true;
    }

    public boolean geborgenMelden() {
        if (status != Status.BERGUNG_BEVORSTEHEND) return false;
        this.status = Status.GEBORGEN;
        return true;
    }

    public void verschollenMelden() {
        this.status = Status.VERSCHOLLEN;
        this.bergendePerson = null;
    }
}