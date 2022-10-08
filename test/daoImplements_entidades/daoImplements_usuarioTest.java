/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImplements_entidades;

import entidades_basededatos.Usuario;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elvis
 */
public class daoImplements_usuarioTest{

    public daoImplements_usuarioTest() {
    }

    
///*
//    @Test
//    public void testIniciarSesion() throws Exception {
//        System.out.println("iniciarSesion");
//        Usuario u = null;
//        daoImplements_usuario instance = new daoImplements_usuario();
//        Usuario expResult = null;
//        Usuario result = instance.iniciarSesion(u);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of crearUsuario method, of class daoImplements_usuario.
//     */
//    @Test
//    public void testCrearUsuario() {
//        System.out.println("crearUsuario");
//        Usuario u = null;
//        daoImplements_usuario instance = new daoImplements_usuario();
//        instance.crearUsuario(u);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actualizarUsuario method, of class daoImplements_usuario.
//     */
//    @Test
//    public void testActualizarUsuario() {
//        System.out.println("actualizarUsuario");
//        Usuario u = null;
//        daoImplements_usuario instance = new daoImplements_usuario();
//        instance.actualizarUsuario(u);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminarUsuario method, of class daoImplements_usuario.
//     */
//    @Test
//    public void testEliminarUsuario() {
//        System.out.println("eliminarUsuario");
//        Usuario u = null;
//        daoImplements_usuario instance = new daoImplements_usuario();
//        instance.eliminarUsuario(u);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of lstUsuarios method, of class daoImplements_usuario.
//     */
//    
//    
    @Test
    public void testLstUsuarios() {
        System.out.println("lstUsuarios");
        daoImplements_usuario instance = new daoImplements_usuario();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.lstUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
