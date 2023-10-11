package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table(name="simulointi")
public class Simulointi {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="paivays", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paivays;

    @Column(name="aika")
    private double aika;
    @Column(name="viive")
    private long viive;

    public Simulointi(double aika, long viive) {
        this.aika = aika;
        this.viive = viive;
    }

    @Override
    public String toString() {
        return "Id: " + id + ". Päiväys: " + paivays;
    }

    public Simulointi(){

    }

    public int getId() {
        return id;
    }

    public double getAika() {
        return aika;
    }

    public long getViive() {
        return viive;
    }
}
