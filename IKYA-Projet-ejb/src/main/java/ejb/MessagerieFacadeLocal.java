/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import jpa.Messagerie;

/**
 *
 * @author TOSHIBA
 */
@Local
public interface MessagerieFacadeLocal {

    void create(Messagerie messagerie);

    void edit(Messagerie messagerie);

    void remove(Messagerie messagerie);

    Messagerie find(Object id);

    List<Messagerie> findAll();

    List<Messagerie> findRange(int[] range);

    int count();
    
}
