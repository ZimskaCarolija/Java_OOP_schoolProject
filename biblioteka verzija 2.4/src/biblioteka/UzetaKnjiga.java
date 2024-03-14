/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteka;

/**
 *
 * @author pc
 */
public class UzetaKnjiga {//Boris
    private Knjiga knjiga;
    private String datumUzimanja;
    private String datumVracanja;
    private boolean vraceno;

    public UzetaKnjiga(Knjiga knjiga, String datumUzimanja, String datumVracanja, boolean vraceno) {//Boris
        this.knjiga = knjiga;
        this.datumUzimanja = datumUzimanja;
        this.datumVracanja = datumVracanja;
        this.vraceno = vraceno;
    }

    public UzetaKnjiga() {//Boris
    
    }

    public Knjiga getKnjiga() {//Boris
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {//Boris
        this.knjiga = knjiga;
    }

    public String getDatumUzimanja() {//Boris
        return datumUzimanja;
    }

    public void setDatumUzimanja(String datumUzimanja) {//Boris
        this.datumUzimanja = datumUzimanja;
    }

    public String getDatumVracanja() {//Boris
        return datumVracanja;
    }

    public void setDatumVracanja(String datumVracanja) {//Boris
        this.datumVracanja = datumVracanja;
    }

    public boolean isVraceno() {//Boris
        return vraceno;
    }

    public void setVraceno(boolean vraceno) {//Boris
        this.vraceno = vraceno;
    }

    @Override
    public String toString() {//Aleksa
        String vracenS = " knjiga je vracena";
        if(!vraceno)
            vracenS = " knjiga nije vracena";
        return "Knjiga - Naziv : "+knjiga.getNaziv() + ",  Autor : " + knjiga.getAutor() + ",  Datum uzimanja : " +  datumUzimanja + ",  Rok vracanja : " + datumVracanja +vracenS;
    }       
}
