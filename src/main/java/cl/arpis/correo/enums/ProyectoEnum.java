package cl.arpis.correo.enums;

public enum ProyectoEnum {
	ENVIO_PAGO_CONTROLPROP("1");

	private String valor;

	ProyectoEnum(String value) {
		this.valor = value;
	}

	public String getValor() {
		return this.valor;
	}
}
