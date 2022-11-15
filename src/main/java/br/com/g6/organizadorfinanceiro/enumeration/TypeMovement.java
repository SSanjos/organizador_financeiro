package br.com.g6.organizadorfinanceiro.enumeration;

public enum TypeMovement {
    receita,
    despesa;

    public static TypeMovement get(String type) {
        if(type == null) return null;
        for ( TypeMovement tp : values()) {
            if(tp.toString().toLowerCase().equals(type.toLowerCase())) return tp;
        }
        return null;
    }}
