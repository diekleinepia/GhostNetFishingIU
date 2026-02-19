package de.shepherd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BergendePerson extends Person {

    @OneToMany(mappedBy = "bergendePerson")
    private List<Geisternetz> netze = new ArrayList<>();

    public List<Geisternetz> getNetze() { return netze; }
}