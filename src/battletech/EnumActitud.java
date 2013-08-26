package battletech;

public enum EnumActitud {
	Defensiva,
	Equilibrada,
	Ofensiva,
	MuyOfensiva;
    
    private String value;

    private EnumActitud(){
    //	Equilibrada;
    }
    
    private EnumActitud(String value) {
            this.value = value;
    }
};   