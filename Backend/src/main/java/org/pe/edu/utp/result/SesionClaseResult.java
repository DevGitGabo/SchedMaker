package org.pe.edu.utp.result;

import java.util.Objects;

public class SesionClaseResult {

    public SesionClaseResult(String cod_alu, String alumno, String ambiente, String dia, String hini, String hfin, String docente, String curso, String tipo_aula) {
        this.cod_alu = cod_alu;
        this.alumno = alumno;
        this.ambiente = ambiente;
        this.dia = dia;
        this.hini = hini;
        this.hfin = hfin;
        this.docente = docente;
        this.curso = curso;
        this.tipo_aula = tipo_aula;
    }

    public String getCod_alu() {
        return cod_alu;
    }

    public void setCod_alu(String cod_alu) {
        this.cod_alu = cod_alu;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHini() {
        return hini;
    }

    public void setHini(String hini) {
        this.hini = hini;
    }

    public String getHfin() {
        return hfin;
    }

    public void setHfin(String hfin) {
        this.hfin = hfin;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTipo_aula() {
        return tipo_aula;
    }

    public void setTipo_aula(String tipo_aula) {
        this.tipo_aula = tipo_aula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SesionClaseResult that)) return false;
        return Objects.equals(getCod_alu(), that.getCod_alu()) && Objects.equals(getAlumno(), that.getAlumno()) && Objects.equals(getAmbiente(), that.getAmbiente()) && Objects.equals(getDia(), that.getDia()) && Objects.equals(getHini(), that.getHini()) && Objects.equals(getHfin(), that.getHfin()) && Objects.equals(getDocente(), that.getDocente()) && Objects.equals(getCurso(), that.getCurso()) && Objects.equals(getTipo_aula(), that.getTipo_aula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_alu(), getAlumno(), getAmbiente(), getDia(), getHini(), getHfin(), getDocente(), getCurso(), getTipo_aula());
    }

    @Override
    public String toString() {
        return "SesionClaseResult{" +
                "cod_alu='" + cod_alu + '\'' +
                ", alumno='" + alumno + '\'' +
                ", ambiente='" + ambiente + '\'' +
                ", dia='" + dia + '\'' +
                ", hini='" + hini + '\'' +
                ", hfin='" + hfin + '\'' +
                ", docente='" + docente + '\'' +
                ", curso='" + curso + '\'' +
                ", tipo_aula='" + tipo_aula + '\'' +
                '}';
    }

    private String cod_alu;
    private String alumno;
    private String ambiente;
    private String dia;
    private String hini;
    private String hfin;
    private String docente;
    private String curso;
    private String tipo_aula;

}
