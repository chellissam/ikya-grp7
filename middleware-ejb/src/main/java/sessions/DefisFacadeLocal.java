/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import com.middleware.model.Defis;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;


@LocalBean
public interface DefisFacadeLocal {

    void create(Defis defis);

    void edit(Defis defis);

    void remove(Defis defis);

    Defis find(Object id);

    List<Defis> findAll();

    List<Defis> findRange(int[] range);

    int count();
    
}
