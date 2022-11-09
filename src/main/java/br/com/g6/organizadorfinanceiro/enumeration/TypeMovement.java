package br.com.g6.organizadorfinanceiro.enumeration;

public enum TypeMovement {
    RECEITA,
    DESPESA;

    public static TypeMovement get(String type) {
    	if(type == null) return null;
    	for ( TypeMovement tp : values()) {
			if(tp.toString().equals(type)) return tp;
		}
    	return null;
    }
}
