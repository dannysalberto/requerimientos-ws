package co.com.interkont.avanzame.bussines.interfaces;

import java.util.List;

import co.com.interkont.avanzame.models.DocumentosRequerimiento;

public interface IDocumentosRequerimiento {
	
	DocumentosRequerimiento buscarPorId(Integer idDoc);
	List<DocumentosRequerimiento> listar(Integer obraid);
	DocumentosRequerimiento crear(DocumentosRequerimiento docReq);
	void borrar(Integer iddocReq);

}
