package es.albarregas.dao;


import es.albarregas.persistencia.HibernateUtil;
import java.io.Serializable;

import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Session;


public class GenericoDAO<T> implements IGenericoDAO<T> {
    
    private Session sesion;
    
    private void iniciaSesion(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }
    
    private void cierraSesion(){
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    private void manejaExcepcion(HibernateException he) throws HibernateException {
        sesion.getTransaction().rollback();
        throw he;
    } 

    @Override
    public void add(T objeto) {
        try {
            iniciaSesion();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
            
        } catch (HibernateException he){
            manejaExcepcion(he);
        } finally {
            cierraSesion();
        }
    }

    @Override
    public <T> List<T> get(String entidad) {
        List<T> listadoResultados = null;
        try {
            iniciaSesion();
            listadoResultados = sesion.createQuery(" from " + entidad).list();
        } catch(HibernateException he){
            this.manejaExcepcion(he);
        } finally {
            this.cierraSesion();
        }
        return listadoResultados;
    }

    @Override
    public <T> T getOne(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        
        try {
            iniciaSesion();
            objetoRecuperado = sesion.get(claseEntidad, pk);
        } catch(HibernateException he){
            this.manejaExcepcion(he);
        } finally {
            this.cierraSesion();
        }
        
        return objetoRecuperado;
    }

    @Override
    public void update(T objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T objeto) {
        try {
            iniciaSesion();
            sesion.delete(objeto);
        } catch(HibernateException he){
            this.manejaExcepcion(he);
        } finally {
            this.cierraSesion();
        }
    }

//    @Override
//    public void add(Object objeto) {
//        Session sesion = null;
//        try {
//            sesion = HibernateUtil.getSessionFactory().openSession();
//            sesion.beginTransaction();
//            sesion.save(objeto);
//            
//            sesion.getTransaction().commit();
//        } catch(org.hibernate.JDBCException jdbce){
//            if(sesion != null){
//                sesion.getTransaction().rollback();
//            }
//        } finally {
//            if(sesion != null){
//                sesion.close();
//            }
//        }
//    }
//
//    @Override
//    public List<Object> get(String entidad) {
//        List<Object> listado = null;
//        Session sesion = null;
//        try {
//            sesion = HibernateUtil.getSessionFactory().openSession();
//            sesion.beginTransaction();
//            listado = sesion.createQuery(" from " + entidad).list();
//            sesion.getTransaction().commit();
//        } catch(org.hibernate.JDBCException jdbce){
//            if(sesion != null){
//                sesion.getTransaction().rollback();
//            }
//        } finally {
//            if(sesion != null){
//                sesion.close();
//            }
//        }
//        return listado;
//    }
//
//    @Override
//    public void delete(Object objeto) {
//        Session sesion = null;
//        try {
//            sesion = HibernateUtil.getSessionFactory().openSession();
//            sesion.beginTransaction();
//            sesion.delete(objeto);
//            sesion.getTransaction().commit();
//        } catch(org.hibernate.JDBCException jdbce){
//            if(sesion != null){
//                sesion.getTransaction().rollback();
//            }
//        } finally {
//            if(sesion != null){
//                sesion.close();
//            }
//        }
//    }
//
//    @Override
//    public void update(Object objeto) {
//        Session sesion = null;
//        try {
//            sesion = HibernateUtil.getSessionFactory().openSession();
//            sesion.beginTransaction();
//            sesion.update(objeto);
//            sesion.getTransaction().commit();
//        } catch(org.hibernate.JDBCException jdbce){
//            if(sesion != null){
//                sesion.getTransaction().rollback();
//            }
//        } finally {
//            if(sesion != null){
//                sesion.close();
//            }
//        }
//    }
//
//    @Override
//    public <T> T getOne(Serializable pk, Class<T> claseEntidad) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
