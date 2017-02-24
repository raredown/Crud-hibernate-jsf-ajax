/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.modelo;

import es.albarregas.dao.IGenericoDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Daw2
 */
@ManagedBean
@RequestScoped
@Entity
@Table(name = "personas")
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idPerso;
    private String nombre;
    private String apellidos;
    private String email;
    @Transient
    private String mensaje = "";

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdPerso() {
        return idPerso;
    }

    public void setIdPerso(int idPerso) {
        this.idPerso = idPerso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void add() {
        DAOFactory daof = DAOFactory.getDAOFactory();
//        IPersonaDAO adao = daof.getPersonaDAO();
        IGenericoDAO gdao = daof.getGenericoDAO();
        Persona persona = new Persona();
        persona.setApellidos(this.apellidos);
        persona.setEmail(this.email);
        persona.setNombre(this.nombre);
        this.mensaje = "Se a introducido correctamente";
        gdao.add(persona);
    }

    public void borrar() {
        if (this.idPerso != 0) {
            DAOFactory daof = DAOFactory.getDAOFactory();
//        IPersonaDAO adao = daof.getPersonaDAO();
            IGenericoDAO gdao = daof.getGenericoDAO();
            Persona persona = new Persona();
            persona.setApellidos(this.apellidos);
            persona.setEmail(this.email);
            persona.setNombre(this.nombre);
            persona.setIdPerso(this.idPerso);
            gdao.delete(persona);
            this.mensaje = "Se a borrado correctamente";

        }
    }

    public void update() {
        if (this.idPerso != 0) {
            DAOFactory daof = DAOFactory.getDAOFactory();
//        IPersonaDAO adao = daof.getPersonaDAO();
            IGenericoDAO gdao = daof.getGenericoDAO();
            Persona persona = new Persona();
            persona.setApellidos(this.apellidos);
            persona.setEmail(this.email);
            persona.setNombre(this.nombre);
            persona.setIdPerso(this.idPerso);
            gdao.update(persona);
            this.mensaje = "Se a actualizado correctamente";

        }
    }

    public ArrayList<Persona> getPersonas() {
        ArrayList<Persona> lista = new ArrayList();
        DAOFactory daof = DAOFactory.getDAOFactory();
//        IPersonaDAO adao = daof.getPersonaDAO();
        IGenericoDAO gdao = daof.getGenericoDAO();
        lista = (ArrayList<Persona>) gdao.get("Persona");
        return lista;
    }

    public void cogerPersona() {
        if (this.idPerso != 0) {

            DAOFactory daof = DAOFactory.getDAOFactory();
//        IPersonaDAO adao = daof.getPersonaDAO();
            IGenericoDAO gdao = daof.getGenericoDAO();
            Persona persona = new Persona();

            persona = (Persona) gdao.getOne(this.idPerso, Persona.class);
            this.apellidos = persona.getApellidos();
            this.email = persona.getEmail();
            this.nombre = persona.getNombre();
            this.idPerso = persona.getIdPerso();
        }
    }
}
