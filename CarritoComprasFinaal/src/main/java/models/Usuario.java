package models;

public class Usuario {
    private String username;
    private String password;
    private String tipo; // "cliente" o "empleado"
    private double saldo; // Solo para clientes

    public Usuario(String username, String password, String tipo, double saldo) {
        this.username = username;
        this.password = password;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public Usuario(String username, String password, String tipo) {
        this(username, password, tipo, 0);
    }

    // Método para incrementar el saldo del usuario (solo para clientes)
    public void incrementarSaldo(double cantidad) {
        if (this.tipo.equals("cliente")) {
            this.saldo += cantidad;
        }
    }

    // Método para decrementar el saldo del usuario (solo para clientes)
    public void decrementarSaldo(double cantidad) {
        if (this.tipo.equals("cliente")) {
            this.saldo -= cantidad;
        }
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

