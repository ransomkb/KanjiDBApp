/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "kanjis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kanjis.findAll", query = "SELECT k FROM Kanjis k"),
    @NamedQuery(name = "Kanjis.findById", query = "SELECT k FROM Kanjis k WHERE k.id = :id"),
    @NamedQuery(name = "Kanjis.findByKanji", query = "SELECT k FROM Kanjis k WHERE k.kanji = :kanji"),
    @NamedQuery(name = "Kanjis.findByEng", query = "SELECT k FROM Kanjis k WHERE k.eng = :eng"),
    @NamedQuery(name = "Kanjis.findByKunyomi", query = "SELECT k FROM Kanjis k WHERE k.kunyomi = :kunyomi"),
    @NamedQuery(name = "Kanjis.findByOnyomi", query = "SELECT k FROM Kanjis k WHERE k.onyomi = :onyomi"),
    @NamedQuery(name = "Kanjis.findByExamples", query = "SELECT k FROM Kanjis k WHERE k.examples = :examples")})
public class Kanjis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "kanji")
    private String kanji;
    @Column(name = "eng")
    private String eng;
    @Column(name = "kunyomi")
    private String kunyomi;
    @Column(name = "onyomi")
    private String onyomi;
    @Column(name = "examples")
    private String examples;

    public Kanjis() {
    }

    public Kanjis(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kanjis)) {
            return false;
        }
        Kanjis other = (Kanjis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Kanjis[ id=" + id + " ]";
    }
    
}
