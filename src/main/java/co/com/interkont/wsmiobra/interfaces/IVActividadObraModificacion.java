package co.com.interkont.wsmiobra.interfaces;

import java.util.List;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.V_ActividadObraModificacion;

public interface IVActividadObraModificacion {


	List<V_ActividadObraModificacion> desplegarTodos(ObraModificacion obra);


	
}
