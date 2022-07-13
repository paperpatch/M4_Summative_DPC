package com.dpc.M4_Summative_DPC.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "games")
public class Games {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String studio;
    private String esrb;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getEsrb() {
        return esrb;
    }

    public void setEsrb(String esrb) {
        this.esrb = esrb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games = (Games) o;
        return id == games.id && Objects.equals(studio, games.studio) && Objects.equals(esrb, games.esrb) && Objects.equals(title, games.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studio, esrb, title);
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", studio='" + studio + '\'' +
                ", esrb='" + esrb + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
