package hotelbrakutxeu;

import hotelbrakutxeu.model.dao.FuncionarioDAO;

/**
 *
 * @author pc
 */
public class NewClass {
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println(new FuncionarioDAO().listar());
        System.out.println("==========================================================================");
    }
}
