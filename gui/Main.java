/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import domein.Domeincontroller;

/**
 *
 * @author Admin
 */
public class Main {

    
    public static void main(String[] args) {
       new ConsoleInterface(new Domeincontroller()).run();
       
    }

}
