/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import jpa.Defis;

/**
 *
 * @author TOSHIBA
 */
@Local
public interface DefisFacadeLocal {

    void create(Defis defis);

    void edit(Defis defis);

    void remove(Defis defis);

    Defis find(Object id);

    List<Defis> findAll();

    List<Defis> findRange(int[] range);

    int count();
    
}
