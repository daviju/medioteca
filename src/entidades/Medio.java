package entidades;

import java.time.LocalDate;

public class Medio {

    // ATRIBUTOS
    public int numRegistro;
    public LocalDate fechaAdquisicion;
    public double precioCompra;
    public int numEjemplares;

    // CONSTRUCTOR
    public Medio(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares) {
        this.setNumRegistro(numRegistro);
        this.setFechaAdquisicion(fechaAdquisicion);
        this.setPrecioCompra(precioCompra);
        this.setNumEjemplares(numEjemplares);
    }
    
    // CONSTRUCTOR SIN ID
    public Medio(LocalDate fechaAdquisicion, double precioCompra, int numEjemplares) {
        this.setFechaAdquisicion(fechaAdquisicion);
        this.setPrecioCompra(precioCompra);
        this.setNumEjemplares(numEjemplares);
    }

    
    // GETTERS Y SETTERS
    public int getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(int numRegistro) {
        this.numRegistro = numRegistro;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(int numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    
    // TO STRING
    @Override
    public String toString() {
        return "Medio [numRegistro=" + numRegistro + ", fechaAdquisicion=" + fechaAdquisicion 
               + ", precioCompra=" + precioCompra + ", numEjemplares=" + numEjemplares + "]";
    }


	public void imprimirFicha() {
		// TODO Auto-generated method stub
		
	}
}
