package cl.arpis.correo.service;

import cl.arpis.correo.entities.ApiUsuariosEntity;

public interface IAutenticarUsuario {

	public ApiUsuariosEntity buscarUsuario(String usuario);

}
