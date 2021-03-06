/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import com.middleware.model.Messagerie;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;


@LocalBean
public interface MessagerieFacadeLocal {

    void create(Messagerie messagerie);

    void edit(Messagerie messagerie);

    void remove(Messagerie messagerie);

    Messagerie find(Object id);

    List<Messagerie> findAll();

    List<Messagerie> findRange(int[] range);

    int count();
    
}
